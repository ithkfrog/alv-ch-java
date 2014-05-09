package ch.alv.components.iam.repository;

import ch.alv.components.data.internal.DefaultSearchRepository;
import ch.alv.components.data.jpa.JpaSearchAdapter;
import ch.alv.components.iam.model.Application;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;

/**
 * Default implementation of the {@link ApplicationRepositoryCustom} interface.
 *
 * @since 1.0.0
 */
public class ApplicationRepositoryImpl extends DefaultSearchRepository<Application, String> implements ApplicationRepositoryCustom {

    @Inject
    public ApplicationRepositoryImpl(EntityManagerFactory emf) {
        super(Application.class, new JpaSearchAdapter<Application, String>(Application.class, emf.createEntityManager()));
    }

}
