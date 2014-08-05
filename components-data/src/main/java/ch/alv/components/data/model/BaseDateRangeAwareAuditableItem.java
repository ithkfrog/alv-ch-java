package ch.alv.components.data.model;

import ch.alv.components.core.beans.DateRangeAware;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Base implementation of an entity.
 *
 * @since 1.0.0
 */
@MappedSuperclass
public class BaseDateRangeAwareAuditableItem extends BaseAuditableItem implements DateRangeAware {

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date validFrom;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date validTo;

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }
}
