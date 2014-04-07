package ch.alv.components.persistence.testcommons;

import ch.alv.components.persistence.search.DynamicSearchImpl;
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
public class TestDefaultSearch extends DynamicSearchImpl {

    @PostConstruct
    private void init() {
        setName("defaultSearch");
        select().where("a", "myAttribute").orderBy(new Sorting("a", "myAttribute", SortType.DESC));
    }
}
