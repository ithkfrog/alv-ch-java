package ch.alv.components.core.search;

import javax.annotation.PostConstruct;

/**
 * The search default configuration.
 *
 * @since 1.0.0
 */
public class DefaultSearch extends SearchImpl {

    @PostConstruct
    private void init() {
        getProjections().add(new Projection("a", "*"));
    }

}
