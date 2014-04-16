package ch.alv.components.data.elastic;

import ch.alv.components.core.reflection.ReflectionUtils;
import ch.alv.components.core.search.Search;
import ch.alv.components.core.search.SearchRenderer;
import ch.alv.components.core.search.ValuesProvider;
import ch.alv.components.data.search.BaseSearchRepositoryImpl;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.GetQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;

/**
 * Default implementation of the {@link ch.alv.components.data.elastic.ElasticBaseSearchRepositoryImpl} interface.
 *
 * @since 1.0.0
 */
@SuppressWarnings("unused, unchecked")
public abstract class ElasticBaseSearchRepositoryImpl<TYPE> extends BaseSearchRepositoryImpl<TYPE> {

    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;

    @Resource
    private ElasticSearchToQueryRenderer renderer;

    private GetQuery getQuery = new GetQuery();

    @Override
    protected List<TYPE> fetchFromSource(Pageable pageable, Search search, ValuesProvider valuesProvider) {
        SearchQuery searchQuery = (SearchQuery) renderer.render(search, valuesProvider);
        return queryForList(searchQuery, pageable);
    }

    @Override
    public Page<TYPE> getAll(Pageable pageable) {
        return queryForPage(new NativeSearchQueryBuilder().withQuery(matchAllQuery()).build(), pageable);
    }

    @Override
    public TYPE getById(String id) {
        getQuery.setId(id);
        return elasticsearchTemplate.queryForObject(getQuery, getEntityClass());
    }

    private List<TYPE> queryForList(SearchQuery query, Pageable pageable) {
        List<TYPE> result = (List<TYPE>) elasticsearchTemplate.queryForList(query, getEntityClass());
        if (pageable == null) {
            return result;
        }
        int offset = pageable.getOffset();
        int pageSize = pageable.getPageSize();
        int numberOfElements = result.size();

        if (offset > numberOfElements) {
            return new ArrayList();
        }
        if (numberOfElements < offset + pageSize) {
            return result.subList(offset, numberOfElements);
        }
        return result.subList(offset, offset + pageSize);
    }

    private Page<TYPE> queryForPage(SearchQuery query, Pageable pageable) {
        Page<TYPE> result = (Page<TYPE>) elasticsearchTemplate.queryForPage(query, getEntityClass());
        if (pageable == null) {
            return result;
        }
        int offset = pageable.getOffset();
        int pageSize = pageable.getPageSize();
        int numberOfElements = result.getNumberOfElements();

        if (offset > numberOfElements) {
            return new PageImpl(new ArrayList(), pageable, numberOfElements);
        }
        if (numberOfElements < offset + pageSize) {
            return new PageImpl(result.getContent().subList(offset, numberOfElements), pageable, numberOfElements);
        }
        return new PageImpl(result.getContent().subList(offset, offset + pageSize), pageable, numberOfElements);
    }

    @Override
    protected SearchRenderer getRenderer() {
        return renderer;
    }

    private Class<? extends TYPE> getEntityClass() {
        return ReflectionUtils.determineFirstParameterClassOfParameterizedSuperClass(getClass());
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
