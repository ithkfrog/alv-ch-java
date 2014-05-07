package ch.alv.components.web.endpoint.filter.internal;

import ch.alv.components.core.spring.SecurityContextProvider;
import ch.alv.components.core.utils.StringHelper;
import ch.alv.components.web.endpoint.Endpoint;
import ch.alv.components.web.endpoint.EndpointRegistry;
import ch.alv.components.web.endpoint.filter.SecurityFilter;
import ch.alv.components.web.endpoint.filter.SecurityFilterResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Default implementation of the {@link ch.alv.components.web.endpoint.filter.SecurityFilter} interface.
 *
 * @since 1.0.0
 */
public class DefaultSecurityFilter implements SecurityFilter {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultSecurityFilter.class);

    @Resource
    private SecurityContextProvider securityContextProvider;

    @Resource
    private EndpointRegistry endpointRegistry;

    @Override
    public SecurityFilterResult doFilter(HttpServletRequest request, String moduleName, String storeName) {
        Endpoint endpoint = endpointRegistry.getEndpoint(moduleName, storeName);

        if (endpoint == null) {
            throw new IllegalStateException("No endpoint for store '" + moduleName + "/" + storeName + "' found.");
        }

        boolean result;

        if ("POST".equalsIgnoreCase(request.getMethod())) {
            if (StringHelper.isEmpty(endpoint.getRolesPOST())) {
                result = true;
            } else {
                result = securityContextProvider.hasAnyRole(Arrays.asList(endpoint.getRolesPOST().split(",")));
            }
        } else if ("PUT".equalsIgnoreCase(request.getMethod())) {
            if (StringHelper.isEmpty(endpoint.getRolesPUT())) {
                result = true;
            } else {
                result = securityContextProvider.hasAnyRole(Arrays.asList(endpoint.getRolesPUT().split(",")));
            }
        } else if ("DELETE".equalsIgnoreCase(request.getMethod())) {
            if (StringHelper.isEmpty(endpoint.getRolesDELETE())) {
                result = true;
            } else {
                result = securityContextProvider.hasAnyRole(Arrays.asList(endpoint.getRolesDELETE().split(",")));
            }
        } else {
            if (StringHelper.isEmpty(endpoint.getRolesGET())) {
                result = true;
            } else {
                result = securityContextProvider.hasAnyRole(Arrays.asList(endpoint.getRolesGET().split(",")));
            }
        }

        if (result) {
            return new SecurityFilterResult(SecurityFilterResult.OK, "Access granted.");
        }
        String msg = "Access denied to store '" + moduleName + ":" + storeName + "' for user '" + securityContextProvider.getUser().getUsername() + "'!";
        LOG.debug(msg);
        return new SecurityFilterResult(SecurityFilterResult.NOK, msg);
    }
}
