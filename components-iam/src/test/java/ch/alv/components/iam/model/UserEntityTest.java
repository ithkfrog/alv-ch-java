package ch.alv.components.iam.model;

import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test cases for the {@link User} class
 *
 * @since 1.0.0
 */
public class UserEntityTest {

    @Test
    public void testEntity() {
        User entity = new User();
        String userName = "testUserName";
        String firstName = "testFirstName";
        String lastName = "testLastName";
        String password = "testPassword";
        int numberOfFailedLogins = 88;
        Date date = new Date();
        User user = new User();
        String email = "testEmail";
        List<Role> roles = new ArrayList<>();
        Role role = new Role();
        role.setName("ROLE_TEST");
        roles.add(role);

        entity.setUsername(userName);
        entity.setFirstName(firstName);
        entity.setLastName(lastName);
        entity.setPassword(password);
        entity.setNumberOfFailedLogins(numberOfFailedLogins);
        entity.setCreateDate(date);
        entity.setCreateUser(user);
        entity.setUpdateDate(date);
        entity.setUpdateUser(user);
        entity.setEmail(email);
        entity.setLastLogin(date);
        entity.setRoles(roles);

        assertEquals(userName, entity.getUsername());
        assertEquals(userName, entity.getUsername());
        assertEquals(firstName, entity.getFirstName());
        assertEquals(lastName, entity.getLastName());
        assertEquals(password, entity.getPassword());
        assertEquals(numberOfFailedLogins, entity.getNumberOfFailedLogins());
        assertEquals(date, entity.getCreateDate());
        assertEquals(user, entity.getCreateUser());
        assertEquals(date, entity.getUpdateDate());
        assertEquals(user, entity.getUpdateUser());
        assertEquals(email, entity.getEmail());
        assertEquals(date, entity.getLastLogin());
        assertEquals(roles, entity.getRoles());

        assertTrue(entity.isAccountNonLocked());
        assertTrue(entity.isAccountNonExpired());
        assertTrue(entity.isCredentialsNonExpired());
        assertTrue(entity.isEnabled());

        List<GrantedAuthority> auths = new ArrayList<>();
        auths.add(new SimpleGrantedAuthority(role.getName()));
        assertEquals(auths, entity.getAuthorities());

    }

}
