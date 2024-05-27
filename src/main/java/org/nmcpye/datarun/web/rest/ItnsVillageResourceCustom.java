package org.nmcpye.datarun.web.rest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.nmcpye.datarun.domain.ItnsVillage;
import org.nmcpye.datarun.repository.ItnsVillageRepository;
import org.nmcpye.datarun.repository.ItnsVillageRepositoryCustom;
import org.nmcpye.datarun.service.ItnsVillageService;
import org.nmcpye.datarun.service.ItnsVillageServiceCustom;
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
 * REST controller for managing {@link ItnsVillage}.
 */
@RestController
@RequestMapping("/api/custom/itns-villages")
public class ItnsVillageResourceCustom extends ItnsVillageResource {

    private final Logger log = LoggerFactory.getLogger(ItnsVillageResourceCustom.class);

    private static final String ENTITY_NAME = "itnsVillage";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ItnsVillageServiceCustom itnsVillageService;

    private final ItnsVillageRepositoryCustom itnsVillageRepository;

    public ItnsVillageResourceCustom(ItnsVillageServiceCustom itnsVillageService,
                                     ItnsVillageRepositoryCustom itnsVillageRepository) {
        super(itnsVillageService, itnsVillageRepository);
        this.itnsVillageService = itnsVillageService;
        this.itnsVillageRepository = itnsVillageRepository;
    }

    /**
     * {@code GET  /itns-villages} : get all the itnsVillages.
     *
     * @param pageable  the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of itnsVillages in body.
     */
    @GetMapping("user")
    public ResponseEntity<List<ItnsVillage>> getAllByUser(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get a page of ItnsVillages");
        Page<ItnsVillage> page;
        if (eagerload) {
            page = itnsVillageService.findAllWithEagerRelationships(pageable);
        } else {
            page = itnsVillageService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /itns-villages/:id} : get the "id" itnsVillage.
     *
     * @param id the id of the itnsVillage to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the itnsVillage, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user/{id}")
    public ResponseEntity<ItnsVillage> getOneByUser(@PathVariable("id") Long id) {
        log.debug("REST request to get ItnsVillage : {}", id);
        Optional<ItnsVillage> itnsVillage = itnsVillageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(itnsVillage);
    }
}
