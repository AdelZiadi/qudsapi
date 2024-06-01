package org.nmcpye.datarun.repository;

import org.nmcpye.datarun.domain.ProgressStatus;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data JPA Custom repository for the ProgressStatus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProgressStatusRepositoryCustom
    extends ProgressStatusRepository {

    Optional<ProgressStatus> findByUid(String uid);
}
