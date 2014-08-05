package ch.alv.components.data.mock;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for the {@link ch.alv.components.data.mock.MockJpaQueryProvider} class
 *
 * @since 1.0.0
 */
public class MockJpaQueryProviderTest {

    @Test
    public void testGettersAndSetters() {
        MockJpaQueryProvider provider = new MockJpaQueryProvider();
        provider.setName("testName");
        assertEquals("testName", provider.getName());
    }

    @Test
    public void testCreateQuery() {
        MockJpaQueryProvider provider = new MockJpaQueryProvider();
        assertEquals("select m from MockJpaQueryProviderTest m", provider.createQuery(null, null, MockJpaQueryProviderTest.class));
    }

}
