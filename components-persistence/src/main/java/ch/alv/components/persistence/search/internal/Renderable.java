package ch.alv.components.persistence.search.internal;

import ch.alv.components.persistence.search.ValuesProvider;

/**
 * DynamicSearch implementation that know to render itself should implement this interface.
 *
 * @since 1.0.0
 */
public interface Renderable {

    String render();

    String render(ValuesProvider valuesProvider);

}
