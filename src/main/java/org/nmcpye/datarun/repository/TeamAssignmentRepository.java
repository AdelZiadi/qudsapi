package org.nmcpye.datarun.repository;

import java.util.List;
import java.util.Optional;
import org.nmcpye.datarun.domain.TeamAssignment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TeamAssignment entity.
 */
@Repository
public interface TeamAssignmentRepository extends JpaRepository<TeamAssignment, Long> {
    default Optional<TeamAssignment> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<TeamAssignment> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<TeamAssignment> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select teamAssignment from TeamAssignment teamAssignment left join fetch teamAssignment.campaign left join fetch teamAssignment.location left join fetch teamAssignment.team left join fetch teamAssignment.warehouse",
        countQuery = "select count(teamAssignment) from TeamAssignment teamAssignment"
    )
    Page<TeamAssignment> findAllWithToOneRelationships(Pageable pageable);

    @Query(
        "select teamAssignment from TeamAssignment teamAssignment left join fetch teamAssignment.campaign left join fetch teamAssignment.location left join fetch teamAssignment.team left join fetch teamAssignment.warehouse"
    )
    List<TeamAssignment> findAllWithToOneRelationships();

    @Query(
        "select teamAssignment from TeamAssignment teamAssignment left join fetch teamAssignment.campaign left join fetch teamAssignment.location left join fetch teamAssignment.team left join fetch teamAssignment.warehouse where teamAssignment.id =:id"
    )
    Optional<TeamAssignment> findOneWithToOneRelationships(@Param("id") Long id);
}
