package ch.alv.components.core.search;

import java.util.HashMap;
import java.util.Map;

/**
 * Use an empty values map to avoid null-checks in classes that use implementations of the {@link ValuesProvider} interface.
 *
 * @since 1.0.0
 */
public class EmptyValuesProvider implements ValuesProvider {

    /* (non-Javadoc)
     * @see ch.alv.components.core.search.ValuesProvider#getValues()
     */
    @Override
    public Map<String, Object> getValues() {
        return new HashMap<>();
    }
}
