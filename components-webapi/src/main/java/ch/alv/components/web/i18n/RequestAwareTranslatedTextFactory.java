package ch.alv.components.web.i18n;

import ch.alv.components.core.enums.Language;
import ch.alv.components.core.spring.ApplicationContextProvider;
import ch.alv.components.core.utils.StringHelper;
import ch.alv.components.data.jpa.TextConstant;
import ch.alv.components.web.context.ServletRequestProvider;
import ch.alv.components.web.dto.internal.TranslatedText;
import org.dozer.BeanFactory;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Converts {@link TextConstant} into {@link ch.alv.components.web.dto.internal.TranslatedText}.
 *
 * @since 1.0.0
 */
public class RequestAwareTranslatedTextFactory implements BeanFactory {

    private static final Language LANGUAGE_DEFAULT = Language.GERMAN;

    @Resource
    private ApplicationContextProvider contextProvider;

    @Override
    public Object createBean(Object o, Class<?> aClass, String s) {
        String defaultValue = "";
        if (o instanceof List) {
            ServletRequestProvider requestProvider = getRequestProvider();

            Language language = null;
            if (requestProvider != null && StringHelper.isNotEmpty(requestProvider.getLanguage())) {
                language = Language.getByCode(requestProvider.getLanguage());
            }
            if (language == null) {
                language = LANGUAGE_DEFAULT;
            }

            List<?> items = (List<?>) o;

            for (Object item : items) {
                if (item instanceof TextConstant) {
                    TextConstant constant = (TextConstant) item;
                    if (constant.getLanguage() == language) {
                        return new TranslatedText(constant.getText());
                    }
                    if (constant.getLanguage() == LANGUAGE_DEFAULT) {
                        defaultValue = constant.getText();
                    }
                }
            }
        }
        return new TranslatedText(defaultValue);
    }

    private ServletRequestProvider getRequestProvider() {
        Map<String, ServletRequestProvider> providers = contextProvider.getBeansOfType(ServletRequestProvider.class);
        if (providers.isEmpty()) {
            return null;
        }
        return providers.get(providers.keySet().iterator().next());
    }

}