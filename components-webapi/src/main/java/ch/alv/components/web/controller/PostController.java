package ch.alv.components.web.controller;

import ch.alv.components.web.endpoint.filter.UnSupportedMethodException;
import ch.alv.components.web.endpoint.filter.UnauthorizedException;

import javax.servlet.http.HttpServletRequest;

/**
 * Interface for "post" controllers.
 *
 * @since 1.0.0
 */
public interface PostController {

    Object handlePostRequest(HttpServletRequest request,
                            String moduleName,
                            String storeName,
                            String body) throws UnauthorizedException, UnSupportedMethodException;

    Object handlePostRequest(HttpServletRequest request,
                            String moduleName,
                            String storeName,
                            String id,
                            String body) throws UnauthorizedException, UnSupportedMethodException;

}
