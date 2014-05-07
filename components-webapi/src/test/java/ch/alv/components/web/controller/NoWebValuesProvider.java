package ch.alv.components.web.controller;

import ch.alv.components.core.search.SearchValuesProvider;
import ch.alv.components.core.search.internal.BaseSearchValuesProvider;

/**
 * Implementation of a ValuesProvider that is not a WebValuesProvider.
 *
 * @since 1.0.0
 */
public class NoWebValuesProvider extends BaseSearchValuesProvider implements SearchValuesProvider {

    @Override
    protected void putData() {
        // not required
    }
}
