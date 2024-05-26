package org.nmcpye.datarun.web.rest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.nmcpye.datarun.domain.Warehouse;
import org.nmcpye.datarun.repository.WarehouseRepository;
import org.nmcpye.datarun.repository.WarehouseRepositoryCustom;
import org.nmcpye.datarun.service.WarehouseService;
import org.nmcpye.datarun.service.WarehouseServiceCustom;
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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * REST controller for managing {@link Warehouse}.
 */
@RestController
@RequestMapping("/api/custom/warehouses")
public class WarehouseResourceCustom extends WarehouseResource {

    private final Logger log = LoggerFactory.getLogger(WarehouseResourceCustom.class);

    private final WarehouseServiceCustom warehouseService;

    private final WarehouseRepositoryCustom warehouseRepository;

    public WarehouseResourceCustom(WarehouseServiceCustom warehouseService,
                                   WarehouseRepositoryCustom warehouseRepository) {
        super(warehouseService, warehouseRepository);
        this.warehouseService = warehouseService;
        this.warehouseRepository = warehouseRepository;
    }

    /**
     * {@code GET  /warehouses} : get all the warehouses.
     *
     * @param pageable  the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of warehouses in body.
     */
    @GetMapping("user")
    public ResponseEntity<List<Warehouse>> getAllByCurrentUser(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get a page of Warehouses By user");
        Page<Warehouse> page;
        page = warehouseService.findByCurrentUser(pageable);

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
