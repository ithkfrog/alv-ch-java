package ch.alv.components.core.model;

/**
 * Mark carriers of ids with this interface.
 *
 * @since 1.0.0
 */
public interface Identifiable<ID> {

    ID getId();

    void setId(ID id);

}
