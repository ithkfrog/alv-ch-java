package ch.alv.components.core.spring;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * ApplicationContextAware, enabled to fetch beans
 *
 * @since 1.0.0
 */
public interface SecurityContextProvider extends ApplicationContextProvider {

    UserDetails getUser();

    boolean hasRole(String role);

    boolean hasAnyRole(List<String> roles);

    boolean hasAllRoles(List<String> roles);

}
