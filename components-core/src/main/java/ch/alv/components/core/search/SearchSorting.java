package ch.alv.components.core.search;

/**
 * Indicates how a search result should be sorted.
 *
 * @since 1.0.0
 */
public class SearchSorting {

    private SortType sortType = SortType.ASC;

    private String sourceToken;

    private String attributeName;

    public SearchSorting(String sourceToken, String attributeName) {
        this.sourceToken = sourceToken;
        this.attributeName = attributeName;
    }

    public SearchSorting(String sourceToken, String attributeName, SortType sortType) {
        this.sourceToken = sourceToken;
        this.sortType = sortType;
        this.attributeName = attributeName;
    }

    public SortType getSortType() {
        return sortType;
    }

    public void setSortType(SortType sortType) {
        this.sortType = sortType;
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

    public String getAttribute() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }
}
