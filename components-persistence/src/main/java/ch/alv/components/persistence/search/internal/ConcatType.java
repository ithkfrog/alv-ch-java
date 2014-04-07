package ch.alv.components.persistence.search.internal;

/**
 * List of allowed predicate combinations.
 *
 * @since 1.0.0
 */
public enum ConcatType {

    AND(" AND "),
    OR(" OR ");

    private String sqlRepresentation;

    private ConcatType(String sqlRepresentation) {
        this.sqlRepresentation = sqlRepresentation;
    }

    public String render() {
        return sqlRepresentation;
    }

}
