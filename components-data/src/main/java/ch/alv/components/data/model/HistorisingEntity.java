package ch.alv.components.data.model;

import ch.alv.components.core.beans.Identifiable;
import ch.alv.components.core.beans.Versionable;

import java.io.Serializable;

/**
 * A historising bean has to provide methods to get & set the id  and
 * the and the state of the historised entity.
 *
 * @since 1.0.0
 */
public interface HistorisingEntity<ID extends Serializable> extends Identifiable<ID>, Versionable<Integer> {

    void setHistorisedEntityId(ID historisedEntityId);

    ID getHistorizedEntityId();

    void setEntityState(EntityState entityState);

    EntityState getEntityState();

}
