package ch.alv.components.core.beans;

import java.util.Date;

/**
 * Interface for auditable entities.
 *
 * @since 1.0.0
 */
public interface Auditable<USER> {

    USER getCreatedBy();

    void setCreatedBy(USER createdBy);

    Date getCreatedOn();

    void setCreatedOn(Date createdOn);

    USER getLastUpdateBy();

    void setLastUpdateBy(USER lastUpdateBy);

    Date getLastUpdateOn();

    void setLastUpdateOn(Date lastUpdateOn);

}
