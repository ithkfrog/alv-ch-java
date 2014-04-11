package ch.alv.components.data.testcommons;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Default search for testing purposes.
 *
 * @since 1.0.0
 */
@Component
public class TestDefaultSearch {

    @PostConstruct
    private void init() {
/*        setName("defaultSearch");
        select().where("a", "myAttribute").orderBy(new Sorting("a", "myAttribute", SortType.DESC));*/
    }
}
