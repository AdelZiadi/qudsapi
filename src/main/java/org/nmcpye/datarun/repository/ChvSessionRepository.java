package org.nmcpye.datarun.repository;

import java.util.List;
import java.util.Optional;
import org.nmcpye.datarun.domain.ChvSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ChvSession entity.
 */
@Repository
public interface ChvSessionRepository extends JpaRepository<ChvSession, Long> {
    default Optional<ChvSession> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<ChvSession> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<ChvSession> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select chvSession from ChvSession chvSession left join fetch chvSession.team left join fetch chvSession.activity",
        countQuery = "select count(chvSession) from ChvSession chvSession"
    )
    Page<ChvSession> findAllWithToOneRelationships(Pageable pageable);

    @Query("select chvSession from ChvSession chvSession left join fetch chvSession.team left join fetch chvSession.activity")
    List<ChvSession> findAllWithToOneRelationships();

    @Query(
        "select chvSession from ChvSession chvSession left join fetch chvSession.team left join fetch chvSession.activity where chvSession.id =:id"
    )
    Optional<ChvSession> findOneWithToOneRelationships(@Param("id") Long id);
}
