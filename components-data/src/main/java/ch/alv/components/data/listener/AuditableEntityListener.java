package ch.alv.components.data.listener;

import ch.alv.components.core.beans.Auditable;
import ch.alv.components.core.beans.ModelItem;
import ch.alv.components.core.beans.mapper.BeanMapper;
import ch.alv.components.core.spring.AutowireHelper;
import ch.alv.components.data.model.EntityState;
import ch.alv.components.data.model.Historised;
import ch.alv.components.data.model.HistorisingEntity;

import javax.annotation.Resource;
import javax.persistence.*;
import java.util.Date;

/**
 * Listener that implements the required actions to realize an auditable entity.
 *
 * @since 1.0.0
 */
public class AuditableEntityListener {

    @Resource
    private EntityManagerFactory emf;

    @Resource
    private BeanMapper mapper;

    @PrePersist
    public void onPrePersist(ModelItem e) {
        Historised historisedAnnotation = e.getClass().getAnnotation(Historised.class);
        if (historisedAnnotation == null) {
            return;
        }
        Date now = new Date();
        ((Auditable) e).setCreatedOn(now);
        ((Auditable) e).setLastUpdateOn(now);
    }

    @PostPersist
    public void onPostPersist(ModelItem e) {
        addCurrentStateToHistory(e, EntityState.INITIAL);
    }

    @PreUpdate
    public void onPreUpdate(ModelItem e) {
        Historised historisedAnnotation = e.getClass().getAnnotation(Historised.class);
        if (historisedAnnotation == null) {
            return;
        }
        ((Auditable) e).setLastUpdateOn(new Date());
    }

    @PostUpdate
    public void onPostUpdate(ModelItem e) {
        addCurrentStateToHistory(e, EntityState.UPDATED);
    }

    @PreRemove
    public void onPreDelete(ModelItem e) {
        Historised historisedAnnotation = e.getClass().getAnnotation(Historised.class);
        if (historisedAnnotation == null) {
            return;
        }
        ((Auditable) e).setLastUpdateOn(new Date());
    }

    @PostRemove
    public void onPostDelete(ModelItem e) {
        addCurrentStateToHistory(e, EntityState.DELETED);
    }

    private void addCurrentStateToHistory(ModelItem e, EntityState entityState) {
        if (emf == null) {
            AutowireHelper.autowire(this, this.emf);
        }
        if (mapper == null) {
            AutowireHelper.autowire(this, this.mapper);
        }
        Historised historisedAnnotation = e.getClass().getAnnotation(Historised.class);
        if (historisedAnnotation == null) {
            return;
        }
        Class<? extends HistorisingEntity> targetClass = historisedAnnotation.targetClass();

        HistorisingEntity historyObject = mapper.mapObject(e, targetClass);
        historyObject.setHistorisedEntityId(e.getId());
        historyObject.setId(null);
        historyObject.setEntityState(entityState);
        EntityManager em = emf.createEntityManager();
        em.joinTransaction();
        em.persist(historyObject);
        em.flush();
        em.close();
    }

}
