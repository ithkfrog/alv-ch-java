package ch.alv.components.core.spring.internal;

import ch.alv.components.core.spring.ApplicationContextProvider;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import java.util.Map;

/**
 * ApplicationContextAware, enabled to fetch beans
 *
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")
public class DefaultApplicationContextProvider implements ApplicationContextProvider {

    private ApplicationContext applicationContext = null;

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public <T> T getBeanByName(String beanName) {
        return (T) getApplicationContext().getBean(beanName);
    }

    public <T> Map<String, T> getBeansOfType(Class<T> type) {
        return getApplicationContext().getBeansOfType(type);
    }

}
