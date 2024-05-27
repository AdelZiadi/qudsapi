package org.nmcpye.datarun.web.rest;

import org.nmcpye.datarun.domain.WarehouseTransaction;
import org.nmcpye.datarun.repository.WarehouseTransactionRepositoryCustom;
import org.nmcpye.datarun.service.WarehouseTransactionServiceCustom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

import java.util.List;
import java.util.Optional;

/**
 * REST custom controller for managing {@link WarehouseTransaction}.
 */
@RestController
@RequestMapping("/api/custom/warehouse-transactions")
public class WarehouseTransactionResourceCustom extends WarehouseTransactionResource {

    private final Logger log = LoggerFactory.getLogger(WarehouseTransactionResourceCustom.class);

    private final WarehouseTransactionServiceCustom warehouseTransactionService;

    private final WarehouseTransactionRepositoryCustom warehouseTransactionRepository;

    public WarehouseTransactionResourceCustom(
        WarehouseTransactionServiceCustom warehouseTransactionService,
        WarehouseTransactionRepositoryCustom warehouseTransactionRepository
    ) {
        super(warehouseTransactionService, warehouseTransactionRepository);
        this.warehouseTransactionService = warehouseTransactionService;
        this.warehouseTransactionRepository = warehouseTransactionRepository;
    }

    /**
     * {@code GET  /warehouse-transactions} : get all the warehouseTransactions.
     *
     * @param pageable  the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of warehouseTransactions in body.
     */
    @GetMapping("user")
    public ResponseEntity<List<WarehouseTransaction>> getAllByUser(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get a page of WarehouseTransactions");
        Page<WarehouseTransaction> page;
        if (eagerload) {
            page = warehouseTransactionService.findAllWithEagerRelationships(pageable);
        } else {
            page = warehouseTransactionService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /warehouse-transactions/:id} : get the "id" warehouseTransaction.
     *
     * @param id the id of the warehouseTransaction to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the warehouseTransaction, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user/{id}")
    public ResponseEntity<WarehouseTransaction> getOneByUser(@PathVariable("id") Long id) {
        log.debug("REST request to get WarehouseTransaction : {}", id);
        Optional<WarehouseTransaction> warehouseTransaction = warehouseTransactionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(warehouseTransaction);
    }
}
