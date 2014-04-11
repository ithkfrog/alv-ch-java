package ch.alv.components.web.mapper;

import ch.alv.components.core.enums.Language;
import ch.alv.components.core.utils.StringHelper;
import ch.alv.components.data.jpa.MultiLanguageText;
import ch.alv.components.web.context.ServletRequestProviderImpl;
import ch.alv.components.web.dto.TranslatedText;
import org.dozer.BeanFactory;

import javax.annotation.Resource;

/**
 * Converts {@link MultiLanguageText} into {@link TranslatedText}.
 *
 * @since 1.0.0
 */
public class TranslatedTextFactory implements BeanFactory {

    private static final Language LANGUAGE_DEFAULT = Language.GERMAN;

    @Resource
    private ServletRequestProviderImpl requestProvider;

    @Override
    public Object createBean(Object o, Class<?> aClass, String s) {
        if (o instanceof MultiLanguageText) {
            Language language = null;
            if (StringHelper.isNotEmpty(requestProvider.getLanguage())) {
                language = Language.getByCode(requestProvider.getLanguage());
            }
            if (language == null) {
                language = LANGUAGE_DEFAULT;
            }
            return new TranslatedText(((MultiLanguageText) o).getValueFor(language));

        }
        return null;
    }

}