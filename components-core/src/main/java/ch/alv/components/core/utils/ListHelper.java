package ch.alv.components.core.utils;

import java.util.List;

/**
 * Convenience methods for {@link List}s.
 *
 * @since 1.0.0
 */
public class ListHelper {

    private ListHelper() {}

    public static <T> void forEach(List<T> items, ListItemProcessor<T> processor) {
        for (T item : items) {
            processor.process(item);
        }
    }

}
