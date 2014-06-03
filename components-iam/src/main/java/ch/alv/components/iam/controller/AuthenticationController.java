package ch.alv.components.iam.controller;

import ch.alv.components.core.beans.mapper.BeanMapper;
import ch.alv.components.core.spring.SecurityContextProvider;
import ch.alv.components.iam.dto.CurrentUserDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Endpoint to fetch data about the current user.
 *
 * @since 1.0.0
 */
@RestController
public class AuthenticationController {

    @Resource
    private BeanMapper mapper;

    @Resource
    private SecurityContextProvider securityContextProvider;

    @RequestMapping(value = "/iam/authenticate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CurrentUserDto handleRequest() {
        return mapper.mapObject(securityContextProvider.getUser(), CurrentUserDto.class);
    }

}
