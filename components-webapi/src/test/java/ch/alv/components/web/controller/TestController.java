package ch.alv.components.web.controller;

import ch.alv.components.core.spring.ApplicationContextProvider;
import ch.alv.components.web.controller.internal.BaseController;

/**
 * Controller implementation for test cases to test the {@link ch.alv.components.web.controller.internal.BaseController}
 *
 * @since 1.0.0
 */
public class TestController extends BaseController {


    public TestController(ApplicationContextProvider contextProvider) {
        super(contextProvider);
    }
}
