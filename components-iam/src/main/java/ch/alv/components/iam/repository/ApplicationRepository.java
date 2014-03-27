package ch.alv.components.iam.repository;

import ch.admin.seco.tcsb.modules.iam.model.Application;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for {@link Application} entities
 *
 * @author seco-hrf
 * @since 1.0.0
 */
@Repository
public interface ApplicationRepository extends PagingAndSortingRepository<Application, String>, ApplicationRepositoryCustom {}
