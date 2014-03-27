package ch.alv.components.web.dto;

/**
 * Base implementation of the {@link Dto}.
 *
 * @author seco-hrf
 * @since 1.0.0
 */
public abstract class DtoImpl implements Dto {

    private String id;

    private int version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
