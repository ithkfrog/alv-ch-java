package ch.alv.components.persistence.query.internal;

/**
 * List of all operators that can be used in a dynamic query.
 *
 * @since 1.0.0
 */
public enum QueryOperator {

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

    private QueryOperator(String sqlRepresentation) {
        this.sqlRepresentation = sqlRepresentation;
    }

    public String render() {
        return sqlRepresentation;
    }
}
