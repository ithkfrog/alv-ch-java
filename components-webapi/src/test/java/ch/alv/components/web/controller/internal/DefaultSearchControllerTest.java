package ch.alv.components.web.controller.internal;

import ch.alv.components.web.WebConstant;
import ch.alv.components.web.controller.NoSuchValuesProviderException;
import ch.alv.components.web.controller.SearchController;
import ch.alv.components.web.endpoint.filter.UnauthorizedException;
import ch.alv.components.web.mock.TestDto;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import static org.junit.Assert.assertNotNull;

/**
 * Test cases for the {@link ch.alv.components.web.controller.internal.BaseController} class.
 *
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/base-controller-test.xml")
public class DefaultSearchControllerTest {

    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";
    public static final String TEST_PASSWORD = "testPassword";
    public static final String TEST_USER_NAME = "testUserName";

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Resource
    private SearchController controller;

    @Resource
    private MockHttpServletRequest request;

    @Before
    public void init() {
        UserDetails user = new TestUserDetails();
        Authentication auth = new TestAuthentication(user);
        SecurityContextHolder.getContext().setAuthentication(auth);
        request.setMethod("GET");
    }

    @Test
    public void testGetAll() throws UnauthorizedException, NoSuchValuesProviderException {
        request.setMethod("GET");
        request.removeAllParameters();
        ResponseEntity response = (ResponseEntity) controller.handleGetRequest(request, "searchTestModule", "searchTestStore");
        assertNotNull(response);
    }

    @Test
    public void testGetById() throws UnauthorizedException, NoSuchValuesProviderException {
        request.setMethod("GET");
        request.removeAllParameters();
        TestDto response = (TestDto) controller.handleGetRequest(request, "searchTestModule", "searchTestStore", "b");
        assertNotNull(response);
        assertEquals("b", response.getId());
    }

    @Test
    public void testGetByNullId() throws UnauthorizedException, NoSuchValuesProviderException {
        request.setMethod("GET");
        request.removeAllParameters();
        ResponseEntity response = (ResponseEntity) controller.handleGetRequest(request, "searchTestModule", "searchTestStore", null);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetWithEndpointCustomSearch() throws UnauthorizedException, NoSuchValuesProviderException {
        request.setMethod("GET");
        request.removeAllParameters();
        request.addParameter(WebConstant.PARAM_NAME_SEARCH, "testCustomSearch");
        ResponseEntity response = (ResponseEntity) controller.handleGetRequest(request, "searchTestModuleWithDefaultSearch", "searchTestStore");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(3, ((Page) response.getBody()).getNumber());
    }

    @Test
    public void testGetWithEndpointDefaultSearch() throws UnauthorizedException, NoSuchValuesProviderException {
        request.setMethod("GET");
        request.removeAllParameters();
        ResponseEntity response = (ResponseEntity) controller.handleGetRequest(request, "searchTestModuleWithDefaultSearch", "searchTestStore");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(3, ((Page) response.getBody()).getNumber());
    }

    @Test
    public void testPost() throws UnauthorizedException, NoSuchValuesProviderException {
        request.setMethod("POST");
        request.removeAllParameters();
        ResponseEntity response = (ResponseEntity) controller.handlePostRequest(request, "searchTestModule", "searchTestStore", "b");
        assertEquals(HttpStatus.METHOD_NOT_ALLOWED, response.getStatusCode());
        assertEquals("Search for post method is not implemented yet.", response.getBody());
    }

    public class TestAuthentication implements Authentication {

        private static final long serialVersionUID = -2694650641896804842L;

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

        private static final long serialVersionUID = 6049203065667172423L;

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
