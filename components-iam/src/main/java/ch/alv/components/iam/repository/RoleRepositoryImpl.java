package ch.alv.components.iam.repository;

import ch.alv.components.data.internal.DefaultSearchRepository;
import ch.alv.components.data.jpa.JpaSearchAdapter;
import ch.alv.components.iam.model.Role;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;

/**
 * Default implementation of the {@link RoleRepositoryCustom} interface.
 *
 * @since 1.0.0
 */
public class RoleRepositoryImpl extends DefaultSearchRepository<Role, String> implements RoleRepositoryCustom {

    @Inject
    public RoleRepositoryImpl(EntityManagerFactory emf) {
        super(Role.class, new JpaSearchAdapter<Role, String>(Role.class, emf.createEntityManager()));
    }

}
