package ch.alv.components.iam.search;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for the {@link ch.alv.components.iam.repository.ApplicationRepositoryImpl} class.
 *
 * @since 1.0.0
 */
public class UserSearchValuesProviderTest {

    @Test
    public void fullCoverageForStaticTest() {

        Map<String, String[]> map = new HashMap<>();
        map.put("userName", new String[]{"testUserName"});
        map.put("lastName", new String[]{"testLastName"});
        map.put("email", new String[]{"testEmail"});

        UserSearchValuesProvider provider = new UserSearchValuesProvider();
        provider.setSource(map);

        assertEquals("testUserName", provider.getStringValue("userName"));
    }

}
