package org.nmcpye.datarun.repository;

import java.util.List;
import java.util.Optional;
import org.nmcpye.datarun.domain.WarehouseTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the WarehouseTransaction entity.
 */
@Repository
public interface WarehouseTransactionRepository extends JpaRepository<WarehouseTransaction, Long> {
    default Optional<WarehouseTransaction> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<WarehouseTransaction> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<WarehouseTransaction> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select warehouseTransaction from WarehouseTransaction warehouseTransaction left join fetch warehouseTransaction.item left join fetch warehouseTransaction.sourceWarehouse left join fetch warehouseTransaction.team left join fetch warehouseTransaction.warehouse left join fetch warehouseTransaction.activity",
        countQuery = "select count(warehouseTransaction) from WarehouseTransaction warehouseTransaction"
    )
    Page<WarehouseTransaction> findAllWithToOneRelationships(Pageable pageable);

    @Query(
        "select warehouseTransaction from WarehouseTransaction warehouseTransaction left join fetch warehouseTransaction.item left join fetch warehouseTransaction.sourceWarehouse left join fetch warehouseTransaction.team left join fetch warehouseTransaction.warehouse left join fetch warehouseTransaction.activity"
    )
    List<WarehouseTransaction> findAllWithToOneRelationships();

    @Query(
        "select warehouseTransaction from WarehouseTransaction warehouseTransaction left join fetch warehouseTransaction.item left join fetch warehouseTransaction.sourceWarehouse left join fetch warehouseTransaction.team left join fetch warehouseTransaction.warehouse left join fetch warehouseTransaction.activity where warehouseTransaction.id =:id"
    )
    Optional<WarehouseTransaction> findOneWithToOneRelationships(@Param("id") Long id);
}
