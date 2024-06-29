package org.nmcpye.datarun.repository;

import java.util.List;
import java.util.Optional;
import org.nmcpye.datarun.domain.ItnsVillage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ItnsVillage entity.
 */
@Repository
public interface    ItnsVillageRepository extends JpaRepository<ItnsVillage, Long> {
    default Optional<ItnsVillage> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<ItnsVillage> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<ItnsVillage> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select itnsVillage from ItnsVillage itnsVillage left join fetch itnsVillage.progressStatus left join fetch itnsVillage.team left join fetch itnsVillage.assignment left join fetch itnsVillage.activity",
        countQuery = "select count(itnsVillage) from ItnsVillage itnsVillage"
    )
    Page<ItnsVillage> findAllWithToOneRelationships(Pageable pageable);

    @Query(
        "select itnsVillage from ItnsVillage itnsVillage left join fetch itnsVillage.progressStatus left join fetch itnsVillage.team left join fetch itnsVillage.assignment left join fetch itnsVillage.activity"
    )
    List<ItnsVillage> findAllWithToOneRelationships();

    @Query(
        "select itnsVillage from ItnsVillage itnsVillage left join fetch itnsVillage.progressStatus left join fetch itnsVillage.team left join fetch itnsVillage.assignment left join fetch itnsVillage.activity where itnsVillage.id =:id"
    )
    Optional<ItnsVillage> findOneWithToOneRelationships(@Param("id") Long id);
}
