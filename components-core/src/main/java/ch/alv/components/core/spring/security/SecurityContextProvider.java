package ch.alv.components.core.spring.security;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * ApplicationContextAware, enabled to fetch beans
 *
 * @author seco-hrf
 * @since 1.0.0
 */
public interface SecurityContextProvider {

    UserDetails getUser();

    boolean hasRole(String role);

    boolean hasAnyRole(List<String> roles);

    boolean hasAllRoles(List<String> roles);

}
