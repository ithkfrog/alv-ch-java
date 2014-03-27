package ch.alv.components.iam.service;

import ch.alv.components.iam.model.User;
import ch.alv.components.service.persistence.PersistenceService;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Custom persistence service interface for {@link User} entities.
 *
 * @since 1.0.0
 */
public interface UserService extends PersistenceService<User, String>, UserDetailsService {

    User getCurrentUser();

    boolean hasRole(String role);

    boolean hasAnyRole(String[] roles);

    boolean hasAllRole(String[] roles);

}
