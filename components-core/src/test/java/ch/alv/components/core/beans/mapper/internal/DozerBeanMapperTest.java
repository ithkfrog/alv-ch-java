package ch.alv.components.core.beans.mapper.internal;

import ch.alv.components.core.beans.mapper.MappingException;
import ch.alv.components.core.mock.BeanA;
import ch.alv.components.core.mock.BeanB;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


/**
 * Unit tests for the {@link ch.alv.components.core.beans.mapper.internal.DozerBeanMapper}.
 *
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/dozer-bean-mapper-test-context.xml")
public class DozerBeanMapperTest {

    private static final String MAPPING_FOLDER_PATH = "dozer/";
    private static final String MAPPING_FILE_PATH = "dozer/dozer-mappings-mapper-test.xml";
    private static final String MAPPING_FILE_PATTERN = "dozer-mappings-*.xml";
    private static final int NUMBER_OF_MAPPING_FILES = 1;

    @Autowired
    private DozerBeanMapper mapper;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void checkInitialization() {
        assertEquals(MAPPING_FOLDER_PATH, mapper.getMappingFilesFolderPath());
        assertEquals(MAPPING_FILE_PATH, mapper.getMappingFiles().get(0));
        assertEquals(MAPPING_FILE_PATTERN, mapper.getMappingFilesPattern());
        assertEquals(NUMBER_OF_MAPPING_FILES, mapper.getMappingFiles().size());
    }

    @Test
    public void testGettersAndSetters() {
        String pattern = mapper.getMappingFilesPattern();
        String folderPath = mapper.getMappingFilesFolderPath();

        String newPattern = "testPattern";
        mapper.setMappingFilesPattern(newPattern);
        assertEquals(newPattern, mapper.getMappingFilesPattern());
        mapper.setMappingFilesPattern(pattern);

        String newFolderPath = "testFolderPath";
        mapper.setMappingFilesFolderPath(newFolderPath);
        assertEquals(newFolderPath, mapper.getMappingFilesFolderPath());
        mapper.setMappingFilesFolderPath(folderPath);

        // check reset to initial state for further test cases
        assertEquals(pattern, mapper.getMappingFilesPattern());
        assertEquals(folderPath, mapper.getMappingFilesFolderPath());
    }

    @Test
    public void testAllowedNullValues() {
        // creation of fresh beans
        assertNull(mapper.mapObject(null, BeanA.class));
        assertNull(mapper.mapObject(null, null));
        assertNull(mapper.mapCollection(null, BeanA.class));
        assertNull(mapper.mapCollection(null, null));

        // mapping to existing beans
        BeanA a = null;
        mapper.mapObject(null, a);
        assertEquals(null, a);
    }

    @Test
    public void testForbiddenNullClassForFreshSingleObjects() {
        exception.expect(MappingException.class);
        exception.expectMessage("Error while mapping objects");
        mapper.mapObject(new BeanA(), null);
    }

    @Test
    public void testForbiddenNullClassForExistingSingleObjects() {
        exception.expect(MappingException.class);
        exception.expectMessage("Error while mapping objects");
        // mapping to existing beans
        BeanA a = null;
        mapper.mapObject(new BeanA(), a);
    }

    public void testForbiddenNullClassForCollections() {
        exception.expect(MappingException.class);
        exception.expectMessage("Error while mapping objects");
        mapper.mapObject(new ArrayList<>(), null);
    }

    @Test
    public void testMapToFreshObject() {
        String key = "testKey";
        String value = "testValue";
        BeanA a = new BeanA(key, value);
        BeanB b = mapper.mapObject(a, BeanB.class);
        assertEquals(key, b.getKey());
        assertEquals(value, b.getValue());
    }

    @Test
    public void testMapToExistingObject() {
        String key = "testKey";
        String value = "testValue";

        BeanA a = new BeanA(key, value);
        BeanB b = new BeanB("initialKey", "initialValue");

        mapper.mapObject(a, b);
        assertEquals(key, b.getKey());
        assertEquals(value, b.getValue());
    }

    @Test
    public void testMapCollection() {
        String keyA = "testKeyA";
        String valueA = "testValueA";

        String keyB = "testKeyB";
        String valueB = "testValueB";

        BeanA a = new BeanA(keyA, valueA);
        BeanA b = new BeanA(keyB, valueB);

        List<Object> list = new ArrayList<>();
        list.add(a);
        list.add(b);

        List<BeanB> newList = mapper.mapCollection(list, BeanB.class);
        assertEquals(keyA, newList.get(0).getKey());
        assertEquals(valueA, newList.get(0).getValue());
        assertEquals(keyB, newList.get(1).getKey());
        assertEquals(valueB, newList.get(1).getValue());
    }

}
