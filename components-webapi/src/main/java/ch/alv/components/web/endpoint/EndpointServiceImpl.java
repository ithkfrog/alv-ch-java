package ch.alv.components.web.endpoint;

import ch.alv.components.core.mapper.BeanMapper;
import ch.alv.components.core.model.ModelItem;
import ch.alv.components.core.search.ValuesProvider;
import ch.alv.components.core.spring.context.DefaultContextProvider;
import ch.alv.components.core.utils.StringHelper;
import ch.alv.components.service.persistence.PersistenceService;
import ch.alv.components.service.persistence.PersistenceServiceRegistry;
import ch.alv.components.service.search.SearchService;
import ch.alv.components.web.dto.Dto;
import ch.alv.components.web.dto.DtoFactory;
import ch.alv.components.web.search.WebValuesProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Default implementation of the {@link EndpointService} interface.
 *
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")
public class EndpointServiceImpl implements EndpointService {

    private static final String PARAM_SEARCH_NAME = "searchName";

    private static final Logger LOG = LoggerFactory.getLogger(EndpointServiceImpl.class);

    @Resource
    private BeanMapper mapper;

    @Resource
    private DtoFactory dtoFactory;

    @Override
    public ResponseEntity<?> find(Pageable pageable, Map<String, String[]> params, String moduleName, String storeName) {
        Endpoint endpoint = EndpointRegistry.getEndpoint(moduleName, storeName);
        String[] searchNameValues = params.get(PARAM_SEARCH_NAME);
        String searchName = null;
        if (searchNameValues != null && searchNameValues.length > 0) {
            searchName = searchNameValues[0];
        }
        PersistenceService service = PersistenceServiceRegistry.getService(endpoint.getServiceName());
        SearchService searchService = DefaultContextProvider.getBeanByName(endpoint.getServiceName());
        if (StringHelper.isEmpty(searchName)) {
            Page page;
            if (params.isEmpty()) {
                page = service.getAll(pageable);
            } else {
                Class<? extends ValuesProvider> paramValuesProviderClass = endpoint.getValuesProviderClass();
                WebValuesProvider provider = (WebValuesProvider) createProvider(paramValuesProviderClass);
                if (provider == null) {
                    LOG.error("Error while executing search: No valuesProvider of class " + paramValuesProviderClass.getName() + " found.");
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
                provider.setSource(params);
                page = searchService.find(pageable, provider);
            }
            return new ResponseEntity<>(new PageImpl(convertEntityListToDtoList(page.getContent(), endpoint), pageable, page.getTotalElements()), HttpStatus.OK);
        } else {
            Page page;
            if (params.isEmpty()) {
                page = service.getAll(pageable);
            } else {
                Class<? extends ValuesProvider> paramValuesProviderClass = endpoint.getValuesProviderClass();
                WebValuesProvider provider = (WebValuesProvider) createProvider(paramValuesProviderClass);
                if (provider == null) {
                    LOG.error("Error while executing search: No valuesProvider of class '" + paramValuesProviderClass.getName() + "' found.");
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
                provider.setSource(params);
                page = searchService.find(pageable, searchName, provider);
            }
            return new ResponseEntity<>(new PageImpl(convertEntityListToDtoList(page.getContent(), endpoint), pageable, page.getTotalElements()), HttpStatus.OK);
        }
    }

    private Dto convertEntityToDto(ModelItem entity, Endpoint endpoint) {
        return dtoFactory.createDtoFromEntity(entity, endpoint);
    }

    private List<Dto> convertEntityListToDtoList(List<ModelItem> entities, Endpoint endpoint) {
        List<Dto> dtos = new ArrayList<>();
        for (ModelItem entity : entities) {
            dtos.add(convertEntityToDto(entity, endpoint));
        }
        return dtos;
    }

    @Override
    public Object getById(String moduleName, String storeName, String id) {
        Endpoint endpoint = EndpointRegistry.getEndpoint(moduleName, storeName);
        PersistenceService service = PersistenceServiceRegistry.getService(endpoint.getServiceName());
        ModelItem entity;
        entity = service.getById(id);
        if (entity == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return dtoFactory.createDtoFromEntity(entity, endpoint);
    }

    @Override
    public Object post(String moduleName, String storeName, String body) {
        return save(moduleName, storeName, null, body);
    }

    public Object save(String moduleName, String storeName, String id, String body) {
        Endpoint endpoint = EndpointRegistry.getEndpoint(moduleName, storeName);
        Dto dto = dtoFactory.createDtoFromRequestBody(body, endpoint);
        if (dto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        ModelItem entity = mapper.mapObject(dto, endpoint.getEntityClass());
        entity.setId(id);
        PersistenceService service = PersistenceServiceRegistry.getService(endpoint.getServiceName());
        entity = service.save(entity);
        return entity;
    }

    @Override
    public Object post(String moduleName, String storeName, String id, String body) {
        return save(moduleName, storeName, id, body);
    }

    @Override
    public Object put(String moduleName, String storeName, String body) {
        return save(moduleName, storeName, null, body);
    }

    @Override
    public Object put(String moduleName, String storeName, String id, String body) {
        return save(moduleName, storeName, id, body);
    }

    @Override
    public Object delete(String moduleName, String storeName, String id) {
        if (id == null) {
            return new ResponseEntity<>("The id must not be null!", HttpStatus.EXPECTATION_FAILED);
        }
        Endpoint endpoint = EndpointRegistry.getEndpoint(moduleName, storeName);
        PersistenceService service = PersistenceServiceRegistry.getService(endpoint.getServiceName());
        service.delete(id);
        return new ResponseEntity<>("Entity successfully deleted", HttpStatus.OK);
    }

    protected ValuesProvider createProvider(Class<? extends ValuesProvider> providerClass) {
        try {
            return providerClass.newInstance();
        } catch (Exception e) {
            LOG.error(e.getMessage());
            throw new IllegalStateException(e);
        }
    }


}
