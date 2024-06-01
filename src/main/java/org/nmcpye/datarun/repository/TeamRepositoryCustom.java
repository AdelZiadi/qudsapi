package org.nmcpye.datarun.repository;

import org.nmcpye.datarun.domain.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for the Team entity.
 */
@Repository
public interface TeamRepositoryCustom
    extends TeamRepository {

    Optional<Team> findByUid(String uid);

    default Optional<Team> findOneWithEagerRelationshipsByUser(Long id) {
        return this.findOneWithToOneRelationshipsByUser(id);
    }

    default List<Team> findAllWithEagerRelationshipsByUser() {
        return this.findAllWithToOneRelationshipsByUser();
    }

    default Page<Team> findAllWithEagerRelationshipsByUser(Pageable pageable) {
        return this.findAllWithToOneRelationshipsByUser(pageable);
    }

    @Query(
        value = "select team from Team team " +
            "left join team.activity " +
            "left join team.operationRoom " +
            "left join team.warehouse " +
            "left join team.userInfo " +
            "where team.userInfo.login = ?#{authentication.name}",
        countQuery = "select count(team) from Team team " +
            "where team.userInfo.login = ?#{authentication.name}"
    )
    Page<Team> findAllByUser(Pageable pageable);

    @Query(
        value = "select team from Team team " +
            "left join fetch team.activity " +
            "left join fetch team.operationRoom " +
            "left join fetch team.warehouse " +
            "left join fetch team.userInfo " +
            "where team.userInfo.login = ?#{authentication.name}",
        countQuery = "select count(team) from Team team " +
            "where team.userInfo.login = ?#{authentication.name}"
    )
    Page<Team> findAllWithToOneRelationshipsByUser(Pageable pageable);

    @Query(
        "select team from Team team " +
            "left join fetch team.activity " +
            "left join fetch team.operationRoom " +
            "left join fetch team.warehouse " +
            "left join fetch team.userInfo " +
            "where team.userInfo.login = ?#{authentication.name}"
    )
    List<Team> findAllWithToOneRelationshipsByUser();

    @Query(
        "select team from Team team " +
            "left join fetch team.activity " +
            "left join fetch team.operationRoom " +
            "left join fetch team.warehouse " +
            "left join fetch team.userInfo " +
            "where team.id =:id and team.userInfo.login = ?#{authentication.name}"
    )
    Optional<Team> findOneWithToOneRelationshipsByUser(@Param("id") Long id);
}
