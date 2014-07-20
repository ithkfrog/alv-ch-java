package ch.alv.components.web.api.controller;

import ch.alv.components.service.ServiceLayerException;
import ch.alv.components.web.WebLayerException;
import ch.alv.components.web.api.filter.HttpRequestFilterException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * A http method based, request dispatching controller.
 *
 * @since 1.0.0
 */
public interface FrontController {

    Object handleGetRequest(HttpServletRequest request) throws HttpRequestFilterException, ServiceLayerException;

    Object handlePostRequest(HttpServletRequest request) throws HttpRequestFilterException, IOException, WebLayerException, ServiceLayerException;

    Object handlePutRequest(HttpServletRequest request) throws HttpRequestFilterException, IOException, WebLayerException, ServiceLayerException;

    Object handleDeleteRequest(HttpServletRequest request) throws HttpRequestFilterException, ServiceLayerException, WebLayerException;

}
