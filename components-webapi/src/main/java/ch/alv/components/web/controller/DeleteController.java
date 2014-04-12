package ch.alv.components.web.controller;

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
                            String id);

}
