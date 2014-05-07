package ch.alv.components.data.elastic;

import ch.alv.components.data.internal.BaseSearchRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.GetQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;

/**
 * Default implementation of the {@link ElasticBaseSearchRepository} interface.
 *
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")
public abstract class ElasticBaseSearchRepository<TYPE, ID extends Serializable> extends BaseSearchRepository<TYPE, ID> {

    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    protected List<TYPE> fetchFromSource(Pageable pageable, Object search) {
        SearchQuery finalSearch = (SearchQuery) search;
        if (finalSearch == null) {
            finalSearch = new NativeSearchQueryBuilder().withQuery(matchAllQuery()).build();
        }
        return queryForList(finalSearch, pageable);
    }

    @Override
    public Page<TYPE> getAll(Pageable pageable) {
        return queryForPage(new NativeSearchQueryBuilder().withQuery(matchAllQuery()).build(), pageable);
    }

    public TYPE getOne(ID id) {
        GetQuery getQuery = new GetQuery();
        getQuery.setId(id.toString());
        return elasticsearchTemplate.queryForObject(getQuery, getEntityClass());
    }

    private List<TYPE> queryForList(SearchQuery query, Pageable pageable) {
        List<TYPE> result = (List<TYPE>) elasticsearchTemplate.queryForList(query, getEntityClass());
        return applyPageableToList(pageable, result);
    }

    private Page<TYPE> queryForPage(SearchQuery query, Pageable pageable) {
        Page<TYPE> result = (Page<TYPE>) elasticsearchTemplate.queryForPage(query, getEntityClass());
        return applyPageableToResultPage(pageable, result);
    }


}
