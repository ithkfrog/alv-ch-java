package ch.alv.components.web.controller;

import ch.alv.components.core.utils.ConversionUtils;
import ch.alv.components.web.endpoint.EndpointHttpMethodFilter;
import ch.alv.components.web.endpoint.EndpointHttpMethodFilterResult;
import ch.alv.components.web.endpoint.EndpointService;
import ch.alv.components.web.security.SecurityFilter;
import ch.alv.components.web.security.SecurityFilterResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 * Base implementation of a {@link FrontController}.
 *
 * @since 1.0.0
 */
public class DefaultFrontController implements FrontController {

    @Value("${api.defaultPage}")
    private int defaultPage = 0;

    @Value("${api.defaultPageSize}")
    private int defaultPageSize = 100;

    private static final String PARAM_NAME_PAGE = "page";
    private static final String PARAM_NAME_PAGE_SIZE = "pageSize";

    @Resource
    private SecurityFilter securityFilter;

    @Resource
    private EndpointHttpMethodFilter methodFilter;

    @Resource
    private EndpointService endpointService;


    @RequestMapping(value = "/{moduleName}/{storeName}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object handleRequest(HttpServletRequest request,
                                @PathVariable String moduleName,
                                @PathVariable String storeName,
                                @RequestBody String body) {

        EndpointHttpMethodFilterResult methodFilterResult = methodFilter.doFilter(request.getMethod(), moduleName, storeName);
        if (!methodFilterResult.isOk()) {
            return new ResponseEntity<>(methodFilterResult.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
        }

        SecurityFilterResult securityFilterResult = securityFilter.doFilter(request, moduleName, storeName);
        if (!securityFilterResult.isOk()) {
            return new ResponseEntity<>(securityFilterResult.getMessage(), HttpStatus.UNAUTHORIZED);
        }

        if (HttpMethod.GET.toString().equals(request.getMethod())) {
            Pageable pageable = createPageable(request);
            return endpointService.find(pageable, request.getParameterMap(), moduleName, storeName);
        }
        if (HttpMethod.POST.toString().equals(request.getMethod())) {
            return endpointService.post(moduleName, storeName, body);
        }
        if (HttpMethod.PUT.toString().equals(request.getMethod())) {
            return endpointService.put(moduleName, storeName, body);
        }
        return new ResponseEntity<>(methodFilterResult.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/{moduleName}/{storeName}/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object handleRequest(HttpServletRequest request,
                                @PathVariable String moduleName,
                                @PathVariable String storeName,
                                @PathVariable String id,
                                @RequestBody String body) {

        EndpointHttpMethodFilterResult methodFilterResult = methodFilter.doFilter(request.getMethod(), moduleName, storeName);
        if (!methodFilterResult.isOk()) {
            return new ResponseEntity<>(methodFilterResult.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
        }

        SecurityFilterResult securityFilterResult = securityFilter.doFilter(request, moduleName, storeName);
        if (!securityFilterResult.isOk()) {
            return new ResponseEntity<>(securityFilterResult.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        if (HttpMethod.GET.toString().equals(request.getMethod())) {
            return endpointService.getById(moduleName, storeName, id);
        }
        if (HttpMethod.POST.toString().equals(request.getMethod())) {
            return endpointService.post(moduleName, storeName, id, body);
        }
        if (HttpMethod.PUT.toString().equals(request.getMethod())) {
            return endpointService.put(moduleName, storeName, id, body);
        }
        if (HttpMethod.DELETE.toString().equals(request.getMethod())) {
            return endpointService.delete(moduleName, storeName, id);
        }
        return new ResponseEntity<>(methodFilterResult.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * If the request contains the params "page" or "pageSize", those will be used to create a new {@link org.springframework.data.domain.Pageable}. For missing values, the defaults
     * become applied.
     *
     * @param request
     * @return
     */
    private Pageable createPageable(HttpServletRequest request) {
        int page = ConversionUtils.toIntValue(request.getParameter(PARAM_NAME_PAGE), getDefaultPage());
        int pageSize = ConversionUtils.toIntValue(request.getParameter(PARAM_NAME_PAGE_SIZE), getDefaultPageSize());
        return new PageRequest(page, pageSize);
    }

    private int getDefaultPage() {
        return defaultPage;
    }

    private int getDefaultPageSize() {
        return defaultPageSize;
    }


}
