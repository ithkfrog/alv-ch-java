package ch.alv.components.web.controller;

import ch.alv.components.core.spring.ApplicationContextProvider;
import ch.alv.components.web.controller.internal.BasePersistingController;

/**
 * Controller implementation for test cases to test the {@link ch.alv.components.web.controller.internal.BasePersistingController}
 *
 * @since 1.0.0
 */
public class PersistingController extends BasePersistingController {


    public PersistingController(ApplicationContextProvider contextProvider) {
        super(contextProvider);
    }
}
