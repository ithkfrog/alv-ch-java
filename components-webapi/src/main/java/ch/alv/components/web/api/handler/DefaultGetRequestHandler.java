package ch.alv.components.web.api.handler;

import ch.alv.components.core.beans.mapper.BeanMapper;
import ch.alv.components.core.search.MapBasedValuesProvider;
import ch.alv.components.core.search.ValuesProvider;
import ch.alv.components.core.utils.StringHelper;
import ch.alv.components.service.ServiceLayerException;
import ch.alv.components.service.data.PagingDataService;
import ch.alv.components.web.api.config.ApiConfiguration;
import ch.alv.components.web.api.config.ResourceConfiguration;
import ch.alv.components.web.api.request.HttpServletRequestWrapper;
import ch.alv.components.web.api.utils.UriParamExtractor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Default implementation of the {@link GetRequestHandler} interface.
 *
 * @since 1.0.0
 */
public class DefaultGetRequestHandler extends BaseDefaultRequestHandler implements GetRequestHandler {

    public static final int DEFAULT_PAGE_NUMBER = 0;
    public static final int DEFAULT_PAGE_SIZE = 100;
    public static final String PAGE_NUMBER_PARAM = "pageNumber";
    public static final String PAGE_SIZE_PARAM = "pageSize";
    public static final String QUERY_NAME_PARAM = "queryName";
    public static final String DEFAULT_QUERY_NAME = "defaultQuery";

    @Resource
    private PagingDataService dataService;

    @Resource
    private BeanMapper mapper;

    @Override
    public Object handleRequest(HttpServletRequestWrapper request, ApiConfiguration apiConfiguration) throws ServiceLayerException {
        ResourceConfiguration configuration = apiConfiguration.getResourceForRequest(request.getRequest());
        if (configuration.hasIdUriParam() || configuration.hasIdQueryParameter()) {
            return handleByIdRequest(request, apiConfiguration, configuration);
        }
        return handleQueryRequest(request.getRequest(), apiConfiguration);
    }

    @SuppressWarnings("unchecked")
    private Object handleByIdRequest(HttpServletRequestWrapper wrapper, ApiConfiguration apiConfiguration, ResourceConfiguration configuration) throws ServiceLayerException {
        Object result;
        Class<?> requestEntity = configuration.getResourceType();
        Class<?> targetEntity = getTargetEntity(requestEntity);
        String id = extractIdFromUrl(wrapper.getRequest().getRequestURL().toString(), apiConfiguration.getBaseUri() + configuration.getUri());
        result = dataService.find(id, targetEntity);
        if (result == null) {
            return new ResponseEntity<>(configuration.getName() + " not found.", HttpStatus.NOT_FOUND);
        }
        if (targetEntity != requestEntity) {
            result = mapper.mapObject(result, requestEntity);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    private String extractIdFromUrl(String requestURL, String configurationURL) {
        return (String) new UriParamExtractor(configurationURL).guessIdParam(requestURL).getValue();
    }

    @SuppressWarnings("unchecked")
    private Object handleQueryRequest(HttpServletRequest request, ApiConfiguration apiConfiguration) throws ServiceLayerException {
        int pageNumber = getPageNumber(request);
        int pageSize = getPageSize(request);
        String queryName = getQueryName(request);
        Class<?> requestEntity = apiConfiguration.getResourceForRequest(request).getResourceType();
        Class<?> targetEntity = getTargetEntity(requestEntity);
        Page result = dataService.find(new PageRequest(pageNumber, pageSize), queryName, createValuesProvider(request, apiConfiguration), targetEntity);
        if (targetEntity != requestEntity) {
            List<?> dtos = result.getContent();
            result = new PageImpl(mapper.mapCollection(dtos, requestEntity), new PageRequest(result.getNumber(), result.getSize()), result.getTotalElements());
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    private int getPageNumber(HttpServletRequest request) {
        int pageNumber = DEFAULT_PAGE_NUMBER;
        if (StringHelper.isNotEmpty(request.getParameter(PAGE_NUMBER_PARAM))) {
            pageNumber = Integer.valueOf(request.getParameter(PAGE_NUMBER_PARAM));
        }
        return pageNumber;
    }

    private int getPageSize(HttpServletRequest request) {
        int pageSize = DEFAULT_PAGE_SIZE;
        if (StringHelper.isNotEmpty(request.getParameter(PAGE_SIZE_PARAM))) {
            pageSize = Integer.valueOf(request.getParameter(PAGE_SIZE_PARAM));
        }
        return pageSize;
    }

    private String getQueryName(HttpServletRequest request) {
        String queryName = DEFAULT_QUERY_NAME;
        if (StringHelper.isNotEmpty(request.getParameter(QUERY_NAME_PARAM))) {
            queryName = request.getParameter(QUERY_NAME_PARAM);
        }
        return queryName;
    }

    private ValuesProvider createValuesProvider(HttpServletRequest request, ApiConfiguration apiConfiguration) {
        Map<String, String[]> params = new HashMap<>();
        params.putAll(request.getParameterMap());
        params.putAll(new UriParamExtractor(apiConfiguration.getResourceByUrl(request.getRequestURL().toString()).getUri()).extractParams(request.getRequestURI()));
        return new MapBasedValuesProvider(params);
    }

}
