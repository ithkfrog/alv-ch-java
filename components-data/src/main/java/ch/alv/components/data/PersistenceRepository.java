package ch.alv.components.data;

import ch.alv.components.core.beans.ModelItem;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * A persistence repository serves to manipulate data on a data store.
 *
 * @since 1.0.0
 */
@NoRepositoryBean
public interface PersistenceRepository<TYPE extends ModelItem<ID, VERSION>,
        ID extends Serializable,
        VERSION extends Serializable>
        extends DataRepository<TYPE, ID> {

    /**
     * Saves a given entity. Use the returned instance for further operations as the save operation might have changed the
     * entity instance completely.
     *
     * @param entity the entity to persist
     * @return the saved entity
     */
    <S extends TYPE> S save(S entity);

    /**
     * Saves all given entities.
     *
     * @param entities an iterable of entities to persist
     * @return the saved entities
     * @throws IllegalArgumentException in case the given entity is (@literal null}.
     */
    <S extends TYPE> Page<S> save(Iterable<S> entities);

    /**
     * Returns whether an entity with the given id exists.
     *
     * @param id must not be {@literal null}.
     * @return true if an entity with the given id exists, {@literal false} otherwise
     * @throws IllegalArgumentException if {@code id} is {@literal null}
     */
    boolean exists(ID id);

    /**
     * Returns the number of entities available.
     *
     * @return the number of entities
     */
    long count();

    /**
     * Deletes the entity with the given id.
     *
     * @param id must not be {@literal null}.
     * @throws IllegalArgumentException in case the given {@code id} is {@literal null}
     */
    void delete(ID id);

    /**
     * Deletes a given entity.
     *
     * @param entity the entity to delete
     * @throws IllegalArgumentException in case the given entity is (@literal null}.
     */
    void delete(TYPE entity);

    /**
     * Deletes the given entities.
     *
     * @param entities an iterable of entities to delete.
     * @throws IllegalArgumentException in case the given {@link Iterable} is (@literal null}.
     */
    void delete(Iterable<? extends TYPE> entities);

    /**
     * Deletes all entities managed by the repository.
     */
    void deleteAll();

}
