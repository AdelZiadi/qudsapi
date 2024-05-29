package org.nmcpye.datarun.repository;

import org.nmcpye.datarun.domain.ChvSessions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for the ChvSessions entity.
 */
@Repository
public interface ChvSessionsRepositoryCustom extends ChvSessionsRepository {
    default Optional<ChvSessions> findOneWithEagerRelationshipsByUser(Long id) {
        return this.findOneWithToOneRelationshipsByUser(id);
    }

    default List<ChvSessions> findAllWithEagerRelationshipsByUser() {
        return this.findAllWithToOneRelationships();
    }

    default Page<ChvSessions> findAllWithEagerRelationshipsByUser(Pageable pageable) {
        return this.findAllWithToOneRelationshipsByUser(pageable);
    }

    @Query(
        value = "select chvSessions from ChvSessions chvSessions " +
            "left join chvSessions.team " +
            "where chvSessions.team.userInfo.login = ?#{authentication.name}",
        countQuery = "select count(chvSessions) from ChvSessions chvSessions " +
            "where chvSessions.team.userInfo.login = ?#{authentication.name}"
    )
    Page<ChvSessions> findAllByUser(Pageable pageable);


    @Query(
        value = "select chvSessions from ChvSessions chvSessions left join fetch chvSessions.team " +
            "where chvSessions.team.userInfo.login = ?#{authentication.name}",
        countQuery = "select count(chvSessions) from ChvSessions chvSessions " +
            "where chvSessions.team.userInfo.login = ?#{authentication.name}"
    )
    Page<ChvSessions> findAllWithToOneRelationshipsByUser(Pageable pageable);

    @Query("select chvSessions from ChvSessions chvSessions " +
        "left join fetch chvSessions.team " +
        "where chvSessions.team.userInfo.login = ?#{authentication.name}")
    List<ChvSessions> findAllWithToOneRelationshipsByUser();

    @Query("select chvSessions from ChvSessions chvSessions " +
        "left join fetch chvSessions.team where chvSessions.id =:id and chvSessions.team.userInfo.login = ?#{authentication.name}")
    Optional<ChvSessions> findOneWithToOneRelationshipsByUser(@Param("id") Long id);
}
