package ch.alv.components.iam.endpoint.dto;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for the {@link ch.alv.components.iam.endpoint.dto.RoleDto} class
 *
 * @since 1.0.0
 */
public class RoleDtoTest {

    @Test
    public void testDto() {
        RoleDto dto = new RoleDto();
        String name = "testName";

        dto.setName(name);
        assertEquals(name, dto.getName());
    }

}
