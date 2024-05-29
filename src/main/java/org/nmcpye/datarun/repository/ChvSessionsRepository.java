package org.nmcpye.datarun.repository;

import org.nmcpye.datarun.domain.ChvSessions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for the ChvSessions entity.
 */
@Repository
public interface ChvSessionsRepository extends JpaRepository<ChvSessions, Long> {
    default Optional<ChvSessions> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<ChvSessions> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<ChvSessions> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select chvSessions from ChvSessions chvSessions left join fetch chvSessions.team",
        countQuery = "select count(chvSessions) from ChvSessions chvSessions"
    )
    Page<ChvSessions> findAllWithToOneRelationships(Pageable pageable);

    @Query("select chvSessions from ChvSessions chvSessions left join fetch chvSessions.team")
    List<ChvSessions> findAllWithToOneRelationships();

    @Query("select chvSessions from ChvSessions chvSessions left join fetch chvSessions.team where chvSessions.id =:id")
    Optional<ChvSessions> findOneWithToOneRelationships(@Param("id") Long id);
}
