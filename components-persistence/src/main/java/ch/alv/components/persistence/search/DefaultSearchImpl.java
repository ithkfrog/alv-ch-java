package ch.alv.components.persistence.search;

import ch.alv.components.persistence.search.internal.SortType;
import ch.alv.components.persistence.search.internal.Sorting;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * TODO: put some comment
 *
 * @since 1.0.0
 */
@Component
public class DefaultSearchImpl extends DynamicSearchImpl {

    @PostConstruct
    private void init() {
        setName("defaultSearch");
        select()
        .where("a", "uuid")
        .and("a", "version")
        .and("a", "name")
        .orderBy(new Sorting("a", "name", SortType.DESC));
    }
}
