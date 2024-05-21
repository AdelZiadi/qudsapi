package org.nmcpye.datarun.web.rest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.nmcpye.datarun.domain.WarehouseTransaction;
import org.nmcpye.datarun.repository.WarehouseTransactionRepository;
import org.nmcpye.datarun.service.WarehouseTransactionService;
import org.nmcpye.datarun.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.nmcpye.datarun.domain.WarehouseTransaction}.
 */
@RestController
@RequestMapping("/api/warehouse-transactions")
public class WarehouseTransactionResource {

    private final Logger log = LoggerFactory.getLogger(WarehouseTransactionResource.class);

    private static final String ENTITY_NAME = "warehouseTransaction";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WarehouseTransactionService warehouseTransactionService;

    private final WarehouseTransactionRepository warehouseTransactionRepository;

    public WarehouseTransactionResource(
        WarehouseTransactionService warehouseTransactionService,
        WarehouseTransactionRepository warehouseTransactionRepository
    ) {
        this.warehouseTransactionService = warehouseTransactionService;
        this.warehouseTransactionRepository = warehouseTransactionRepository;
    }

    /**
     * {@code POST  /warehouse-transactions} : Create a new warehouseTransaction.
     *
     * @param warehouseTransaction the warehouseTransaction to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new warehouseTransaction, or with status {@code 400 (Bad Request)} if the warehouseTransaction has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<WarehouseTransaction> createWarehouseTransaction(@Valid @RequestBody WarehouseTransaction warehouseTransaction)
        throws URISyntaxException {
        log.debug("REST request to save WarehouseTransaction : {}", warehouseTransaction);
        if (warehouseTransaction.getId() != null) {
            throw new BadRequestAlertException("A new warehouseTransaction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        warehouseTransaction = warehouseTransactionService.save(warehouseTransaction);
        return ResponseEntity.created(new URI("/api/warehouse-transactions/" + warehouseTransaction.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, warehouseTransaction.getId().toString()))
            .body(warehouseTransaction);
    }

    /**
     * {@code PUT  /warehouse-transactions/:id} : Updates an existing warehouseTransaction.
     *
     * @param id the id of the warehouseTransaction to save.
     * @param warehouseTransaction the warehouseTransaction to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated warehouseTransaction,
     * or with status {@code 400 (Bad Request)} if the warehouseTransaction is not valid,
     * or with status {@code 500 (Internal Server Error)} if the warehouseTransaction couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<WarehouseTransaction> updateWarehouseTransaction(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody WarehouseTransaction warehouseTransaction
    ) throws URISyntaxException {
        log.debug("REST request to update WarehouseTransaction : {}, {}", id, warehouseTransaction);
        if (warehouseTransaction.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, warehouseTransaction.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!warehouseTransactionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        warehouseTransaction = warehouseTransactionService.update(warehouseTransaction);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, warehouseTransaction.getId().toString()))
            .body(warehouseTransaction);
    }

    /**
     * {@code PATCH  /warehouse-transactions/:id} : Partial updates given fields of an existing warehouseTransaction, field will ignore if it is null
     *
     * @param id the id of the warehouseTransaction to save.
     * @param warehouseTransaction the warehouseTransaction to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated warehouseTransaction,
     * or with status {@code 400 (Bad Request)} if the warehouseTransaction is not valid,
     * or with status {@code 404 (Not Found)} if the warehouseTransaction is not found,
     * or with status {@code 500 (Internal Server Error)} if the warehouseTransaction couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<WarehouseTransaction> partialUpdateWarehouseTransaction(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody WarehouseTransaction warehouseTransaction
    ) throws URISyntaxException {
        log.debug("REST request to partial update WarehouseTransaction partially : {}, {}", id, warehouseTransaction);
        if (warehouseTransaction.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, warehouseTransaction.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!warehouseTransactionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<WarehouseTransaction> result = warehouseTransactionService.partialUpdate(warehouseTransaction);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, warehouseTransaction.getId().toString())
        );
    }

    /**
     * {@code GET  /warehouse-transactions} : get all the warehouseTransactions.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of warehouseTransactions in body.
     */
    @GetMapping("")
    public ResponseEntity<List<WarehouseTransaction>> getAllWarehouseTransactions(
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
    @GetMapping("/{id}")
    public ResponseEntity<WarehouseTransaction> getWarehouseTransaction(@PathVariable("id") Long id) {
        log.debug("REST request to get WarehouseTransaction : {}", id);
        Optional<WarehouseTransaction> warehouseTransaction = warehouseTransactionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(warehouseTransaction);
    }

    /**
     * {@code DELETE  /warehouse-transactions/:id} : delete the "id" warehouseTransaction.
     *
     * @param id the id of the warehouseTransaction to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWarehouseTransaction(@PathVariable("id") Long id) {
        log.debug("REST request to delete WarehouseTransaction : {}", id);
        warehouseTransactionService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
