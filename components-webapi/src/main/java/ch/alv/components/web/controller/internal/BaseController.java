package ch.alv.components.web.controller.internal;

import ch.alv.components.core.spring.ApplicationContextProvider;
import ch.alv.components.core.utils.ConversionUtils;
import ch.alv.components.web.WebConstant;
import ch.alv.components.web.dto.DtoFactory;
import ch.alv.components.web.endpoint.Endpoint;
import ch.alv.components.web.endpoint.EndpointRegistry;
import ch.alv.components.web.endpoint.filter.EndpointHttpMethodFilter;
import ch.alv.components.web.endpoint.filter.SecurityFilter;
import ch.alv.components.web.endpoint.filter.UnSupportedMethodException;
import ch.alv.components.web.endpoint.filter.UnauthorizedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 * Base implementation of a {@link ch.alv.components.web.controller.FrontController}.
 *
 * @since 1.0.0
 */
public abstract class BaseController {

    private static final String BEAN_NAME_DTO_FACTORY = "dtoFactory";

    @Value("${api.defaultPage}")
    private int defaultPage = 0;

    @Value("${api.defaultPageSize}")
    private int defaultPageSize = 100;

    private final ApplicationContextProvider contextProvider;

    @Resource
    private EndpointRegistry endpointRegistry;

    @Resource
    private SecurityFilter securityFilter;

    @Resource
    private EndpointHttpMethodFilter methodFilter;

    protected BaseController(ApplicationContextProvider contextProvider) {
        this.contextProvider = contextProvider;
    }

    protected void runFilters(HttpServletRequest request, String moduleName, String storeName) throws UnSupportedMethodException, UnauthorizedException {
        methodFilter.doFilter(request.getMethod(), moduleName, storeName);
        securityFilter.doFilter(request, moduleName, storeName);
    }

    /**
     * If the request contains the params "page" or "pageSize", those will be used to create a new {@link org.springframework.data.domain.Pageable}. For missing values, the defaults
     * become applied.
     *
     * @param request the servlet request.
     * @return pageable that matches the requests requirements.
     */
    protected Pageable createPageable(HttpServletRequest request) {
        int page = ConversionUtils.toIntValue(request.getParameter(WebConstant.PARAM_NAME_PAGE), getDefaultPage());
        int pageSize = ConversionUtils.toIntValue(request.getParameter(WebConstant.PARAM_NAME_PAGE_SIZE), getDefaultPageSize());
        return new PageRequest(page, pageSize);
    }

    protected int getDefaultPage() {
        return defaultPage;
    }

    protected int getDefaultPageSize() {
        return defaultPageSize;
    }

    protected <T> T getBean(String beanName) {
        return contextProvider.getBeanByName(beanName);
    }

    protected Endpoint getEndpoint(String moduleName, String storeName) {
        return endpointRegistry.getEndpoint(moduleName, storeName);
    }

    protected DtoFactory getDtoFactory() {
        return getBean(BEAN_NAME_DTO_FACTORY);
    }

}
