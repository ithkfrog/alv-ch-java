package ch.alv.components.iam.endpoint.dto;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for the {@link PermissionDto} class
 *
 * @since 1.0.0
 */
public class PermissionDtoTest {

    @Test
    public void testDto() {
        PermissionDto dto = new PermissionDto();
        String name = "testName";
        String key = "testKey";

        dto.setName(name);
        dto.setKey(key);

        assertEquals(name, dto.getName());
        assertEquals(key, dto.getKey());
    }

}
