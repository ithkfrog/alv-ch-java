package ch.alv.components.persistence.search.internal;

/**
 * List of all operators / comparisons that can be used in a dynamic search.
 *
 * @since 1.0.0
 */
public enum ComparatorType {

    EQUALS(" = "),
    NOT_EQUALS(" != "),
    LIKE(" LIKE "),
    NOT_LIKE(" NOT LIKE "),
    GT(" > "),
    GE(" >= "),
    LT(" < "),
    LE(" <= "),
    IN(" IN "),
    NOT_IN(" NOT IN ");

    private final String sqlRepresentation;

    private ComparatorType(String sqlRepresentation) {
        this.sqlRepresentation = sqlRepresentation;
    }

    public String render() {
        return sqlRepresentation;
    }
}
