package ch.alv.components.web.context;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * Default implementation of the {@link ServletRequestProvider} interface.
 *
 * @since 1.0.0
 */
public class ServletRequestProviderImpl implements ServletRequestProvider {

    @Autowired
    private HttpServletRequest request;

    public String getLanguage() {
        return request.getParameter("language");
    }

    @Override
    public String getBasePath() {
        return "http://" + request.getLocalAddr() + ":" + request.getLocalPort() + request.getServletPath() + "/";
    }

    @Override
    public HttpServletRequest getRequest() {
        return request;
    }

}
