package ch.alv.components.iam.service;

import ch.alv.components.iam.model.Role;
import ch.alv.components.iam.model.User;
import ch.alv.components.iam.repository.UserRepository;
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
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Test cases for the {@link ch.alv.components.service.internal.ServiceRegistry} class.
 *
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/default-user-service-test-context.xml")
public class DefaultUserServiceTest {

    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";
    public static final String TEST_PASSWORD = "testPassword";
    public static final String TEST_USER_NAME = "testUserName";

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Resource
    private UserSearchService service;

    @Resource
    private UserRepository repository;

    private User user;
    private Authentication auth;

    @Before
    public void initAuthentication() {
        user = new TestUserDetails();
        user.setId("user_id");
        List<Role> roles = new ArrayList<>();
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        Role role2 = new Role();
        role2.setName("ROLE_TEST");
        roles.add(role);
        roles.add(role2);
        user.setRoles(roles);
        auth = new TestAuthentication(user);
    }

    @Test
    public void testGetCurrentUser() {
        SecurityContextHolder.getContext().setAuthentication(auth);
        User currentUser = service.getCurrentUser();
        assertEquals(user.getId(), currentUser.getId());
        assertEquals(2, currentUser.getAuthorities().size());
    }

    @Test
         public void testGetCurrentUserNoAuthentication() {
        SecurityContextHolder.getContext().setAuthentication(null);
        assertNull(service.getCurrentUser());
    }

    @Test
    public void testGetCurrentUserNoUserDetail() {
        SecurityContextHolder.getContext().setAuthentication(new TestAuthentication("UserDetails"));
        assertNull(service.getCurrentUser());
    }

    @Test
    public void testHasRole() {
        SecurityContextHolder.getContext().setAuthentication(auth);
        assertTrue(service.hasRole("ROLE_ADMIN"));
        assertFalse(service.hasRole("ROLE_NONE"));
    }

    @Test
    public void testHasAnyRole() {
        SecurityContextHolder.getContext().setAuthentication(auth);
        assertTrue(service.hasAnyRole(new String[]{"ROLE", "ROLE_ADMIN"}));
        assertFalse(service.hasAnyRole(new String[]{"ROLE_NONE", "ROLE_ADM"}));
    }

    @Test
    public void testHasAllRoles() {
        SecurityContextHolder.getContext().setAuthentication(auth);
        assertTrue(service.hasAllRoles(new String[]{"ROLE_TEST", "ROLE_ADMIN"}));
        assertFalse(service.hasAllRoles(new String[]{"ROLE_TEST", "ROLE_ADMIN", "ROLE_NONE"}));
    }

    @Test
    public void testLoadUserByUsername() {
        SecurityContextHolder.getContext().setAuthentication(auth);
        User user = new User();
        user.setUsername("testUser");
        user.setFirstName("testFirstName");
        user.setLastName("testLastName");
        repository.save(user);
        assertEquals("testUser", service.loadUserByUsername("testUser").getUsername());
        assertNull("testUser", service.loadUserByUsername("unknownUser"));
    }


    public class TestAuthentication implements Authentication {

        private static final long serialVersionUID = -3498912841097406310L;

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

    public class TestUserDetails extends User implements UserDetails {

        private static final long serialVersionUID = -3589771667894556779L;

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
