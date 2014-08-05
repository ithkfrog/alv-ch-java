package ch.alv.components.core.beans;

import java.util.Date;

/**
 * A DateRangeAware entity contains information on its own temporal validity.
 *
 * @since 1.0.0
 */
public interface DateRangeAware {

    Date getValidFrom();

    void setValidFrom(Date validFrom);

    Date getValidTo();

    void setValidTo(Date validTo);

}
