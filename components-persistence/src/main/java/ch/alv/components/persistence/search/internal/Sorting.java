package ch.alv.components.persistence.search.internal;

/**
 * Defines how a search result has to be sorted.
 *
 * @since 1.0.0
 */
public class Sorting {

    private String name;

    private String entityToken;

    private SortType type = SortType.ASC;

    public Sorting(String entityToken, String name, SortType type) {
        this.entityToken = entityToken;
        this.name = name;
        this.type = type;
    }

    public Sorting(String entityToken, String name) {
        this.entityToken = entityToken;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEntityToken() {
        return entityToken;
    }

    public void setEntityToken(String entityToken) {
        this.entityToken = entityToken;
    }

    public SortType getType() {
        return type;
    }

    public void setType(SortType type) {
        this.type = type;
    }
}
