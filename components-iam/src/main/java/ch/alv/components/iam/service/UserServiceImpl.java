package ch.alv.components.iam.service;

import ch.alv.components.core.utils.Md5Encoder;
import ch.alv.components.iam.IamConstant;
import ch.alv.components.iam.model.Role;
import ch.alv.components.iam.model.User;
import ch.alv.components.iam.repository.UserRepository;
import ch.alv.components.iam.search.UserSearchValuesProvider;
import ch.alv.components.service.persistence.PersistenceServiceImpl;
import ch.alv.components.web.search.WebValuesProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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
public class UserServiceImpl extends PersistenceServiceImpl<User, String, UserRepository> implements UserService {

    @Inject
    public UserServiceImpl(UserRepository repo) {
        super(repo);
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof UserDetails)) {
            return null;
        }
        return (User) authentication.getPrincipal();
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
    public boolean hasAllRole(String[] roles) {
        for (String role : roles) {
            if (!hasRole(role)) {
                return false;
            }
        }
        return true;
    }

    @Override
    @Transactional
    public User save(User item) {
        if (!StringUtils.isEmpty(item.getPassword())) {
            item.setPassword(Md5Encoder.convertToMd5(item.getPassword()));
        }
        return getRepository().save(item);
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        WebValuesProvider provider = new UserSearchValuesProvider();
        Map<String, String[]> sourceMap = new HashMap<>();
        sourceMap.put(IamConstant.PARAM_USER_NAME, new String[]{username});
        provider.setSource(sourceMap);
        List<User> result = getRepository().findWithDefaultSearch(provider).getContent();
        if (result.size() > 0) {
            return result.get(0);
        } else {
            return null;
        }
    }
}
