package ch.alv.components.iam.search;

import ch.alv.components.iam.IamConstant;
import ch.alv.components.web.search.BaseWebValuesProvider;
import ch.alv.components.web.search.WebValuesProvider;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link ch.alv.components.persistence.search.ValuesProvider} for {@link ch.alv.components.iam.model.Application} entities.
 *
 * @since 1.0.0
 */
@Component
public class ApplicationSearchValuesProvider extends BaseWebValuesProvider implements WebValuesProvider {

    @Override
    public Map<String, Object> getValues() {
        Map<String, Object> map = new HashMap<>();
        map.put(IamConstant.PARAM_NAME, getStringValue("name"));
        return map;
    }

}
