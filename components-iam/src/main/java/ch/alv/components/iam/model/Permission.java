package ch.alv.components.iam.model;

import ch.alv.components.data.model.BaseModelItem;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The Permission entity (works with table 'module_iam_permission')
 *
 * @since 1.0.0
 */
@Entity
@Table(name = "iam_permission")
@Document(indexName = "iam", type = "permissions", shards = 1, replicas = 0)
public class Permission extends BaseModelItem {

    @Column(nullable = false)
    private String name;

    @Column(name = "permission_key", nullable = false)
    private String key;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
