package ch.alv.components.persistence.search;

/**
 * TODO: put some comment
 *
 * @since 1.0.0
 */
public class HardCodedSearchImpl implements HardCodedSearch {

    private final String name;

    private final String template;

    public HardCodedSearchImpl(String name, String template) {
        this.name = name;
        this.template = template;
    }

    @Override
    public String getTemplate() {
        return template;
    }

    @Override
    public String getName() {
        return name;
    }
}
