package ch.alv.components.web.controller;

import ch.alv.components.core.beans.Identifiable;
import ch.alv.components.core.beans.ModelItem;
import ch.alv.components.core.beans.mapper.BeanMapper;
import ch.alv.components.core.search.MapBasedValuesProvider;
import ch.alv.components.core.search.ValuesProvider;
import ch.alv.components.core.utils.ConversionUtils;
import ch.alv.components.core.utils.StringHelper;
import ch.alv.components.data.LinkPage;
import ch.alv.components.service.ServiceLayerException;
import ch.alv.components.service.data.PagingDataService;
import ch.alv.components.web.WebConstant;
import ch.alv.components.web.WebLayerException;
import ch.alv.components.web.dto.Dto;
import ch.alv.components.web.dto.DtoFactory;
import ch.alv.components.web.endpoint.Endpoint;
import ch.alv.components.web.endpoint.EndpointRegistry;
import ch.alv.components.web.endpoint.filter.EndpointHttpMethodFilter;
import ch.alv.components.web.endpoint.filter.SecurityFilter;
import ch.alv.components.web.endpoint.filter.UnSupportedMethodException;
import ch.alv.components.web.endpoint.filter.UnauthorizedException;
import ch.alv.components.web.exception.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Default controller for the alv-ch WebApi architecture.
 *
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")
public class DefaultWebApiController {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultWebApiController.class);
    public static final String DEFAULT_SEARCH = "defaultSearch";

    @Value("${api.defaultPage}")
    private int defaultPage = 0;

    @Value("${api.defaultPageSize}")
    private int defaultPageSize = 100;

    private final DtoFactory dtoFactory;

    private final EndpointRegistry endpointRegistry;

    private final BeanMapper mapper;

    @Resource
    private SecurityFilter securityFilter;

    @Resource
    private EndpointHttpMethodFilter methodFilter;

    @Resource
    private PagingDataService dataService;


    public DefaultWebApiController(DtoFactory dtoFactory, EndpointRegistry endpointRegistry, BeanMapper mapper) {
        this.dtoFactory = dtoFactory;
        this.endpointRegistry = endpointRegistry;
        this.mapper = mapper;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/{moduleName}/{storeName}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object handleGetRequest(HttpServletRequest request,
                                   @PathVariable String moduleName,
                                   @PathVariable String storeName) throws UnauthorizedException, UnSupportedMethodException, ServiceLayerException, NoSuchValuesProviderException, WebLayerException {
        runFilters(request, moduleName, storeName);
        return find(createPageable(request), request.getParameterMap(), moduleName, storeName, request);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{moduleName}/{storeName}/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object handleGetRequest(HttpServletRequest request,
                                   @PathVariable String moduleName,
                                   @PathVariable String storeName,
                                   @PathVariable String id) throws UnSupportedMethodException, UnauthorizedException, ServiceLayerException, WebLayerException {

        runFilters(request, moduleName, storeName);
        return getById(moduleName, storeName, id);
    }

    protected ResponseEntity<?> find(Pageable pageable, Map<String, String[]> params, String moduleName, String storeName, HttpServletRequest request) throws NoSuchValuesProviderException, ServiceLayerException {
        Endpoint endpoint = endpointRegistry.getEndpoint(moduleName, storeName);
        String searchName = extractSearchName(params);
        if (StringHelper.isEmpty(searchName) && StringHelper.isEmpty(endpoint.getDefaultSearchName())) {
            return handleDefaultSearch(pageable, params, endpoint, request);
        } else if (StringHelper.isEmpty(searchName)) {
            return handleNamedSearch(pageable, params, endpoint, endpoint.getDefaultSearchName(), request);
        } else {
            return handleNamedSearch(pageable, params, endpoint, searchName, request);
        }
    }

    protected String extractSearchName(Map<String, String[]> params) {
        String[] searchNameValues = params.get(WebConstant.PARAM_NAME_SEARCH);
        String searchName = null;
        if (searchNameValues != null) {
            searchName = searchNameValues[0];
        }
        return searchName;
    }

    protected ResponseEntity<?> handleDefaultSearch(Pageable pageable, Map<String, String[]> params, Endpoint endpoint, HttpServletRequest request) throws ServiceLayerException {
        Page page = dataService.find(pageable, DEFAULT_SEARCH, new MapBasedValuesProvider(params), endpoint.getEntityClass());
        LinkPage linkPage = new LinkPage(convertEntityListToDtoList(page.getContent(), endpoint), pageable, page.getTotalElements());
        linkPage.addLink(createSelfLink(request));
        return new ResponseEntity<>(linkPage, HttpStatus.OK);
    }

    private Link createSelfLink(HttpServletRequest request) {
        return new Link(request.getRequestURL().toString(), "self");
    }

    protected ResponseEntity<?> handleNamedSearch(Pageable pageable, Map<String, String[]> params, Endpoint endpoint, String searchName, HttpServletRequest request) throws ServiceLayerException {
        ValuesProvider provider = new MapBasedValuesProvider(params);
        LinkPage page = new LinkPage(dataService.find(pageable, searchName, provider, endpoint.getEntityClass()));
        LinkPage linkPage = new LinkPage(convertEntityListToDtoList(page.getContent(), endpoint), pageable, page.getTotalElements());
        linkPage.addLink(createSelfLink(request));
        return new ResponseEntity<>(linkPage, HttpStatus.OK);
    }

    protected Object getById(String moduleName, String storeName, String id) throws ServiceLayerException {
        Endpoint endpoint = endpointRegistry.getEndpoint(moduleName, storeName);
        Identifiable entity;
        entity = dataService.find(id, endpoint.getEntityClass());
        if (entity == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return dtoFactory.createDtoFromEntity(entity, endpoint);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{moduleName}/{storeName}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object handlePostRequest(HttpServletRequest request,
                                    @PathVariable String moduleName,
                                    @PathVariable String storeName,
                                    @RequestBody String body) throws UnauthorizedException, UnSupportedMethodException, ServiceLayerException, WebLayerException {
        runFilters(request, moduleName, storeName);
        ModelItem returnValue = (ModelItem) persist(moduleName, storeName, null, body);
        LOG.debug("POST request: Entity with id '" + returnValue.getId() + "' successfully updated in '" + moduleName + "." + storeName + "'.");
        return returnValue;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{moduleName}/{storeName}/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object handlePostRequest(HttpServletRequest request,
                                    @PathVariable String moduleName,
                                    @PathVariable String storeName,
                                    @PathVariable String id,
                                    @RequestBody String body) throws UnauthorizedException, UnSupportedMethodException, ServiceLayerException, WebLayerException {

        runFilters(request, moduleName, storeName);
        Object returnValue = persist(moduleName, storeName, id, body);
        LOG.debug("POST request: Entity with id '" + id + "' successfully updated in '" + moduleName + "." + storeName + "'.");
        return returnValue;
    }

    public Object persist(String moduleName, String storeName, String id, String body) throws ServiceLayerException {
        Endpoint endpoint = endpointRegistry.getEndpoint(moduleName, storeName);
        Dto dto = dtoFactory.createDtoFromRequestBody(body, endpoint);
        Identifiable entity = mapper.mapObject(dto, endpoint.getEntityClass());
        if (StringHelper.isNotEmpty(id)) {
            entity.setId(id);
        }
        entity = dataService.save(entity, endpoint.getEntityClass());
        return entity;
    }

    protected Dto convertEntityToDto(ModelItem entity, Endpoint endpoint) {
        return dtoFactory.createDtoFromEntity(entity, endpoint);
    }

    protected List<Dto> convertEntityListToDtoList(List<ModelItem> entities, Endpoint endpoint) {
        List<Dto> dtos = new ArrayList<>();
        for (ModelItem entity : entities) {
            dtos.add(convertEntityToDto(entity, endpoint));
        }
        return dtos;
    }


    @RequestMapping(method = RequestMethod.PUT, value = "/{moduleName}/{storeName}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object handlePutRequest(HttpServletRequest request,
                                   @PathVariable String moduleName,
                                   @PathVariable String storeName,
                                   @RequestBody String body) throws UnauthorizedException, UnSupportedMethodException, ServiceLayerException, WebLayerException {
        runFilters(request, moduleName, storeName);
        ModelItem returnValue = (ModelItem) persist(moduleName, storeName, null, body);
        LOG.debug("POST request: Entity with id '" + returnValue.getId() + "' successfully updated in '" + moduleName + "." + storeName + "'.");
        return returnValue;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{moduleName}/{storeName}/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object handlePutRequest(HttpServletRequest request,
                                   @PathVariable String moduleName,
                                   @PathVariable String storeName,
                                   @PathVariable String id,
                                   @RequestBody String body) throws UnauthorizedException, UnSupportedMethodException, ServiceLayerException, WebLayerException {

        runFilters(request, moduleName, storeName);
        Object returnValue = persist(moduleName, storeName, id, body);
        LOG.debug("PUT request: Entity with id '" + id + "' successfully updated in '" + moduleName + "." + storeName + "'.");
        return returnValue;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{moduleName}/{storeName}({id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object handleDeleteRequest(HttpServletRequest request,
                                      @PathVariable String moduleName,
                                      @PathVariable String storeName,
                                      @PathVariable String id) throws UnauthorizedException, UnSupportedMethodException, ServiceLayerException, WebLayerException {
        if (StringHelper.isEmpty(id)) {
            throw new BadRequestException("The id must not be null!");
        }
        runFilters(request, moduleName, storeName);
        dataService.delete(id, getEndpoint(moduleName, storeName).getEntityClass());
        LOG.debug("DELETE request: Entity with id '" + id + "' successfully deleted in '" + moduleName + "." + storeName + "'.");
        return new ResponseEntity<>("Entity successfully deleted", HttpStatus.OK);
    }

    private void runFilters(HttpServletRequest request, String moduleName, String storeName) throws UnSupportedMethodException, UnauthorizedException, WebLayerException {
        if (methodFilter != null) {
            methodFilter.doFilter(request.getMethod(), moduleName, storeName);
        }
        if (securityFilter != null) {
            securityFilter.doFilter(request, moduleName, storeName);
        }

    }


    /**
     * If the request contains the params "page" or "pageSize", those will be used to create a new {@link org.springframework.data.domain.Pageable}. For missing values, the defaults
     * become applied.
     *
     * @param request the servlet request.
     * @return pageable that matches the requests requirements.
     */
    private Pageable createPageable(HttpServletRequest request) {
        int page = ConversionUtils.toIntValue(request.getParameter(WebConstant.PARAM_NAME_PAGE), getDefaultPage());
        int pageSize = ConversionUtils.toIntValue(request.getParameter(WebConstant.PARAM_NAME_PAGE_SIZE), getDefaultPageSize());
        return new PageRequest(page, pageSize);
    }

    private int getDefaultPage() {
        return defaultPage;
    }

    private int getDefaultPageSize() {
        return defaultPageSize;
    }

    private Endpoint getEndpoint(String moduleName, String storeName) {
        return endpointRegistry.getEndpoint(moduleName, storeName);
    }

}
