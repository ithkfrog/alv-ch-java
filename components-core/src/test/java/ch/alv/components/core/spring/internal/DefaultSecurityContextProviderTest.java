package ch.alv.components.core.spring.internal;

import ch.alv.components.core.spring.SecurityContextProvider;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;


/**
 * Unit tests for the BeanMapper
 *
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/default-security-context-provider-test.xml")
public class DefaultSecurityContextProviderTest {

    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";
    public static final String TEST_PASSWORD = "testPassword";
    public static final String TEST_USER_NAME = "testUserName";
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Resource
    private SecurityContextProvider provider;

    private UserDetails user;

    @Before
    public void init() {
        user = new TestUserDetails();
        Authentication auth = new TestAuthentication(user);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @Test
    public void testGetUser() {
        UserDetails providedUser = provider.getUser();
        assertEquals(user.getPassword(), providedUser.getPassword());
        assertEquals(user.getUsername(), providedUser.getUsername());
        assertEquals(user.getAuthorities(), providedUser.getAuthorities());
        assertEquals(user.isAccountNonExpired(), providedUser.isAccountNonExpired());
        assertEquals(user.isAccountNonLocked(), providedUser.isAccountNonLocked());
        assertEquals(user.isCredentialsNonExpired(), providedUser.isCredentialsNonExpired());
        assertEquals(user.isEnabled(), providedUser.isEnabled());
    }

    @Test
    public void testHasRole() {
        assertTrue(provider.hasRole(null));
        assertTrue(provider.hasRole(ROLE_ADMIN));
        assertFalse(provider.hasRole("nonExistingRole"));
    }

    @Test
    public void testHasAnyRole() {
        assertTrue(provider.hasAnyRole(null));
        assertTrue(provider.hasAnyRole(Arrays.asList("nonExistingRole", ROLE_USER)));
        assertTrue(provider.hasAnyRole(new ArrayList<String>()));
        assertFalse(provider.hasAnyRole(Arrays.asList("nonExistingRole", "anotherNonExistingRole")));
    }

    @Test
    public void testHasAllRoles() {
        assertTrue(provider.hasAllRoles(null));
        assertTrue(provider.hasAllRoles(Arrays.asList(ROLE_ADMIN, ROLE_USER)));
        assertTrue(provider.hasAllRoles(new ArrayList<String>()));
        assertFalse(provider.hasAllRoles(Arrays.asList(ROLE_ADMIN, "nonExistingRole", ROLE_USER)));
    }

    @Test
    public void testNullAuthentication() {
        SecurityContextHolder.getContext().setAuthentication(null);
        assertNull(provider.getUser());
    }

    @Test
    public void testNonUserDetailsDetails() {
        Authentication auth = new TestAuthentication(new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(auth);
        assertNull(provider.getUser());
    }

    public class TestAuthentication implements Authentication {

        private static final long serialVersionUID = 4472323144341006630L;

        private final Object user;

        public TestAuthentication(Object user) {
            this.user = user;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            if (user instanceof UserDetails) {
                return ((UserDetails) user).getAuthorities();
            }
            return null;
        }

        @Override
        public Object getCredentials() {
            if (user instanceof UserDetails) {
                return ((UserDetails) user).getPassword();
            }
            return null;
        }

        @Override
        public Object getDetails() {
            return user;
        }

        @Override
        public Object getPrincipal() {
            if (user instanceof UserDetails) {
                return ((UserDetails) user).getUsername();
            }
            return null;
        }

        @Override
        public boolean isAuthenticated() {
            return true;
        }

        @Override
        public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
            // nothing to do
        }

        @Override
        public String getName() {
            if (user instanceof UserDetails) {
                return ((UserDetails) user).getUsername();
            }
            return null;
        }
    }

    public class TestUserDetails implements UserDetails {

        private static final long serialVersionUID = -2577888018926330288L;

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(ROLE_ADMIN));
            authorities.add(new SimpleGrantedAuthority(ROLE_USER));
            return authorities;
        }

        @Override
        public String getPassword() {
            return TEST_PASSWORD;
        }

        @Override
        public String getUsername() {
            return TEST_USER_NAME;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }

}
