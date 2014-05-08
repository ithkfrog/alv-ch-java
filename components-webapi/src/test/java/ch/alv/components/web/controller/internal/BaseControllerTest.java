package ch.alv.components.web.controller.internal;

import ch.alv.components.web.controller.TestController;
import ch.alv.components.web.dto.DtoFactory;
import ch.alv.components.web.endpoint.Endpoint;
import ch.alv.components.web.endpoint.filter.UnSupportedMethodException;
import ch.alv.components.web.endpoint.filter.UnauthorizedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.data.domain.Pageable;
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

import static org.junit.Assert.*;

/**
 * Test cases for the {@link ch.alv.components.web.controller.internal.BaseController} class.
 *
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/base-controller-test.xml")
public class BaseControllerTest {

    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";
    public static final String TEST_PASSWORD = "testPassword";
    public static final String TEST_USER_NAME = "testUserName";

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Resource
    private TestController controller;

    @Resource
    private MockHttpServletRequest request;

    @Before
    public void init() {
        UserDetails user = new TestUserDetails();
        Authentication auth = new TestAuthentication(user);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @Test
    public void testGetDtoFactory() {
        assertNotNull(controller.getDtoFactory());
        assertNotNull(controller.getDtoFactory());
    }

    @Test
    public void testGetEndpoint() {
        Endpoint endpoint = controller.getEndpoint("filterTestModule", "filterTestStore");
        assertNotNull(endpoint);
        assertEquals("filterTestModule", endpoint.getModuleName());
        assertEquals("filterTestStore", endpoint.getStoreName());
    }

    @Test
    public void testGetDefaultPage() {
        assertEquals(3, controller.getDefaultPage());
    }

    @Test
    public void testGetDefaultPageSize() {
        assertEquals(5, controller.getDefaultPageSize());
    }

    @Test
    public void testGetBean() {
        DtoFactory factory = controller.getBean("dtoFactory");
        assertNotNull(factory);
    }

    @Test
    public void testCreateProvider() {
        // ValuesProvider provider = controller.createProvider(StringWebValuesProvider.class);
        // assertNotNull(provider);
    }

    @Test
    public void testCreateProviderFail() {
        //exception.expect(IllegalStateException.class);
        // ValuesProvider provider = controller.createProvider(null);
    }

    @Test
    public void testCreatePageable() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        Pageable pageable = controller.createPageable(request);
        assertEquals(3, pageable.getPageNumber());
        assertEquals(5, pageable.getPageSize());
        assertEquals(15, pageable.getOffset());
        assertNull(pageable.getSort());
    }

    @Test
    public void testRunFilters() throws UnauthorizedException, UnSupportedMethodException {
        request.setMethod("GET");
        controller.runFilters(request, "securedFilterTestModule", "securedFilterTestStore");
        // everything's fine with no exception...
    }

    public class TestAuthentication implements Authentication {

        private static final long serialVersionUID = 1246715431928028303L;

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
            return false;
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

        private static final long serialVersionUID = -2871395175081202534L;

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
