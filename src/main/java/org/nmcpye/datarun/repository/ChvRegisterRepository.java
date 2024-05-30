package org.nmcpye.datarun.repository;

import java.util.List;
import java.util.Optional;
import org.nmcpye.datarun.domain.ChvRegister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ChvRegister entity.
 */
@Repository
public interface ChvRegisterRepository extends JpaRepository<ChvRegister, Long> {
    default Optional<ChvRegister> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<ChvRegister> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<ChvRegister> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select chvRegister from ChvRegister chvRegister left join fetch chvRegister.patient left join fetch chvRegister.team",
        countQuery = "select count(chvRegister) from ChvRegister chvRegister"
    )
    Page<ChvRegister> findAllWithToOneRelationships(Pageable pageable);

    @Query("select chvRegister from ChvRegister chvRegister left join fetch chvRegister.patient left join fetch chvRegister.team")
    List<ChvRegister> findAllWithToOneRelationships();

    @Query(
        "select chvRegister from ChvRegister chvRegister left join fetch chvRegister.patient left join fetch chvRegister.team where chvRegister.id =:id"
    )
    Optional<ChvRegister> findOneWithToOneRelationships(@Param("id") Long id);
}
