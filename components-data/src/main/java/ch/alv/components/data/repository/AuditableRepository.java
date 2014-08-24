package ch.alv.components.data.repository;

import ch.alv.components.core.beans.Identifiable;

import java.io.Serializable;

/**
 * Interface for a generic repository
 *
 * @since 1.0.0
 */
public interface AuditableRepository<TYPE extends Identifiable<ID>, ID extends Serializable> extends Repository<TYPE, ID> {

    void addCurrentStateToHistory(ID id);

}
