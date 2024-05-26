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

    default Optional<ItnsVillage> findOneWithEagerRelationshipsByUser(Long id, String login) {
        return this.fetchBagRelationships(this.findOneWithToOneRelationshipsByUser(id, login));
    }

    default List<ItnsVillage> findAllWithEagerRelationshipsByUser(String login) {
        return this.fetchBagRelationships(this.findAllWithToOneRelationshipsByUser(login));
    }

    default Page<ItnsVillage> findAllWithEagerRelationshipsByUser(Pageable pageable, String login) {
        return this.fetchBagRelationships(this.findAllWithToOneRelationshipsByUser(pageable, login));
    }

    @Query(
        value = "select itnsVillage from ItnsVillage itnsVillage " +
            "left join fetch itnsVillage.progressStatus " +
            "left join fetch itnsVillage.team " +
            "left join fetch itnsVillage.assignment " +
            "left join fetch itnsVillage.activity " +
            "WHERE itnsVillage.team.userInfo.login = :login",
        countQuery = "select count(itnsVillage) from ItnsVillage itnsVillage " +
            "WHERE itnsVillage.team.userInfo.login = :login"
    )
    Page<ItnsVillage> findAllWithToOneRelationshipsByUser(Pageable pageable, @Param("login") String login);

    @Query(
        "select itnsVillage from ItnsVillage itnsVillage " +
            "left join fetch itnsVillage.progressStatus " +
            "left join fetch itnsVillage.team " +
            "left join fetch itnsVillage.assignment " +
            "left join fetch itnsVillage.activity WHERE itnsVillage.team.userInfo.login = :login"
    )
    List<ItnsVillage> findAllWithToOneRelationshipsByUser(@Param("login") String login);

    @Query(
        "select itnsVillage from ItnsVillage itnsVillage " +
            "left join fetch itnsVillage.progressStatus " +
            "left join fetch itnsVillage.team " +
            "left join fetch itnsVillage.assignment " +
            "left join fetch itnsVillage.activity where itnsVillage.id =:id and itnsVillage.team.userInfo.login = :login"
    )
    Optional<ItnsVillage> findOneWithToOneRelationshipsByUser(@Param("id") Long id, @Param("login") String login);
}

