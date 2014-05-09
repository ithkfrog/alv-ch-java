package ch.alv.components.data;

import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

/**
 * DataStoreSearchAdapters execute search objects to retrieve data from a certain store type (e.g. JPA, ElasticSearch).
 *
 * @since 1.0.0
 */
public interface DataStoreSearchAdapter<TYPE, ID extends Serializable> {

    List<TYPE> fetchFromSource(Pageable pageable, Object search);

    TYPE fetchFromSource(ID id);

}
