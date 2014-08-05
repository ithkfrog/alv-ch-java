package ch.alv.components.data.mock;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for the {@link MockQueryProvider} class
 *
 * @since 1.0.0
 */
public class MockQueryProviderTest {

    @Test
    public void testGettersAndSetters() {
        MockQueryProvider provider = new MockQueryProvider();
        provider.setName("testName");
        assertEquals("testName", provider.getName());
        provider.setStaticQuery("testStaticQuery");
        assertEquals("testStaticQuery", provider.getStaticQuery());
    }

    @Test
    public void testCreateQuery() {
        MockQueryProvider provider = new MockQueryProvider();
        provider.setStaticQuery("testStaticQuery");
        assertEquals("testStaticQuery", provider.createQuery(null, null, null));
    }

}
