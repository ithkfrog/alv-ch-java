package ch.alv.components.web.context;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Default implementation of the {@link ServletRequestProvider} interface.
 *
 * @since 1.0.0
 */
public class ServletRequestProviderImpl implements ServletRequestProvider {

    @Resource
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
