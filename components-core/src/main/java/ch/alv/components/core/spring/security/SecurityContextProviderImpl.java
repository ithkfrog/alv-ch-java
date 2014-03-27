package ch.alv.components.core.spring.security;


import ch.alv.components.core.utils.StringHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * Default implementation of the {@link SecurityContextProvider} interface.
 *
 * @since 1.0.0
 */
public class SecurityContextProviderImpl implements SecurityContextProvider {

    @Override
    public UserDetails getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof UserDetails)) {
            return null;
        }
        return (UserDetails) authentication.getPrincipal();
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
        for (GrantedAuthority authority : user.getAuthorities()) {
            if (roles.indexOf(authority.getAuthority()) < 0) {
                return false;
            }
        }
        return true;
    }
}
