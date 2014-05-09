package ch.alv.components.iam.repository;

import ch.alv.components.data.internal.DefaultSearchRepository;
import ch.alv.components.data.jpa.JpaSearchAdapter;
import ch.alv.components.iam.model.Permission;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;

/**
 * Default implementation of the {@link PermissionRepositoryCustom} interface.
 *
 * @since 1.0.0
 */
public class PermissionRepositoryImpl extends DefaultSearchRepository<Permission, String> implements PermissionRepositoryCustom {

    @Inject
    public PermissionRepositoryImpl(EntityManagerFactory emf) {
        super(Permission.class, new JpaSearchAdapter<Permission, String>(Permission.class, emf.createEntityManager()));
    }

}
