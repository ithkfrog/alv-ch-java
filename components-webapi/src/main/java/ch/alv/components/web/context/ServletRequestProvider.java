package ch.alv.components.web.context;

import javax.servlet.http.HttpServletRequest;

/**
 * Convenience Bean to easily access the HttpServletRequest.
 *
 * @since 1.0.0
 */
public interface ServletRequestProvider {

    String getLanguage();

    String getBasePath();

    HttpServletRequest getRequest();


}
