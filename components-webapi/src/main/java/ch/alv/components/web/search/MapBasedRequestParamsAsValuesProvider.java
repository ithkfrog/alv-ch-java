package ch.alv.components.web.search;

import ch.alv.components.core.search.internal.MapBasedValuesProvider;

import java.util.HashMap;
import java.util.Map;

/**
 * Extension of the {@link MapBasedValuesProvider} which may consume request param maps and provide
 * them as values map of type <String, Objec>
 *
 * @since 1.0.0
 */
public class MapBasedRequestParamsAsValuesProvider extends MapBasedValuesProvider {

    private final Map<String, String[]> source = new HashMap<>();

    private final RequestParamsToValuesMapper mapper;

    public MapBasedRequestParamsAsValuesProvider(RequestParamsToValuesMapper mapper) {
        super();
        this.mapper = mapper;
        mapper.map(source, getValues());
    }

    public MapBasedRequestParamsAsValuesProvider(RequestParamsToValuesMapper mapper, Map<String, String[]> source) {
        super();
        this.mapper = mapper;
        setSource(source);
    }

    public void setSource(Map<String, String[]> source) {
        if (source == null) {
            this.source.clear();
        } else {
            this.source.putAll(source);
        }
        mapper.map(source, getValues());
    }

}
