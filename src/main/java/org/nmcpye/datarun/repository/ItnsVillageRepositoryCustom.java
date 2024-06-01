package org.nmcpye.datarun.repository;

import org.nmcpye.datarun.domain.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for the ItnsVillage entity.
 */
@Repository
public interface ItnsVillageRepositoryCustom
    extends ItnsVillageRepositoryWithBagRelationships, ItnsVillageRepository {

    Optional<ItnsVillage> findByUid(String uid);

    default Optional<ItnsVillage> findOneWithEagerRelationshipsByUser(Long id) {
        return this.fetchBagRelationships(this.findOneWithToOneRelationshipsByUser(id));
    }

    default List<ItnsVillage> findAllWithEagerRelationshipsByUser() {
        return this.fetchBagRelationships(this.findAllWithToOneRelationshipsByUser());
    }

    default Page<ItnsVillage> findAllWithEagerRelationshipsByUser(Pageable pageable) {
        return this.fetchBagRelationships(this.findAllWithToOneRelationshipsByUser(pageable));
    }

    @Query(
        value = "select itnsVillage from ItnsVillage itnsVillage " +
            "left join itnsVillage.progressStatus " +
            "left join itnsVillage.team " +
            "left join itnsVillage.assignment " +
            "left join itnsVillage.activity " +
            "WHERE itnsVillage.team.userInfo.login = ?#{authentication.name}",
        countQuery = "select count(itnsVillage) from ItnsVillage itnsVillage " +
            "WHERE itnsVillage.team.userInfo.login = ?#{authentication.name}"
    )
    Page<ItnsVillage> findAllByUser(Pageable pageable);

    @Query(
        value = "select itnsVillage from ItnsVillage itnsVillage " +
            "left join fetch itnsVillage.progressStatus " +
            "left join fetch itnsVillage.team " +
            "left join fetch itnsVillage.assignment " +
            "left join fetch itnsVillage.activity " +
            "WHERE itnsVillage.team.userInfo.login = ?#{authentication.name}",
        countQuery = "select count(itnsVillage) from ItnsVillage itnsVillage " +
            "WHERE itnsVillage.team.userInfo.login = ?#{authentication.name}"
    )
    Page<ItnsVillage> findAllWithToOneRelationshipsByUser(Pageable pageable);

    @Query(
        "select itnsVillage from ItnsVillage itnsVillage " +
            "left join fetch itnsVillage.progressStatus " +
            "left join fetch itnsVillage.team " +
            "left join fetch itnsVillage.assignment " +
            "left join fetch itnsVillage.activity WHERE itnsVillage.team.userInfo.login = ?#{authentication.name}"
    )
    List<ItnsVillage> findAllWithToOneRelationshipsByUser();

    @Query(
        "select itnsVillage from ItnsVillage itnsVillage " +
            "left join fetch itnsVillage.progressStatus " +
            "left join fetch itnsVillage.team " +
            "left join fetch itnsVillage.assignment " +
            "left join fetch itnsVillage.activity where itnsVillage.id =:id and itnsVillage.team.userInfo.login = ?#{authentication.name}"
    )
    Optional<ItnsVillage> findOneWithToOneRelationshipsByUser(@Param("id") Long id);
}

