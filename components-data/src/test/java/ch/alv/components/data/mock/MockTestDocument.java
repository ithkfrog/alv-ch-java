package ch.alv.components.data.mock;

import ch.alv.components.data.model.BaseModelItem;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Mock document for elasticsearch tests
 *
 * @since 1.0.0
 */
@Document(indexName = "testindex", type = "testdocuments")
public class MockTestDocument extends BaseModelItem {

}
