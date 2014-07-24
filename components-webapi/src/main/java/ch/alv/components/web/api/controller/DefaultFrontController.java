package ch.alv.components.web.api.controller;

import ch.alv.components.core.utils.StringHelper;
import ch.alv.components.service.ServiceLayerException;
import ch.alv.components.web.WebLayerException;
import ch.alv.components.web.api.config.ApiConfiguration;
import ch.alv.components.web.api.config.ApiConfigurationProvider;
import ch.alv.components.web.api.filter.HttpRequestFilter;
import ch.alv.components.web.api.filter.HttpRequestFilterComparator;
import ch.alv.components.web.api.filter.HttpRequestFilterException;
import ch.alv.components.web.api.handler.DeleteRequestHandler;
import ch.alv.components.web.api.handler.GetRequestHandler;
import ch.alv.components.web.api.handler.PostRequestHandler;
import ch.alv.components.web.api.handler.PutRequestHandler;
import ch.alv.components.web.api.request.HttpServletRequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Default implementation of the {@link FrontController} interface.
 *
 * @since 1.0.0
 */
@Controller
public class DefaultFrontController implements FrontController {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultFrontController.class);

    @Resource
    private GetRequestHandler getRequestHandler;
    @Resource
    private PostRequestHandler postRequestHandler;
    @Resource
    private PutRequestHandler putRequestHandler;
    @Resource
    private DeleteRequestHandler deleteRequestHandler;

    @Resource
    private ApiConfigurationProvider apiConfigurationProvider;

    private List<HttpRequestFilter> filters = new ArrayList<>();

    @Override
    @RequestMapping(value = "/**", method = RequestMethod.GET)
    public Object handleGetRequest(HttpServletRequest request) throws HttpRequestFilterException, ServiceLayerException {
        HttpServletRequestWrapper adapter = createAdapterAndApplyFilters(request);
        return getGetRequestHandler().handleRequest(adapter, getApiConfiguration());
    }

    @Override
    @RequestMapping(value = "/**", method = RequestMethod.POST)
    public Object handlePostRequest(HttpServletRequest request) throws HttpRequestFilterException, IOException, WebLayerException, ServiceLayerException {
        HttpServletRequestWrapper adapter = createAdapterAndApplyFilters(request);
        return getPostRequestHandler().handleRequest(adapter, getApiConfiguration());
    }

    @Override
    @RequestMapping(value = "/**", method = RequestMethod.PUT)
    public Object handlePutRequest(HttpServletRequest request) throws HttpRequestFilterException, IOException, WebLayerException, ServiceLayerException {
        HttpServletRequestWrapper adapter = createAdapterAndApplyFilters(request);
        return getPutRequestHandler().handleRequest(adapter, getApiConfiguration());
    }

    @Override
    @RequestMapping(value = "/**", method = RequestMethod.DELETE)
    public Object handleDeleteRequest(HttpServletRequest request) throws HttpRequestFilterException, ServiceLayerException, WebLayerException {
        HttpServletRequestWrapper adapter = createAdapterAndApplyFilters(request);
        return getDeleteRequestHandler().handleRequest(adapter, getApiConfiguration());
    }

    private HttpServletRequestWrapper createAdapterAndApplyFilters(HttpServletRequest request) throws HttpRequestFilterException {
        HttpServletRequestWrapper adapter = new HttpServletRequestWrapper(request);
        String method = request.getMethod();
        LOG.debug("Received a new " + method + " request and assigned correlationId '" + adapter.getCorrelationId() + "'.");
        applyFilters(adapter);
        LOG.debug(adapter.getCorrelationId() + ": delegate request to registered "
                + StringHelper.capitalize(method.toLowerCase()) + "RequestHandler.");
        return adapter;
    }

    private void applyFilters(HttpServletRequestWrapper request) throws HttpRequestFilterException {
        LOG.debug(request.getCorrelationId() + ": phase 'applyFilters' starts.");
        for (HttpRequestFilter filter : filters) {
            LOG.debug(request.getCorrelationId() + ": applying filter '"
                    + filter.getName() + "' (priority " + filter.getPriority() + ").");

            filter.filterRequest(request, getApiConfiguration());

            LOG.debug(request.getCorrelationId() + ": filter '"
                    + filter.getName() + "' (priority " + filter.getPriority() + ") successfully applied.");
        }
        LOG.debug(request.getCorrelationId() + ": phase 'applyFilters' done.");
    }

    public GetRequestHandler getGetRequestHandler() {
        return getRequestHandler;
    }

    public void setGetRequestHandler(GetRequestHandler getRequestHandler) {
        this.getRequestHandler = getRequestHandler;
    }

    public PostRequestHandler getPostRequestHandler() {
        return postRequestHandler;
    }

    public void setPostRequestHandler(PostRequestHandler postRequestHandler) {
        this.postRequestHandler = postRequestHandler;
    }

    public PutRequestHandler getPutRequestHandler() {
        return putRequestHandler;
    }

    public void setPutRequestHandler(PutRequestHandler putRequestHandler) {
        this.putRequestHandler = putRequestHandler;
    }

    public DeleteRequestHandler getDeleteRequestHandler() {
        return deleteRequestHandler;
    }

    public void setDeleteRequestHandler(DeleteRequestHandler deleteRequestHandler) {
        this.deleteRequestHandler = deleteRequestHandler;
    }

    public ApiConfiguration getApiConfiguration() {
        return apiConfigurationProvider.getConfiguration();
    }

    public List<HttpRequestFilter> getFilters() {
        return filters;
    }

    public void setFilters(List<HttpRequestFilter> filters) {
        if (CollectionUtils.isEmpty(filters)) {
            throw new IllegalArgumentException("Filters to add must not be null or empty.");
        }
        this.filters = filters;
        Collections.sort(this.filters, new HttpRequestFilterComparator());
    }

    public void addFilter(HttpRequestFilter filter) {
        if (filter == null) {
            throw new IllegalArgumentException("Filter to add must not be null.");
        }
        this.filters.add(filter);
        Collections.sort(this.filters, new HttpRequestFilterComparator());
    }

    public void setApiConfigurationProvider(ApiConfigurationProvider apiConfigurationProvider) {
        this.apiConfigurationProvider = apiConfigurationProvider;
    }
}
