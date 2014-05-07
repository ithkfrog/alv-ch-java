package ch.alv.components.web.controller.internal;

import ch.alv.components.core.utils.StringHelper;
import ch.alv.components.web.controller.PersistingController;
import ch.alv.components.web.dto.DtoFactoryException;
import ch.alv.components.web.mapper.MockFactoryTestEntity;
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

import static org.junit.Assert.assertTrue;

/**
 * Test cases for the {@link ch.alv.components.web.controller.internal.BaseController} class.
 *
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/base-controller-test.xml")
public class BasePersistingControllerTest {

    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";
    public static final String TEST_PASSWORD = "testPassword";
    public static final String TEST_USER_NAME = "testUserName";

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Resource
    private PersistingController controller;

    @Before
    public void init() {
        UserDetails user = new TestUserDetails();
        Authentication auth = new TestAuthentication(user);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @Test
    public void testSuccess() {
        MockFactoryTestEntity result = (MockFactoryTestEntity) controller.persist("filterTestModule", "filterTestStore", null, "{ \"id\": \"abc\", \"version\": 3 }");
        assertTrue(StringHelper.isNotEmpty(result.getId()));
    }

    @Test
    public void testEmptyString() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("requestBody param must not be empty.");
        controller.persist("filterTestModule", "filterTestStore", null, "");
    }

    @Test
    public void testEmptyJsonObject() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Illegal string source.");
        controller.persist("filterTestModule", "filterTestStore", null, "{}");
    }

    @Test
    public void testIllegal() {
        exception.expect(DtoFactoryException.class);
        exception.expectMessage("Error while creating Dto of type ch.alv.components.web.mock.TestDto from requestBody 'illegalContent'.");
        controller.persist("filterTestModule", "filterTestStore", null, "illegalContent");
    }


    public class TestAuthentication implements Authentication {

        private static final long serialVersionUID = -6338301258920652057L;

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

        private static final long serialVersionUID = -439783384394800987L;

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
