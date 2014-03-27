package ch.alv.components.web.controller;

import javax.servlet.http.HttpServletRequest;

/**
 * Interface for dynamic WebApi controllers
 *
 * @since 1.0.0
 */
public interface FrontController {

    Object handleRequest(HttpServletRequest request,
                         String moduleName,
                         String storeName,
                         String body);

}
