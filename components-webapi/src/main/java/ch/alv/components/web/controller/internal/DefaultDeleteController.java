package ch.alv.components.web.controller.internal;

import ch.alv.components.core.utils.StringHelper;
import ch.alv.components.web.controller.DeleteController;
import ch.alv.components.web.endpoint.filter.UnauthorizedException;
import ch.alv.components.web.exception.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller to handle DELETE requests with help of the alv-ch-web framework.
 *
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")
public class DefaultDeleteController extends BasePersistingController implements DeleteController {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultDeleteController.class);

    @Override
    @RequestMapping(method = RequestMethod.DELETE, value = "/{moduleName}/{storeName}({id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object handleDeleteRequest(HttpServletRequest request,
                                      @PathVariable String moduleName,
                                      @PathVariable String storeName,
                                      @PathVariable String id) throws UnauthorizedException {
        if (StringHelper.isEmpty(id)) {
            throw new BadRequestException("The id must not be null!");
        }
        runFilters(request, moduleName, storeName);
        getService(getEndpoint(moduleName, storeName)).delete(id);
        LOG.debug("DELETE request: Entity with id '" + id + "' successfully deleted in '" + moduleName + "." + storeName + "'.");
        return new ResponseEntity<>("Entity successfully deleted", HttpStatus.OK);
    }
}
