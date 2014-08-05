package ch.alv.components.data.model;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for the {@link User} class
 *
 * @since 1.0.0
 */
public class UserTest {

    @Test
    public void testEntity() {
        Date now = new Date();
        User entity = new User();
        entity.setId("testId");
        entity.setUserName("userName");
        assertEquals("userName", entity.getUserName());
        entity.setFirstName("testFirstName");
        assertEquals("testFirstName", entity.getFirstName());
        entity.setLastName("testLastName");
        assertEquals("testLastName", entity.getLastName());
        entity.setEmail("testEmail");
        assertEquals("testEmail", entity.getEmail());
        entity.setPassword("testPassword");
        assertEquals("testPassword", entity.getPassword());
        entity.setLastLogin(now);
        assertEquals(now, entity.getLastLogin());
        entity.setNumberOfFailedLogins(99);
        assertEquals(99, entity.getNumberOfFailedLogins());
    }

}
