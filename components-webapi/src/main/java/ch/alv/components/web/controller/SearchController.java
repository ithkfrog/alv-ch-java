package ch.alv.components.web.controller;

import ch.alv.components.web.endpoint.filter.UnSupportedMethodException;
import ch.alv.components.web.endpoint.filter.UnauthorizedException;

import javax.servlet.http.HttpServletRequest;

/**
 * Interface for search controllers.
 *
 * @since 1.0.0
 */
public interface SearchController {

    Object handleGetRequest(HttpServletRequest request,
                            String moduleName,
                            String storeName) throws UnauthorizedException, UnSupportedMethodException, NoSuchValuesProviderException;

    Object handleGetRequest(HttpServletRequest request,
                            String moduleName,
                            String storeName,
                            String id) throws UnSupportedMethodException, UnauthorizedException;

    Object handlePostRequest(HttpServletRequest request,
                             String moduleName,
                             String storeName,
                             String body) throws UnauthorizedException, UnSupportedMethodException, NoSuchValuesProviderException;

}
