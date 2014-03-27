package ch.alv.components.persistence.query.internal;

/**
 * List of allowed predicate combinations.
 *
 * @author seco-hrf
 * @since 1.0.0
 */
public enum QueryPredicateCombination {

    AND(" AND "),
    OR(" OR ");

    private String sqlRepresentation;

    private QueryPredicateCombination(String sqlRepresentation) {
        this.sqlRepresentation = sqlRepresentation;
    }

    public String render() {
        return sqlRepresentation;
    }

}
