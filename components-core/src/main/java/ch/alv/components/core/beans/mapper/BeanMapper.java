package ch.alv.components.core.beans.mapper;

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
     * @param source the source object
     * @param targetClass the class of the resulting bean
     * @return an object of the targetClass carrying all mappable values.
     * @throws MappingException
     */
    <T> T mapObject(Object source, Class<T> targetClass) throws MappingException;

    /**
     * Performs mapping between source and destination objects.
     *
     * @param source the source object
     * @param target the target object
     * @throws MappingException
     */
    void mapObject(Object source, Object target) throws MappingException;

    /**
     * Performs mappings of all elements of a list between source and
     * destination objects
     *
     * @param sourceCollection the collection of sources
     * @param targetClass the class of the resulting bean
     * @return a list of objects of the targetClass carrying all mappable values.
     * @throws MappingException
     */
    <T> List<T> mapCollection(Collection<?> sourceCollection, Class<T> targetClass) throws MappingException;

}
