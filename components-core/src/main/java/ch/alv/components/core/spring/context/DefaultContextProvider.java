package ch.alv.components.core.spring.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * ApplicationContextAware, enabled to fetch beans
 *
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")
public class DefaultContextProvider implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static <T> T getBeanByName(String beanName) {
        return (T) getApplicationContext().getBean(beanName);
    }

    public static <T> Map<String, T> getBeansOfType(Class<T> type) {
        return getApplicationContext().getBeansOfType(type);
    }

}
