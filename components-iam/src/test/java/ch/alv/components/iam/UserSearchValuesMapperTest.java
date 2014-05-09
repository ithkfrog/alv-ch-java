package ch.alv.components.iam;

import ch.alv.components.iam.search.UserSearchValuesMapper;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for the {@link UserSearchValuesMapper} class.
 *
 * @since 1.0.0
 */
public class UserSearchValuesMapperTest {

    private UserSearchValuesMapper mapper = new UserSearchValuesMapper();

    @Test
    public void testMap() {
        Map<String, String[]> source = new HashMap<>();
        source.put(IamConstant.PARAM_USER_NAME, new String[] { "testUserName" });
        source.put(IamConstant.PARAM_LAST_NAME, new String[] { "testLastName" });
        source.put(IamConstant.PARAM_EMAIL, new String[] { "testEmail" });

        Map<String, Object> target = new HashMap<>();
        mapper.map(source, target);

        assertEquals(3, target.size());
        assertEquals("testUserName", target.get(IamConstant.PARAM_USER_NAME));
        assertEquals("testLastName", target.get(IamConstant.PARAM_LAST_NAME));
        assertEquals("testEmail", target.get(IamConstant.PARAM_EMAIL));
    }


}
