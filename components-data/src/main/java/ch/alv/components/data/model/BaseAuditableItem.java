package ch.alv.components.data.model;

import ch.alv.components.core.beans.Auditable;

import javax.persistence.*;
import java.util.Date;

/**
 * Base implementation of an entity.
 *
 * @since 1.0.0
 */
@MappedSuperclass
public class BaseAuditableItem extends BaseModelItem implements Auditable<User> {

    @JoinColumn
    @ManyToOne(targetEntity = User.class)
    private User createdBy;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    @JoinColumn
    @ManyToOne(targetEntity = User.class)
    private User lastUpdateBy;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateOn;

    @Override
    public User getCreatedBy() {
        return createdBy;
    }

    @Override
    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public Date getCreatedOn() {
        return createdOn;
    }

    @Override
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public User getLastUpdateBy() {
        return lastUpdateBy;
    }

    @Override
    public void setLastUpdateBy(User lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    @Override
    public Date getLastUpdateOn() {
        return lastUpdateOn;
    }

    @Override
    public void setLastUpdateOn(Date lastUpdateOn) {
        this.lastUpdateOn = lastUpdateOn;
    }
}
