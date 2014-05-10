package ch.alv.components.data.elastic;

import ch.alv.components.data.DataStoreSearchAdapter;
import ch.alv.components.data.internal.RepositoryHelper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.GetQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.io.Serializable;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;

/**
 * ElasticSearch implementation of the {@link ch.alv.components.data.DataStoreSearchAdapter} interface.
 *
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")
public class ElasticSearchAdapter<TYPE, ID extends Serializable> implements DataStoreSearchAdapter<TYPE, ID> {

    private final ElasticsearchTemplate elasticsearchTemplate;

    private final Class<?> managedClass;

    public ElasticSearchAdapter(ElasticsearchTemplate elasticsearchTemplate, Class<?> managedClass) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.managedClass = managedClass;
    }

    @Override
    public List<TYPE> fetchFromSource(Pageable pageable, Object search) {
        SearchQuery finalSearch = new NativeSearchQueryBuilder().withQuery(matchAllQuery()).build();
        if (search != null) {
            finalSearch = (SearchQuery) search;
        }
        List<TYPE> result = (List<TYPE>) elasticsearchTemplate.queryForList(finalSearch, managedClass);
        return RepositoryHelper.applyPageable(pageable, result);
    }

    public TYPE fetchFromSource(ID id) {
        GetQuery getQuery = new GetQuery();
        getQuery.setId(id.toString());
        return (TYPE) elasticsearchTemplate.queryForObject(getQuery, managedClass);
    }

}
