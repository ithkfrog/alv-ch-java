package ch.alv.components.data.adapter;

import ch.alv.components.core.search.MapBasedValuesProvider;
import ch.alv.components.core.search.ValuesProvider;
import ch.alv.components.data.DataLayerException;
import ch.alv.components.data.mock.MockMongoDbDocument;
import ch.alv.components.data.mock.MockMongoDbQueryProvider;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Unit tests for the {@link ch.alv.components.data.query.DefaultQueryFactory} class.
 *
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")
public class MongoDbDataStoreAdapterTest {

    private static final int PORT_NBR = 27017;
    private static final String DATABASE_NAME = "embedded";

    private MongodProcess mongod;

    private DataStoreAdapter adapter;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void before() throws Exception {
        MongodStarter starter = MongodStarter.getDefaultInstance();
        IMongodConfig mongodConfig = new MongodConfigBuilder()
                .version(Version.Main.PRODUCTION)
                .net(new Net(27017, Network.localhostIsIPv6()))
                .build();

        MongodExecutable mongodExecutable = null;
        mongodExecutable = starter.prepare(mongodConfig);
        mongod = mongodExecutable.start();

        ApplicationContext context = new ClassPathXmlApplicationContext("spring/mongodb-data-store-adapter-test-context.xml");
        BeanFactory factory = context;
        adapter = (DataStoreAdapter) factory.getBean("adapter");
    }

    @After
    public void after() {
        mongod.stop();
    }


    @Test
    public void testFind() throws DataLayerException {
        MockMongoDbDocument entity = new MockMongoDbDocument();
        entity.setName("testName");
        entity = (MockMongoDbDocument) adapter.save(entity, MockMongoDbDocument.class);
        assertNotNull(adapter.find(entity.getId(), MockMongoDbDocument.class));
        assertNull(adapter.find("unknownItem", MockMongoDbDocument.class));

        MockMongoDbDocument entity2 = new MockMongoDbDocument();
        entity2.setName("otherName");
        adapter.save(entity2, MockMongoDbDocument.class);

        assertEquals(2, adapter.find(MockMongoDbDocument.class).size());
        Map<String, String[]> values = new HashMap<>();
        values.put("name", new String[] { "testName" });
        ValuesProvider valuesProvider = new MapBasedValuesProvider();
        valuesProvider.setValues(values);
        assertEquals(1, adapter.find(MockMongoDbQueryProvider.NAME, valuesProvider, MockMongoDbDocument.class).size());

        expectedException.expect(DataLayerException.class);
        expectedException.expectMessage("Could not execute query with name 'unknownQuery'.");
        adapter.find("unknownQuery", new MapBasedValuesProvider(), MockMongoDbDocument.class);
    }

    @Test
    public void testCreate() throws DataLayerException {
        assertNotNull(adapter.save(new MockMongoDbDocument(), MockMongoDbDocument.class).getId());
    }

    @Test
    public void testUpdate() throws DataLayerException {
        MockMongoDbDocument entity = new MockMongoDbDocument();

        entity = (MockMongoDbDocument) adapter.save(entity, MockMongoDbDocument.class);
        String id = entity.getId();
        assertNull(entity.getName());
        entity.setName("testName");
        adapter.save(entity, MockMongoDbDocument.class);
        entity = (MockMongoDbDocument) adapter.find(id, MockMongoDbDocument.class);
        assertEquals("testName", entity.getName());
    }

    @Test
    public void testDelete() throws DataLayerException {
        MockMongoDbDocument doc = new MockMongoDbDocument();
        doc = (MockMongoDbDocument) adapter.save(doc, MockMongoDbDocument.class);
        adapter.delete(doc.getId(), MockMongoDbDocument.class);
        assertNull(adapter.find(doc.getId(), MockMongoDbDocument.class));
        adapter.delete("unknown", MockMongoDbDocument.class);
    }

}
