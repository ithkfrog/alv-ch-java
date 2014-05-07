package ch.alv.components.iam.endpoint.dto;

import org.junit.Test;
import org.springframework.hateoas.Link;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for the {@link UserDto} class
 *
 * @since 1.0.0
 */
public class UserDtoTest {

    @Test
    public void testDto() {
        UserDto dto = new UserDto();

        String userName = "testUserName";
        String firstName = "testFirstName";
        String lastName = "testLastName";
        String email = "testEmail";
        Date updateDate = new Date();
        String password = "testPassword";
        List<Link> roles = new ArrayList<>();
        roles.add(new Link("http://localhost.ch"));

        dto.setUserName(userName);
        dto.setFirstName(firstName);
        dto.setLastName(lastName);
        dto.setEmail(email);
        dto.setUpdateDate(updateDate);
        dto.setPassword(password);
        dto.setRoles(roles);

        assertEquals(userName, dto.getUserName());
        assertEquals(firstName, dto.getFirstName());
        assertEquals(lastName, dto.getLastName());
        assertEquals(email, dto.getEmail());
        assertEquals(updateDate, dto.getUpdateDate());
        assertEquals(password, dto.getPassword());
        assertEquals(roles, dto.getRoles());
    }

}
