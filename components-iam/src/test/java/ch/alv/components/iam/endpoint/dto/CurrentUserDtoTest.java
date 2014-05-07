package ch.alv.components.iam.endpoint.dto;

import org.junit.Test;
import org.springframework.hateoas.Link;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for the {@link ch.alv.components.iam.endpoint.dto.CurrentUserDto} class
 *
 * @since 1.0.0
 */
public class CurrentUserDtoTest {

    @Test
    public void testDto() {
        CurrentUserDto dto = new CurrentUserDto();

        String userName = "testUserName";
        String firstName = "testFirstName";
        String lastName = "testLastName";
        String email = "testEmail";
        Date date = new Date();
        List<String> roles = new ArrayList<>();
        roles.add("ROLE_TEST");
        Link user = new Link("updateUserLink");
        int numberOfFailedLogins = 77;

        dto.setUserName(userName);
        dto.setFirstName(firstName);
        dto.setLastName(lastName);
        dto.setEmail(email);
        dto.setRoles(roles);
        dto.setLastLogin(date);
        dto.setNumberOfFailedLogins(numberOfFailedLogins);
        dto.setCreateDate(date);
        dto.setCreateUser(user);
        dto.setUpdateUser(user);
        dto.setUpdateDate(date);

        assertEquals(userName, dto.getUserName());
        assertEquals(firstName, dto.getFirstName());
        assertEquals(lastName, dto.getLastName());
        assertEquals(email, dto.getEmail());
        assertEquals(roles, dto.getRoles());
        assertEquals(date, dto.getLastLogin());
        assertEquals(numberOfFailedLogins, dto.getNumberOfFailedLogins());
        assertEquals(date, dto.getCreateDate());
        assertEquals(user, dto.getCreateUser());
        assertEquals(user, dto.getUpdateUser());
        assertEquals(date, dto.getUpdateDate());

    }

}
