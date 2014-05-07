package ch.alv.components.core.spring.internal;


import ch.alv.components.core.spring.SecurityContextProvider;
import ch.alv.components.core.utils.StringHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * Default implementation of the {@link ch.alv.components.core.spring.SecurityContextProvider} interface.
 *
 * @since 1.0.0
 */
public class DefaultSecurityContextProvider extends DefaultApplicationContextProvider implements SecurityContextProvider {

    @Override
    public UserDetails getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getDetails() instanceof UserDetails)) {
            return null;
        }
        return (UserDetails) authentication.getDetails();
    }

    @Override
    public boolean hasRole(String role) {
        if (StringHelper.isEmpty(role)) {
            return true;
        }
        UserDetails user = getUser();
        for (GrantedAuthority authority : user.getAuthorities()) {
            if (authority.getAuthority().equalsIgnoreCase(role)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasAnyRole(List<String> roles) {
        if (roles == null || roles.size() == 0) {
            return true;
        }
        UserDetails user = getUser();
        for (GrantedAuthority authority : user.getAuthorities()) {
            if (roles.indexOf(authority.getAuthority()) > -1) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasAllRoles(List<String> roles) {
        if (roles == null || roles.size() == 0) {
            return true;
        }
        UserDetails user = getUser();
        int numberOfMatchedAuthorities = 0;
        for (String role : roles) {
            boolean match = false;
            for (GrantedAuthority authority : user.getAuthorities()) {
                if (role.equals(authority.getAuthority())) {
                    match = true;
                }
            }
            if (match) {
                numberOfMatchedAuthorities++;
            }

        }
        return numberOfMatchedAuthorities == roles.size();
    }
}
