package ch.alv.components.web.context;

import org.springframework.beans.factory.annotation.Value;

/**
 * Created with IntelliJ IDEA.
 * User: TYWUG
 * Date: 18.03.14
 * Time: 14:14
 * To change this template use File | Settings | File Templates.
 */
public class ContextPathProviderImpl implements ContextPathProvider {

    @Value("${api.contextPath}")
    private String contextPath = "";

    @Override
    public String getContextPath() {
        return contextPath;
    }
}
