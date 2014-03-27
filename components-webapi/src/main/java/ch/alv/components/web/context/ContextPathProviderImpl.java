package ch.alv.components.web.context;

import org.springframework.beans.factory.annotation.Value;

/**
 * Implementation of the {@link ContextPathProvider} interface.
 *
 * @since 1.0.0
 */
public class ContextPathProviderImpl implements ContextPathProvider {

    @Value("${api.contextPath}")
    private String contextPath = "";

    @Override
    public String getContextPath() {
        return contextPath;
    }
}
