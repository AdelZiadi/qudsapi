package org.nmcpye.datarun.repository;

import java.util.List;
import java.util.Optional;
import org.nmcpye.datarun.domain.Assignment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Assignment entity.
 */
@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    default Optional<Assignment> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<Assignment> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<Assignment> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select assignment from Assignment assignment left join fetch assignment.activity left join fetch assignment.location left join fetch assignment.team left join fetch assignment.warehouse",
        countQuery = "select count(assignment) from Assignment assignment"
    )
    Page<Assignment> findAllWithToOneRelationships(Pageable pageable);

    @Query(
        "select assignment from Assignment assignment left join fetch assignment.activity left join fetch assignment.location left join fetch assignment.team left join fetch assignment.warehouse"
    )
    List<Assignment> findAllWithToOneRelationships();

    @Query(
        "select assignment from Assignment assignment left join fetch assignment.activity left join fetch assignment.location left join fetch assignment.team left join fetch assignment.warehouse where assignment.id =:id"
    )
    Optional<Assignment> findOneWithToOneRelationships(@Param("id") Long id);
}
