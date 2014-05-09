package ch.alv.components.data.mock.jpa;

import ch.alv.components.data.jpa.JpaSearchAdapter;

import javax.persistence.EntityManagerFactory;

/**
 * JPA mock implementation of the {@link ch.alv.components.data.DataStoreSearchAdapter} repository.
 *
 * @since 1.0.0
 */
public class MockJpaEntitySearchAdapter extends JpaSearchAdapter<MockJpaEntity, String> {

    public MockJpaEntitySearchAdapter(EntityManagerFactory emf) {
        super(MockJpaEntity.class, emf.createEntityManager());
    }
}
