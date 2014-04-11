package ch.alv.components.core.search;

/**
 * Define a source that is used for search.
 *
 * @since 1.0.0
 */
public class SearchSource {

    private String name;

    private String token;

    public SearchSource() {
    }

    public SearchSource(String token, String name) {
        this.name = name;
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
