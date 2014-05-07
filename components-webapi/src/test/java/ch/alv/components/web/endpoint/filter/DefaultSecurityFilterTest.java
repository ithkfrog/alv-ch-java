package ch.alv.components.web.endpoint.filter;

import ch.alv.components.web.endpoint.filter.internal.DefaultSecurityFilter;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
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

import static org.junit.Assert.assertEquals;

/**
 * Test cases for the {@link ch.alv.components.web.endpoint.filter.internal.DefaultEndpointHttpMethodFilter} unit.
 *
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/endpoint-security-filter-test.xml")
public class DefaultSecurityFilterTest {

    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";
    public static final String TEST_PASSWORD = "testPassword";
    public static final String TEST_USER_NAME = "testUserName";

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Resource
    private DefaultSecurityFilter filter;

    private UserDetails user;

    @Before
    public void init() {
        user = new TestUserDetails();
        Authentication auth = new TestAuthentication(user);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @Test
    public void testGetAccess() {
        SecurityFilterResult result = filter.doFilter(new MockHttpServletRequest(), "filterTestModule", "filterTestStore");
        assertEquals(SecurityFilterResult.OK, result.getResult());
        assertEquals("Access granted.", result.getMessage());
    }

    @Test
    public void testPostAccess() {
        SecurityFilterResult result = filter.doFilter(new MockHttpServletRequest("POST", "baseRequestUri?language=fr"), "filterTestModule", "filterTestStore");
        assertEquals(SecurityFilterResult.OK, result.getResult());
        assertEquals("Access granted.", result.getMessage());
    }

    @Test
    public void testPutAccess() {
        SecurityFilterResult result = filter.doFilter(new MockHttpServletRequest("PUT", "baseRequestUri"), "filterTestModule", "filterTestStore");
        assertEquals(SecurityFilterResult.OK, result.getResult());
        assertEquals("Access granted.", result.getMessage());
    }

    @Test
    public void testDeleteAccess() {
        SecurityFilterResult result = filter.doFilter(new MockHttpServletRequest("DELETE", "baseRequestUri"), "filterTestModule", "filterTestStore");
        assertEquals(SecurityFilterResult.OK, result.getResult());
        assertEquals("Access granted.", result.getMessage());
    }

    @Test
    public void testGetAccessFail() {
        SecurityFilterResult result = filter.doFilter(new MockHttpServletRequest(), "securedFilterTestModule", "securedFilterTestStore");
        assertEquals(SecurityFilterResult.NOK, result.getResult());
        assertEquals("Access denied to store 'securedFilterTestModule:securedFilterTestStore' for user 'testUserName'!", result.getMessage());
    }

    @Test
    public void testPostAccessFail() {
        SecurityFilterResult result = filter.doFilter(new MockHttpServletRequest("POST", "baseRequestUri"), "securedFilterTestModule", "securedFilterTestStore");
        assertEquals(SecurityFilterResult.NOK, result.getResult());
        assertEquals("Access denied to store 'securedFilterTestModule:securedFilterTestStore' for user 'testUserName'!", result.getMessage());
    }

    @Test
    public void testPutAccessFail() {
        SecurityFilterResult result = filter.doFilter(new MockHttpServletRequest("PUT", "baseRequestUri"), "securedFilterTestModule", "securedFilterTestStore");
        assertEquals(SecurityFilterResult.NOK, result.getResult());
        assertEquals("Access denied to store 'securedFilterTestModule:securedFilterTestStore' for user 'testUserName'!", result.getMessage());
    }

    @Test
    public void testDeleteAccessFail() {
        SecurityFilterResult result = filter.doFilter(new MockHttpServletRequest("DELETE", "baseRequestUri"), "securedFilterTestModule", "securedFilterTestStore");
        assertEquals(SecurityFilterResult.NOK, result.getResult());
        assertEquals("Access denied to store 'securedFilterTestModule:securedFilterTestStore' for user 'testUserName'!", result.getMessage());
    }

    @Test
    public void testEmptyMethodAccessFail() {
        SecurityFilterResult result = filter.doFilter(new MockHttpServletRequest("", "baseRequestUri"), "securedFilterTestModule", "securedFilterTestStore");
        assertEquals(SecurityFilterResult.NOK, result.getResult());
        assertEquals("Access denied to store 'securedFilterTestModule:securedFilterTestStore' for user 'testUserName'!", result.getMessage());
    }

    @Test
    public void testNonExistingEndpoint() {
        exception.expect(IllegalStateException.class);
        exception.expectMessage("No endpoint for store 'test/test' found.");
        filter.doFilter(new MockHttpServletRequest(), "test", "test");
    }

    public class TestAuthentication implements Authentication {

        private static final long serialVersionUID = 8581629722710915371L;

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

        private static final long serialVersionUID = -4959976202913777497L;

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
