package ch.alv.components.iam.repository;

import ch.alv.components.data.internal.DefaultSearchRepository;
import ch.alv.components.data.jpa.JpaSearchAdapter;
import ch.alv.components.iam.model.User;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;

/**
 * Default implementation of the {@link UserRepositoryCustom} interface.
 *
 * @since 1.0.0
 */
public class UserRepositoryImpl extends DefaultSearchRepository<User, String> implements UserRepositoryCustom {

    @Inject
    public UserRepositoryImpl(EntityManagerFactory emf) {
        super(new JpaSearchAdapter<User, String>(User.class, emf.createEntityManager()));
    }

}
