package ch.alv.components.iam.service;

import ch.alv.components.core.search.MapBasedValuesProvider;
import ch.alv.components.core.search.ValuesProvider;
import ch.alv.components.data.repository.Repository;
import ch.alv.components.iam.IamConstant;
import ch.alv.components.iam.model.Role;
import ch.alv.components.iam.model.User;
import ch.alv.components.service.data.DefaultDataService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Default implementation of the {@link UserService} interface.
 *
 * @since 1.0.0
 */
@Service(value = "iam.user.service")
public class DefaultUserService extends DefaultDataService<String> implements UserService {

    @Inject
    public DefaultUserService(Repository<String> repository) {
        super(repository);
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getDetails() instanceof UserDetails)) {
            return null;
        }
        return (User) authentication.getDetails();
    }

    @Override
    public boolean hasRole(String role) {
        List<Role> roles = getCurrentUser().getRoles();
        for (Role currentRole : roles) {
            if (currentRole.getName().equalsIgnoreCase(role)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasAnyRole(String[] roles) {
        for (String role : roles) {
            if (hasRole(role)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasAllRoles(String[] roles) {
        for (String role : roles) {
            if (!hasRole(role)) {
                return false;
            }
        }
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
            Map<String, String[]> values = new HashMap<>();
            values.put(IamConstant.PARAM_USER_NAME, new String[] {username});
            ValuesProvider provider = new MapBasedValuesProvider(values);
            List<User> result = repository.find("userByUsernameQuery", provider, User.class);
            if (result.size() > 0) {
                return result.get(0);
            } else {
                return null;
            }
    }
}
