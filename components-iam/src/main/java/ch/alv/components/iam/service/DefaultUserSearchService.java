package ch.alv.components.iam.service;

import ch.alv.components.iam.IamConstant;
import ch.alv.components.iam.model.Role;
import ch.alv.components.iam.model.User;
import ch.alv.components.iam.repository.UserRepository;
import ch.alv.components.iam.search.UserSearchValuesProvider;
import ch.alv.components.service.internal.DefaultSearchService;
import ch.alv.components.web.search.WebSearchValuesProvider;
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
 * Default implementation of the {@link UserSearchService} interface.
 *
 * @since 1.0.0
 */
@Service(value = "iam.user.service")
public class DefaultUserSearchService extends DefaultSearchService<User, String> implements UserSearchService {

    @Inject
    public DefaultUserSearchService(UserRepository repo) {
        super(repo);
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
        WebSearchValuesProvider provider = new UserSearchValuesProvider();
        Map<String, String[]> sourceMap = new HashMap<>();
        sourceMap.put(IamConstant.PARAM_USER_NAME, new String[]{username});
        provider.setSource(sourceMap);
        List<User> result = getRepository().find(provider, "userByUsernameSearch").getContent();
        if (result.size() > 0) {
            return result.get(0);
        } else {
            return null;
        }
    }
}