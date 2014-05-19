package ch.alv.components.web.controller;

import ch.alv.components.service.ServiceLayerException;
import ch.alv.components.web.WebConstant;
import ch.alv.components.web.WebLayerException;
import ch.alv.components.web.dto.DtoFactoryException;
import ch.alv.components.web.endpoint.filter.UnSupportedMethodException;
import ch.alv.components.web.endpoint.filter.UnauthorizedException;
import ch.alv.components.web.exception.BadRequestException;
import ch.alv.components.web.mock.MockDto;
import ch.alv.components.web.mock.MockEntity;
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
 * Test cases for the {@link ch.alv.components.web.controller.DefaultWebApiController} class.
 *
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/default-web-api-controller-test-context.xml")
public class DefaultWebApiControllerTest {

    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";
    public static final String TEST_PASSWORD = "testPassword";
    public static final String TEST_USER_NAME = "testUserName";
    public static final String MODULE_NAME = "testModule";
    public static final String STORE_NAME = "testStore";

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Resource
    private DefaultWebApiController controller;

    @Resource
    private MockHttpServletRequest request;

    @Before
    public void init() {
        UserDetails user = new TestUserDetails();
        Authentication auth = new TestAuthentication(user);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @Test
    public void testGetAll() throws NoSuchValuesProviderException, ServiceLayerException, UnSupportedMethodException, UnauthorizedException, WebLayerException {
        request.setMethod("GET");
        request.removeAllParameters();
        ResponseEntity response = (ResponseEntity) controller.handleGetRequest(request, MODULE_NAME, STORE_NAME);
        assertNotNull(response);
    }


    @Test
    public void testGetById() throws UnauthorizedException, NoSuchValuesProviderException, ServiceLayerException, UnSupportedMethodException, WebLayerException {
        request.setMethod("GET");
        request.removeAllParameters();
        //request.setRequestURI("/testModule/testStore/testId");
        MockDto response = (MockDto) controller.handleGetRequest(request, MODULE_NAME, STORE_NAME, "testId");
        assertNotNull(response);
        assertEquals("testId", response.getId());
    }

    @Test
    public void testGetByNullId() throws UnauthorizedException, NoSuchValuesProviderException, ServiceLayerException, UnSupportedMethodException, WebLayerException {
        request.setMethod("GET");
        request.removeAllParameters();
        ResponseEntity response = (ResponseEntity) controller.handleGetRequest(request, MODULE_NAME, STORE_NAME, null);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetWithEndpointCustomSearch() throws UnauthorizedException, NoSuchValuesProviderException, ServiceLayerException, UnSupportedMethodException, WebLayerException {
        request.setMethod("GET");
        request.removeAllParameters();
        request.addParameter(WebConstant.PARAM_NAME_SEARCH, "testCustomSearch");
        ResponseEntity response = (ResponseEntity) controller.handleGetRequest(request, MODULE_NAME, STORE_NAME);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(0, ((Page) response.getBody()).getNumber());
    }

    @Test
    public void testGetWithEndpointDefaultSearch() throws UnauthorizedException, NoSuchValuesProviderException, ServiceLayerException, UnSupportedMethodException, WebLayerException {
        request.setMethod("GET");
        request.removeAllParameters();
        ResponseEntity response = (ResponseEntity) controller.handleGetRequest(request, MODULE_NAME, STORE_NAME);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(0, ((Page) response.getBody()).getNumber());
    }

    @Test
    public void testGetWithNoEndpointDefaultSearch() throws UnauthorizedException, NoSuchValuesProviderException, ServiceLayerException, UnSupportedMethodException, WebLayerException {
        request.setMethod("GET");
        request.removeAllParameters();
        ResponseEntity response = (ResponseEntity) controller.handleGetRequest(request, "noDefaultSearchModule", "noDefaultSearchStore");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(0, ((Page) response.getBody()).getNumber());
    }

    @Test
    public void testGetWithNoSearchNameAndNoEndpointDefaultSearch() throws UnauthorizedException, NoSuchValuesProviderException, ServiceLayerException, UnSupportedMethodException, WebLayerException {
        request.setMethod("GET");
        request.removeAllParameters();
        request.addParameter(WebConstant.PARAM_NAME_SEARCH, "testSearch");
        ResponseEntity response = (ResponseEntity) controller.handleGetRequest(request, "noDefaultSearchModule", "noDefaultSearchStore");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(0, ((Page) response.getBody()).getNumber());
    }

    @Test
    public void testPostCreate() throws UnauthorizedException, ServiceLayerException, UnSupportedMethodException, WebLayerException {
        request.setMethod("POST");
        request.removeAllParameters();
        MockEntity response = (MockEntity) controller.handlePostRequest(request, MODULE_NAME, STORE_NAME, "{ \"version\": 55}");
        assertNotNull(response.getId());
        assertEquals((Integer) 55, response.getVersion());
    }

    @Test
    public void testPostUpdate() throws UnauthorizedException, ServiceLayerException, UnSupportedMethodException, WebLayerException {
        request.setMethod("POST");
        request.removeAllParameters();
        MockEntity response = (MockEntity) controller.handlePostRequest(request, MODULE_NAME, STORE_NAME, "{ \"id\": \"postUpdateId\", \"version\": 55}");
        assertEquals("postUpdateId", response.getId());
        assertEquals((Integer) 55, response.getVersion());
    }

    @Test
    public void testPostUpdateFail() throws UnauthorizedException, ServiceLayerException, UnSupportedMethodException, WebLayerException {
        request.setMethod("POST");
        request.removeAllParameters();
        exception.expect(DtoFactoryException.class);
        exception.expectMessage("Error while creating Dto of type ch.alv.components.web.mock.MockDto from requestBody '{ \"id\": \"postUpdateId\" \"version\": 55}'.");
        controller.handlePostRequest(request, MODULE_NAME, STORE_NAME, "{ \"id\": \"postUpdateId\" \"version\": 55}");
    }

    @Test
    public void testPostCreateWithId() throws UnauthorizedException, ServiceLayerException, UnSupportedMethodException, WebLayerException {
        request.setMethod("POST");
        request.removeAllParameters();
        MockEntity response = (MockEntity) controller.handlePostRequest(request, MODULE_NAME, STORE_NAME, "anotherPostTestId", "{ \"version\": 55}");
        assertEquals("anotherPostTestId", response.getId());
        assertEquals((Integer) 55, response.getVersion());
    }

    @Test
    public void testPostUpdateWithId() throws UnauthorizedException, ServiceLayerException, UnSupportedMethodException, WebLayerException {
        request.setMethod("POST");
        request.removeAllParameters();
        MockEntity response = (MockEntity) controller.handlePostRequest(request, MODULE_NAME, STORE_NAME, "anotherPostTestId", "{ \"version\": 55}");
        assertEquals("anotherPostTestId", response.getId());
        assertEquals((Integer) 55, response.getVersion());
    }

    @Test
    public void testPostUpdateFailWithId() throws UnauthorizedException, ServiceLayerException, UnSupportedMethodException, WebLayerException {
        request.setMethod("POST");
        request.removeAllParameters();
        exception.expect(DtoFactoryException.class);
        exception.expectMessage("Error while creating Dto of type ch.alv.components.web.mock.MockDto from requestBody '{ \"version\": 55,}'.");
        controller.handlePostRequest(request, MODULE_NAME, STORE_NAME, "{ \"version\": 55,}");
    }

    @Test
    public void testPutCreate() throws UnauthorizedException, ServiceLayerException, UnSupportedMethodException, WebLayerException {
        request.setMethod("PUT");
        request.removeAllParameters();
        MockEntity response = (MockEntity) controller.handlePutRequest(request, MODULE_NAME, STORE_NAME, "{ \"version\": 55}");
        assertNotNull(response.getId());
        assertEquals((Integer) 55, response.getVersion());
    }

    @Test
    public void testPutUpdate() throws UnauthorizedException, ServiceLayerException, UnSupportedMethodException, WebLayerException {
        request.setMethod("PUT");
        request.removeAllParameters();
        MockEntity response = (MockEntity) controller.handlePutRequest(request, MODULE_NAME, STORE_NAME, "{ \"id\": \"postUpdateId\", \"version\": 55}");
        assertEquals("postUpdateId", response.getId());
        assertEquals((Integer) 55, response.getVersion());
    }

    @Test
    public void testPutUpdateFail() throws UnauthorizedException, ServiceLayerException, UnSupportedMethodException, WebLayerException {
        request.setMethod("PUT");
        request.removeAllParameters();
        exception.expect(DtoFactoryException.class);
        exception.expectMessage("Error while creating Dto of type ch.alv.components.web.mock.MockDto from requestBody '{ \"id\": \"postUpdateId\" \"version\": 55}'.");
        controller.handlePutRequest(request, MODULE_NAME, STORE_NAME, "{ \"id\": \"postUpdateId\" \"version\": 55}");
    }

    @Test
    public void testPutCreateWithId() throws UnauthorizedException, ServiceLayerException, UnSupportedMethodException, WebLayerException {
        request.setMethod("PUT");
        request.removeAllParameters();
        MockEntity response = (MockEntity) controller.handlePutRequest(request, MODULE_NAME, STORE_NAME, "anotherPostTestId", "{ \"version\": 55}");
        assertEquals("anotherPostTestId", response.getId());
        assertEquals((Integer) 55, response.getVersion());
    }

    @Test
    public void testPutUpdateWithId() throws UnauthorizedException, ServiceLayerException, UnSupportedMethodException, WebLayerException {
        request.setMethod("PUT");
        request.removeAllParameters();
        MockEntity response = (MockEntity) controller.handlePutRequest(request, MODULE_NAME, STORE_NAME, "anotherPostTestId", "{ \"version\": 55}");
        assertEquals("anotherPostTestId", response.getId());
        assertEquals((Integer) 55, response.getVersion());
    }

    @Test
    public void testPutUpdateFailWithId() throws UnauthorizedException, ServiceLayerException, UnSupportedMethodException, WebLayerException {
        request.setMethod("PUT");
        request.removeAllParameters();
        exception.expect(DtoFactoryException.class);
        exception.expectMessage("Error while creating Dto of type ch.alv.components.web.mock.MockDto from requestBody '{ \"version\": 55,}'.");
        controller.handlePutRequest(request, MODULE_NAME, STORE_NAME, "{ \"version\": 55,}");
    }

    @Test
    public void testDeleteSuccess() throws UnauthorizedException, ServiceLayerException, UnSupportedMethodException, WebLayerException {
        ResponseEntity response = (ResponseEntity) controller.handleDeleteRequest(request, MODULE_NAME, STORE_NAME, "testId");
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testDeleteNoId() throws UnauthorizedException, ServiceLayerException, UnSupportedMethodException, WebLayerException {
        exception.expect(BadRequestException.class);
        exception.expectMessage("The id must not be null!");
        controller.handleDeleteRequest(request, MODULE_NAME, STORE_NAME, null);
    }

    @Test
    public void testDeleteUnknownId() throws UnauthorizedException, ServiceLayerException, UnSupportedMethodException, WebLayerException {
        ResponseEntity response = (ResponseEntity) controller.handleDeleteRequest(request, MODULE_NAME, STORE_NAME, "unknownId");
        assertEquals(HttpStatus.OK, response.getStatusCode());
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
