package ch.alv.components.web.controller.internal;

import ch.alv.components.core.beans.ModelItem;
import ch.alv.components.core.spring.ApplicationContextProvider;
import ch.alv.components.web.controller.PostController;
import ch.alv.components.web.endpoint.filter.UnSupportedMethodException;
import ch.alv.components.web.endpoint.filter.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * Controller to handle POST requests with help of the alv-ch-web framework.
 *
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")
public class DefaultPostController extends BasePersistingController implements PostController {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultPostController.class);

    public DefaultPostController(ApplicationContextProvider contextProvider) {
        super(contextProvider);
    }

    @Override
    @RequestMapping(method = RequestMethod.POST, value = "/{moduleName}/{storeName}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object handlePostRequest(HttpServletRequest request,
                                    @PathVariable String moduleName,
                                    @PathVariable String storeName,
                                    @RequestBody String body) throws UnauthorizedException, UnSupportedMethodException {
        runFilters(request, moduleName, storeName);
        ModelItem returnValue = (ModelItem) persist(moduleName, storeName, null, body);
        LOG.debug("POST request: Entity with id '" + returnValue.getId() + "' successfully updated in '" + moduleName + "." + storeName + "'.");
        return returnValue;
    }

    @Override
    @RequestMapping(method = RequestMethod.POST, value = "/{moduleName}/{storeName}/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object handlePostRequest(HttpServletRequest request,
                                    @PathVariable String moduleName,
                                    @PathVariable String storeName,
                                    @PathVariable String id,
                                    @RequestBody String body) throws UnauthorizedException, UnSupportedMethodException {

        runFilters(request, moduleName, storeName);
        Object returnValue = persist(moduleName, storeName, id, body);
        LOG.debug("POST request: Entity with id '" + id + "' successfully updated in '" + moduleName + "." + storeName + "'.");
        return returnValue;
    }

}
