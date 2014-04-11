package ch.alv.components.core.search;

/**
 * A projection represents an attribute which should be part of the search result items.
 *
 * @since 1.0.0
 */
public class Projection {

    /**
     * The token which denominates the related source
     */
    private String sourceToken;

    /**
     * The name of the attribute which should be selected.
     */
    private String attributeName;

    /**
     * Constructor.
     */
    public Projection() {
    }

    /**
     * Constructor
     * @param sourceToken the token which is assigned to the related source.
     * @param attributeName the name of the attribute which should be selected.
     */
    public Projection(String sourceToken, String attributeName) {
        this.sourceToken = sourceToken;
        this.attributeName = attributeName;
    }

    public String getSourceToken() {
        return sourceToken;
    }

    public void setSourceToken(String sourceToken) {
        this.sourceToken = sourceToken;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }
}
