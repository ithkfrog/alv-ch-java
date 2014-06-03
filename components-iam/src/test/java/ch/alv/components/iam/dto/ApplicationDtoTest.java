package ch.alv.components.iam.dto;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for the {@link ApplicationDto} class
 *
 * @since 1.0.0
 */
public class ApplicationDtoTest {

    @Test
    public void testDto() {
        ApplicationDto dto = new ApplicationDto();
        String name = "testName";
        dto.setName(name);
        assertEquals(name, dto.getName());
    }

}
