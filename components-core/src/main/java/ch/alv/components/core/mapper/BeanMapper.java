package ch.alv.components.core.mapper;

import java.util.Collection;
import java.util.List;

/**
 * Interface for BeanMappers
 *
 * @since 1.0.0
 */
public interface BeanMapper {

    /**
     * Constructs new instance of destinationClass and performs mapping between
     * source and new instance.
     *
     * @param source
     * @param targetClass
     * @param <T>
     * @return
     * @throws MappingException
     */
    <T> T mapObject(Object source, Class<T> targetClass) throws MappingException;

    /**
     * Performs mapping between source and destination objects.
     *
     * @param source
     * @param target
     * @throws MappingException
     */
    void mapObject(Object source, Object target) throws MappingException;

    /**
     * Performs mappings of all elements of a list between source and
     * destination objects
     *
     * @param sourceCollection
     * @param targetClass
     * @throws MappingException
     */
    <T> List<T> mapCollection(Collection<?> sourceCollection, Class<T> targetClass) throws MappingException;

}
