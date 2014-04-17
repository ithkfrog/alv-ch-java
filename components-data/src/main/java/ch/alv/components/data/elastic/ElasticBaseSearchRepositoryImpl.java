package ch.alv.components.data.elastic;

import ch.alv.components.data.search.BaseSearchRepositoryImpl;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.GetQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import javax.annotation.Resource;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;

/**
 * Default implementation of the {@link ch.alv.components.data.elastic.ElasticBaseSearchRepositoryImpl} interface.
 *
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")
public abstract class ElasticBaseSearchRepositoryImpl<TYPE> extends BaseSearchRepositoryImpl<TYPE> {

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

    @Override
    public TYPE getById(String id) {
        GetQuery getQuery = new GetQuery();
        getQuery.setId(id);
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(Object that) {
        if (that == null) {
            return false;
        }
        if (that == this) {
            return true;
        }
        if (that.getClass() != getClass()) {
            return false;
        }
        return EqualsBuilder.reflectionEquals(this, that);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(17, 37, this);
    }

}
