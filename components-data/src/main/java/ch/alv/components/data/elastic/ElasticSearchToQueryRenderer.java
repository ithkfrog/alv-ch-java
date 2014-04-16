package ch.alv.components.data.elastic;

import ch.alv.components.core.search.BaseSearchRendererImpl;
import ch.alv.components.core.search.Search;
import ch.alv.components.core.search.SearchRenderer;
import ch.alv.components.core.search.ValuesProvider;

/**
 * Implements the {@link SearchRenderer} interface to provide Elastic SearchImpl query strings.
 *
 * @since 1.0.0
 */
public class ElasticSearchToQueryRenderer extends BaseSearchRendererImpl {

    /**
     * Constructor
     *
     * @param defaultSearch search configuration to use if no custom configuration is passed.
     */
    public ElasticSearchToQueryRenderer(Search defaultSearch) {
        super(defaultSearch);
    }

    /* (non-Javadoc)
     * @see ch.alv.components.core.search.BaseSearchRendererImpl#doRender(ch.alv.components.core.search.SearchImpl, ch.alv.components.core.search.ValuesProvider)
     */
    @Override
    protected String doRender(Search search, ValuesProvider valuesProvider) {
        // TODO: implement rendering logic
        return null;
    }

    /* (non-Javadoc)
 * @see ch.alv.components.core.search.BaseSearchRendererImpl#decorateValue(java.lang.String, java.lang.Object)
 */
    @Override
    public Object decorateValue(Search search, String name, Object value) {
        // TODO: implement rendering logic
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

}
