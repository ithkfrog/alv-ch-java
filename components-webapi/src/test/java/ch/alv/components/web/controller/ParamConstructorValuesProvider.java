package ch.alv.components.web.controller;

import ch.alv.components.core.search.SearchValuesProvider;
import ch.alv.components.core.search.internal.BaseSearchValuesProvider;

/**
 * Mock extension of the {@link BaseSearchValuesProvider} class.
 *
 * @since 1.0.0
 */
public class ParamConstructorValuesProvider extends BaseSearchValuesProvider implements SearchValuesProvider {

    private String param;

    public ParamConstructorValuesProvider(String param) {
        this.param = param;
    }

    @Override
    protected void putData() {
        // not required
    }
}
