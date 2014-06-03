package ch.alv.components.data.mock;

import ch.alv.components.data.model.BaseModelItem;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Mock document for MongoDb tests
 *
 * @since 1.0.0
 */
@Document(collection = "testDocs")
public class MockMongoDbDocument extends BaseModelItem {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
