package ch.alv.components.core.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

/**
 * Decorator interface extending the {@link ApplicationContextAware} interface
 *
 * @since 1.0.0
 */
public interface ApplicationContextProvider extends ApplicationContextAware {

    ApplicationContext getApplicationContext();

    <T> T getBeanByName(String beanName);

    <T> Map<String, T> getBeansOfType(Class<T> type);

}
