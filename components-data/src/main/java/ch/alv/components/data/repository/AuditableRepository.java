package ch.alv.components.data.repository;

import java.io.Serializable;

/**
 * Interface for a generic repository
 *
 * @since 1.0.0
 */
public interface AuditableRepository<ID extends Serializable> extends Repository<ID> {

    void addCurrentStateToHistory(ID id);

}
