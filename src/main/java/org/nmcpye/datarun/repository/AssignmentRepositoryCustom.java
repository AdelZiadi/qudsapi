package org.nmcpye.datarun.repository;

import org.nmcpye.datarun.domain.Assignment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssignmentRepositoryCustom extends AssignmentRepository {
    @Query(
        value = "SELECT a FROM Assignment a WHERE a.team.userInfo.login = :login and a.activity.active = true",
        countQuery = "select count(assignment) from Assignment assignment"
    )
    Page<Assignment> findByCurrentUser(@Param("login") String login,
                                       Pageable pageable);

    default Optional<Assignment> findOneWithEagerRelationshipsByUser(Long id, String login) {
        return this.findOneWithToOneRelationshipsByUser(id, login);
    }

    default List<Assignment> findAllWithEagerRelationshipsByUser(String login) {
        return this.findAllWithToOneRelationshipsByUser(login);
    }

    default Page<Assignment> findAllWithEagerRelationshipsByUser(Pageable pageable, String login) {
        return this.findAllWithToOneRelationshipsByUser(pageable, login);
    }

    @Query(
        value = "select assignment from Assignment assignment " +
            "left join fetch assignment.activity " +
            "left join fetch assignment.organisationUnit " +
            "left join fetch assignment.team " +
            "left join fetch assignment.warehouse " +
            "where assignment.team.userInfo.login = :login",
        countQuery = "select count(assignment) from Assignment assignment " +
            "where assignment.team.userInfo.login = :login"
    )
    Page<Assignment> findAllWithToOneRelationshipsByUser(Pageable pageable, @Param("login") String login);

    @Query(
        "select assignment from Assignment assignment " +
            "left join fetch assignment.activity " +
            "left join fetch assignment.organisationUnit " +
            "left join fetch assignment.team " +
            "left join fetch assignment.warehouse " +
            "where assignment.team.userInfo.login = :login"
    )
    List<Assignment> findAllWithToOneRelationshipsByUser(@Param("login") String login);

    @Query(
        "select assignment from Assignment assignment " +
            "left join fetch assignment.activity " +
            "left join fetch assignment.organisationUnit " +
            "left join fetch assignment.team " +
            "left join fetch assignment.warehouse " +
            "where assignment.id =:id and assignment.team.userInfo.login = :login"
    )
    Optional<Assignment> findOneWithToOneRelationshipsByUser(@Param("id") Long id, @Param("login") String login);
}
