package org.nmcpye.datarun.service;

import java.util.Optional;
import org.nmcpye.datarun.domain.WarehouseTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link org.nmcpye.datarun.domain.WarehouseTransaction}.
 */
public interface WarehouseTransactionService {
    /**
     * Save a warehouseTransaction.
     *
     * @param warehouseTransaction the entity to save.
     * @return the persisted entity.
     */
    WarehouseTransaction save(WarehouseTransaction warehouseTransaction);

    /**
     * Updates a warehouseTransaction.
     *
     * @param warehouseTransaction the entity to update.
     * @return the persisted entity.
     */
    WarehouseTransaction update(WarehouseTransaction warehouseTransaction);

    /**
     * Partially updates a warehouseTransaction.
     *
     * @param warehouseTransaction the entity to update partially.
     * @return the persisted entity.
     */
    Optional<WarehouseTransaction> partialUpdate(WarehouseTransaction warehouseTransaction);

    /**
     * Get all the warehouseTransactions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<WarehouseTransaction> findAll(Pageable pageable);

    /**
     * Get all the warehouseTransactions with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<WarehouseTransaction> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" warehouseTransaction.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WarehouseTransaction> findOne(Long id);

    /**
     * Delete the "id" warehouseTransaction.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
