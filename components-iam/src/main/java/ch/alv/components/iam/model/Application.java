package ch.alv.components.iam.model;

import ch.alv.components.data.model.BaseModelItem;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The Application entity (works with table 'module_iam_application')
 *
 * @since 1.0.0
 */
@Entity
@Table(name = "iam_application")
@Document(indexName = "iam", type = "applications", shards = 1, replicas = 0)
public class Application extends BaseModelItem {

    @Column(nullable = false)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
