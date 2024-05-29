package org.nmcpye.datarun.repository;

import org.nmcpye.datarun.domain.Warehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for the Warehouse entity.
 */
@Repository
public interface WarehouseRepositoryCustom extends WarehouseRepository {
//    @Query("SELECT warehouse FROM Warehouse warehouse " +
//        "WHERE EXISTS (SELECT t FROM Team t WHERE t.userInfo.login = ?#{authentication.name} AND t.warehouse = warehouse)")
//    Page<Warehouse> findByCurrentUser(Pageable pageable);

    default Optional<Warehouse> findOneWithEagerRelationshipsByUser(Long id) {
        return this.findOneWithToOneRelationshipsByUser(id);
    }

    default List<Warehouse> findAllWithEagerRelationshipsByUser() {
        return this.findAllWithToOneRelationships();
    }

    default Page<Warehouse> findAllWithEagerRelationshipsByUser(Pageable pageable) {
        return this.findAllWithToOneRelationshipsByUser(pageable);
    }

    @Query(
        value = "select warehouse from Warehouse warehouse " +
            "left join warehouse.activity " +
            "WHERE EXISTS (SELECT t FROM Team t " +
            "WHERE t.userInfo.login = ?#{authentication.name} AND t.warehouse = warehouse)",
        countQuery = "select count(warehouse) from Warehouse warehouse " +
            "WHERE EXISTS (SELECT t FROM Team t " +
            "WHERE t.userInfo.login = ?#{authentication.name} AND t.warehouse = warehouse)"
    )
    Page<Warehouse> findAllByUser(Pageable pageable);

    @Query(
        value = "select warehouse from Warehouse warehouse " +
                "left join fetch warehouse.activity " +
                "WHERE EXISTS (SELECT t FROM Team t " +
                "WHERE t.userInfo.login = ?#{authentication.name} AND t.warehouse = warehouse)",
        countQuery = "select count(warehouse) from Warehouse warehouse " +
            "WHERE EXISTS (SELECT t FROM Team t " +
            "WHERE t.userInfo.login = ?#{authentication.name} AND t.warehouse = warehouse)"
    )
    Page<Warehouse> findAllWithToOneRelationshipsByUser(Pageable pageable);

    @Query("select warehouse from Warehouse warehouse " +
        "left join fetch warehouse.activity " +
        "WHERE EXISTS (SELECT t FROM Team t " +
        "WHERE t.userInfo.login = ?#{authentication.name} AND t.warehouse = warehouse)")
    List<Warehouse> findAllWithToOneRelationshipsByUser();

    @Query("select warehouse from Warehouse warehouse " +
        "left join fetch warehouse.activity " +
        "where warehouse.id =:id and " +
        "EXISTS (SELECT t FROM Team t " +
        "WHERE t.userInfo.login = ?#{authentication.name} AND t.warehouse = warehouse)")
    Optional<Warehouse> findOneWithToOneRelationshipsByUser(@Param("id") Long id);
}
