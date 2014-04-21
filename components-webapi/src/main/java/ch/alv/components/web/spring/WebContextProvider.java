package ch.alv.components.web.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

/**
 * ApplicationContextAware, enabled to fetch beans
 *
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")
public class WebContextProvider implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext newContext) throws BeansException {
        applicationContext = newContext;
    }

    public static <T> T getBeanByName(String beanName) {
        return (T) getApplicationContext().getBean(beanName);
    }

    public static <T> Map<String, T> getBeansOfType(Class<T> type) {
        return getApplicationContext().getBeansOfType(type);
    }

}
