package ch.alv.components.persistence.search;

/**
 * Parameters of custom search configurations (see {@link Search}) are defined by objects of this class.
 *
 * @since 1.0.0
 */
public class SearchParam {

    private String name;

    private Class<?> type;

    private Class<?> collectionType;

    public SearchParam() {
    }

    public SearchParam(String name, Class<?> type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public Class<?> getCollectionType() {
        return collectionType;
    }

    public void setCollectionType(Class<?> collectionType) {
        this.collectionType = collectionType;
    }
}
