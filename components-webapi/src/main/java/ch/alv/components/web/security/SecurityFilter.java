package ch.alv.components.web.security;

import javax.servlet.http.HttpServletRequest;

/**
 * Security Filter for requests.
 *
 * @author seco-hrf
 * @since  1.0.0
 */
public interface SecurityFilter {

    SecurityFilterResult doFilter(HttpServletRequest request, String moduleName, String storeName);

}
