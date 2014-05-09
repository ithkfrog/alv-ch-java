package ch.alv.components.iam.mock;

import ch.alv.components.data.SearchRepository;
import ch.alv.components.data.internal.DefaultSearchRepository;
import ch.alv.components.data.jpa.JpaSearchAdapter;
import ch.alv.components.iam.model.Role;
import ch.alv.components.iam.model.User;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Mock for the SearchRepository
 *
 * @since 1.0.0
 */
@Repository
public class MockUserRepository extends DefaultSearchRepository<User, String> implements SearchRepository<User, String> {

    Map<String, User> entitiesMap = new HashMap<>();

    List<User> entitiesList = new ArrayList<>();

    @Inject
    public MockUserRepository(EntityManager em) {
        super(User.class, new JpaSearchAdapter<User, String>(User.class, em));
    }

    @PostConstruct
    private void init() {
        User admin = new User();
        admin.setId("user_admin_id");
        List<Role> adminRoles = new ArrayList<>();
        Role roleAdmin = new Role();
        roleAdmin.setId("role_admin_id");
        roleAdmin.setName("ROLE_ADMIN");
        adminRoles.add(roleAdmin);
        admin.setRoles(adminRoles);

        for (User entity : entitiesList) {
            entitiesMap.put(entity.getId(), entity);
        }
    }

}
