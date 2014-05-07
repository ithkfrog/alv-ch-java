package ch.alv.components.data.elastic;

import ch.alv.components.core.beans.ModelItem;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Entity for Repository test concerns
 *
 * @since 1.0.0
 */
@Document(indexName = "tests", type = "entities")
public class ElasticBaseSearchRepositoryTestEntity implements ModelItem<String, Integer> {

    private String id;

    private Integer version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    private String myAttribute;

    public String getMyAttribute() {
        return myAttribute;
    }

    public void setMyAttribute(String myAttribute) {
        this.myAttribute = myAttribute;
    }
}
