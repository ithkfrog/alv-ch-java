package ch.alv.components.web.controller;

import ch.alv.components.web.endpoint.filter.UnauthorizedException;

import javax.servlet.http.HttpServletRequest;

/**
 * Interface for "delete" controllers.
 *
 * @since 1.0.0
 */
public interface DeleteController {

    Object handleDeleteRequest(HttpServletRequest request,
                            String moduleName,
                            String storeName,
                            String id) throws UnauthorizedException;

}
