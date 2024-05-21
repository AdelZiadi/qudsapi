package org.nmcpye.datarun.web.rest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.nmcpye.datarun.domain.ItnsVillage;
import org.nmcpye.datarun.repository.ItnsVillageRepository;
import org.nmcpye.datarun.service.ItnsVillageService;
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
 * REST controller for managing {@link org.nmcpye.datarun.domain.ItnsVillage}.
 */
@RestController
@RequestMapping("/api/itns-villages")
public class ItnsVillageResource {

    private final Logger log = LoggerFactory.getLogger(ItnsVillageResource.class);

    private static final String ENTITY_NAME = "itnsVillage";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ItnsVillageService itnsVillageService;

    private final ItnsVillageRepository itnsVillageRepository;

    public ItnsVillageResource(ItnsVillageService itnsVillageService, ItnsVillageRepository itnsVillageRepository) {
        this.itnsVillageService = itnsVillageService;
        this.itnsVillageRepository = itnsVillageRepository;
    }

    /**
     * {@code POST  /itns-villages} : Create a new itnsVillage.
     *
     * @param itnsVillage the itnsVillage to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new itnsVillage, or with status {@code 400 (Bad Request)} if the itnsVillage has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ItnsVillage> createItnsVillage(@Valid @RequestBody ItnsVillage itnsVillage) throws URISyntaxException {
        log.debug("REST request to save ItnsVillage : {}", itnsVillage);
        if (itnsVillage.getId() != null) {
            throw new BadRequestAlertException("A new itnsVillage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        itnsVillage = itnsVillageService.save(itnsVillage);
        return ResponseEntity.created(new URI("/api/itns-villages/" + itnsVillage.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, itnsVillage.getId().toString()))
            .body(itnsVillage);
    }

    /**
     * {@code PUT  /itns-villages/:id} : Updates an existing itnsVillage.
     *
     * @param id the id of the itnsVillage to save.
     * @param itnsVillage the itnsVillage to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated itnsVillage,
     * or with status {@code 400 (Bad Request)} if the itnsVillage is not valid,
     * or with status {@code 500 (Internal Server Error)} if the itnsVillage couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ItnsVillage> updateItnsVillage(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ItnsVillage itnsVillage
    ) throws URISyntaxException {
        log.debug("REST request to update ItnsVillage : {}, {}", id, itnsVillage);
        if (itnsVillage.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, itnsVillage.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!itnsVillageRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        itnsVillage = itnsVillageService.update(itnsVillage);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, itnsVillage.getId().toString()))
            .body(itnsVillage);
    }

    /**
     * {@code PATCH  /itns-villages/:id} : Partial updates given fields of an existing itnsVillage, field will ignore if it is null
     *
     * @param id the id of the itnsVillage to save.
     * @param itnsVillage the itnsVillage to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated itnsVillage,
     * or with status {@code 400 (Bad Request)} if the itnsVillage is not valid,
     * or with status {@code 404 (Not Found)} if the itnsVillage is not found,
     * or with status {@code 500 (Internal Server Error)} if the itnsVillage couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ItnsVillage> partialUpdateItnsVillage(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ItnsVillage itnsVillage
    ) throws URISyntaxException {
        log.debug("REST request to partial update ItnsVillage partially : {}, {}", id, itnsVillage);
        if (itnsVillage.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, itnsVillage.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!itnsVillageRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ItnsVillage> result = itnsVillageService.partialUpdate(itnsVillage);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, itnsVillage.getId().toString())
        );
    }

    /**
     * {@code GET  /itns-villages} : get all the itnsVillages.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of itnsVillages in body.
     */
    @GetMapping("")
    public ResponseEntity<List<ItnsVillage>> getAllItnsVillages(
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
    @GetMapping("/{id}")
    public ResponseEntity<ItnsVillage> getItnsVillage(@PathVariable("id") Long id) {
        log.debug("REST request to get ItnsVillage : {}", id);
        Optional<ItnsVillage> itnsVillage = itnsVillageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(itnsVillage);
    }

    /**
     * {@code DELETE  /itns-villages/:id} : delete the "id" itnsVillage.
     *
     * @param id the id of the itnsVillage to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItnsVillage(@PathVariable("id") Long id) {
        log.debug("REST request to delete ItnsVillage : {}", id);
        itnsVillageService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
