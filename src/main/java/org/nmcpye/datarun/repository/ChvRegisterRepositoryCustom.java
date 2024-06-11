package org.nmcpye.datarun.repository;

import org.nmcpye.datarun.domain.ChvRegister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for the ChvRegister entity.
 */
@Repository
public interface ChvRegisterRepositoryCustom
    extends ChvRegisterRepository {
    Optional<ChvRegister> findByUid(String uid);
}
