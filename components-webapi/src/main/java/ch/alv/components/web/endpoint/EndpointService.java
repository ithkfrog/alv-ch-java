package ch.alv.components.web.endpoint;

import org.springframework.data.domain.Pageable;

import java.util.Map;

/**
 * Methods which an endpoint service has to provide.
 *
 * @author seco-hrf
 * @since 1.0.0
 */
public interface EndpointService {

    /**
     * Find items in a specific store or collection. If the request object contains search params, those have to be considered.
     *
     * @param moduleName the name of the module (first part of the URI)
     * @param storeName  the name of the store (second part of the URI)
     * @return An array of entities (can be empty) with corresponding paging info.
     */
    Object find(Pageable pageable, Map<String, String[]> params, String moduleName, String storeName);

    /**
     * Fetch an entity by its id
     *
     * @param moduleName the name of the module (first part of the URI)
     * @param storeName  the name of the store (second part of the URI)
     * @param id         the id of the entity
     * @return the entity with the given id, wrapped in a ResponseEntity or an empty ResponseEntity with error code 'not found'.
     */
    Object getById(String moduleName, String storeName, String id);


    /**
     * Add a new entity to a store / collection with post style (if the id is not empty and an entity with this id exists).
     * If no entity with such an id could be found, a new one with a fresh id (!) will be created.
     *
     * @param moduleName the name of the module (first part of the URI)
     * @param storeName  the name of the store (second part of the URI)
     * @param body       the entity to persist as json-representation
     * @return the data of the persisted entity, wrapped in a ResponseEntity
     */
    Object post(String moduleName, String storeName, String body);

    /**
     * Update an existing one with post style.
     * If no entity with such an id could be found, a 400 error is sent.
     *
     * @param moduleName the name of the module (first part of the URI)
     * @param storeName  the name of the store (second part of the URI)
     * @param id         the id of the entity
     * @param body       the entity to persist as json-representation
     * @return the data of the persisted entity, wrapped in a ResponseEntity
     */
    Object post(String moduleName, String storeName, String id, String body);

    /**
     * Add a new entity to a store / collection.
     *
     * @param moduleName the name of the module (first part of the URI)
     * @param storeName  the name of the store (second part of the URI)
     * @param body       the entity to persist as json-representation
     * @return the data of the persisted entity, wrapped in a ResponseEntity
     */
    Object put(String moduleName, String storeName, String body);

    /**
     * Update an existing entity with put style.
     * If no entity with such an id could be found, a 400 error is sent.
     *
     * @param moduleName the name of the module (first part of the URI)
     * @param storeName  the name of the store (second part of the URI)
     * @param id         the id of the entity
     * @param body       the entity to persist as json-representation
     * @return the data of the persisted entity, wrapped in a ResponseEntity
     */
    Object put(String moduleName, String storeName, String id, String body);


    /**
     * Delete an existing entity or remove one from a collection.
     *
     * @param moduleName the name of the module (first part of the URI)
     * @param storeName  the name of the store (second part of the URI)
     * @param id         the id of the entity
     * @return the result of the transaction
     */
    Object delete(String moduleName, String storeName, String id);

}
