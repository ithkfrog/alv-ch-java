package ch.alv.components.web.mapper;

import ch.alv.components.core.enums.Language;
import ch.alv.components.core.utils.StringHelper;
import ch.alv.components.persistence.model.MultiLanguageText;
import ch.alv.components.web.context.ServletRequestProviderImpl;
import ch.alv.components.web.dto.TranslatedText;
import org.dozer.BeanFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * Converts {@link MultiLanguageText} into {@link TranslatedText}.
 *
 * @since 1.0.0
 */
public class TranslatedTextFactory implements BeanFactory {

    private static final Language LANGUAGE_DEFAULT = Language.GERMAN;

    private static final Logger LOG = LoggerFactory.getLogger(TranslatedTextFactory.class);

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