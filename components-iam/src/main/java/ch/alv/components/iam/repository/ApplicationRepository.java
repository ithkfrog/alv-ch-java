package ch.alv.components.iam.repository;

import ch.alv.components.iam.model.Application;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for {@link Application} entities
 *
 * @since 1.0.0
 */
@Repository
public interface ApplicationRepository extends PagingAndSortingRepository<Application, String>, ApplicationRepositoryCustom {
}
