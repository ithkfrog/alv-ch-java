package ch.alv.components.web.endpoint.filter;

import ch.alv.components.core.spring.security.SecurityContextProvider;
import ch.alv.components.core.utils.StringHelper;
import ch.alv.components.web.endpoint.Endpoint;
import ch.alv.components.web.endpoint.EndpointRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Default implementation of the {@link SecurityFilter} interface.
 *
 * @since 1.0.0
 */
public class SecurityFilterImpl implements SecurityFilter {

    private static final Logger LOG = LoggerFactory.getLogger(SecurityFilterImpl.class);

    @Resource
    private SecurityContextProvider securityContextProvider;

    @Override
    public SecurityFilterResult doFilter(HttpServletRequest request, String moduleName, String storeName) {
        Endpoint endpoint = EndpointRegistry.getEndpoint(moduleName, storeName);
        boolean result;
        switch (request.getMethod()) {
            case "POST":
                if (StringHelper.isEmpty(endpoint.getRolesPOST())) {
                    result = true;
                } else {
                    result = securityContextProvider.hasAnyRole(Arrays.asList(endpoint.getRolesPOST().split(",")));
                }
                break;
            case "PUT":
                if (StringHelper.isEmpty(endpoint.getRolesPUT())) {
                    result = true;
                } else {
                    result = securityContextProvider.hasAnyRole(Arrays.asList(endpoint.getRolesPUT().split(",")));
                }
                break;
            case "DELETE":
                if (StringHelper.isEmpty(endpoint.getRolesDELETE())) {
                    result = true;
                } else {
                    result = securityContextProvider.hasAnyRole(Arrays.asList(endpoint.getRolesDELETE().split(",")));
                }
                break;
            default:
                if (StringHelper.isEmpty(endpoint.getRolesGET())) {
                    result = true;
                } else {
                    result = securityContextProvider.hasAnyRole(Arrays.asList(endpoint.getRolesGET().split(",")));
                }
                break;
        }
        if (result) {
            return new SecurityFilterResult(SecurityFilterResult.OK, "Access granted.");
        }
        String msg = "Access denied to store '" + moduleName + ":" + storeName + "' for user '" + securityContextProvider.getUser().getUsername() + "'!";
        LOG.debug(msg);
        return new SecurityFilterResult(SecurityFilterResult.OK, "Access denied.");
    }
}
