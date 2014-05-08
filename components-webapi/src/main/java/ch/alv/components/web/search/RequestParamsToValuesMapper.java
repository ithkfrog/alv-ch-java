package ch.alv.components.web.search;

import java.util.Map;

/**
 * Maps request params (a map of <String, String[]>) to a ValuesProvider's map (typed <String, Object>).
 *
 * @since 1.0.0
 */
public interface RequestParamsToValuesMapper {

    /**
     * Maps the required values from the source to the target map and converts the values into the required type.
     * @param source
     * @param target
     */
    void map(Map<String, String[]> source, Map<String, Object> target);

}
