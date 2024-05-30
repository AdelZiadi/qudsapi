package org.nmcpye.datarun.web.rest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.nmcpye.datarun.domain.MVillagesLocations;
import org.nmcpye.datarun.repository.MVillagesLocationsRepository;
import org.nmcpye.datarun.service.MVillagesLocationsService;
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
 * REST controller for managing {@link org.nmcpye.datarun.domain.MVillagesLocations}.
 */
@RestController
@RequestMapping("/api/m-villages-locations")
public class MVillagesLocationsResource {

    private final Logger log = LoggerFactory.getLogger(MVillagesLocationsResource.class);

    private static final String ENTITY_NAME = "mVillagesLocations";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MVillagesLocationsService mVillagesLocationsService;

    private final MVillagesLocationsRepository mVillagesLocationsRepository;

    public MVillagesLocationsResource(
        MVillagesLocationsService mVillagesLocationsService,
        MVillagesLocationsRepository mVillagesLocationsRepository
    ) {
        this.mVillagesLocationsService = mVillagesLocationsService;
        this.mVillagesLocationsRepository = mVillagesLocationsRepository;
    }

    /**
     * {@code POST  /m-villages-locations} : Create a new mVillagesLocations.
     *
     * @param mVillagesLocations the mVillagesLocations to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mVillagesLocations, or with status {@code 400 (Bad Request)} if the mVillagesLocations has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<MVillagesLocations> createMVillagesLocations(@Valid @RequestBody MVillagesLocations mVillagesLocations)
        throws URISyntaxException {
        log.debug("REST request to save MVillagesLocations : {}", mVillagesLocations);
        if (mVillagesLocations.getId() != null) {
            throw new BadRequestAlertException("A new mVillagesLocations cannot already have an ID", ENTITY_NAME, "idexists");
        }
        mVillagesLocations = mVillagesLocationsService.save(mVillagesLocations);
        return ResponseEntity.created(new URI("/api/m-villages-locations/" + mVillagesLocations.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, mVillagesLocations.getId().toString()))
            .body(mVillagesLocations);
    }

    /**
     * {@code PUT  /m-villages-locations/:id} : Updates an existing mVillagesLocations.
     *
     * @param id the id of the mVillagesLocations to save.
     * @param mVillagesLocations the mVillagesLocations to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mVillagesLocations,
     * or with status {@code 400 (Bad Request)} if the mVillagesLocations is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mVillagesLocations couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<MVillagesLocations> updateMVillagesLocations(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody MVillagesLocations mVillagesLocations
    ) throws URISyntaxException {
        log.debug("REST request to update MVillagesLocations : {}, {}", id, mVillagesLocations);
        if (mVillagesLocations.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, mVillagesLocations.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!mVillagesLocationsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        mVillagesLocations = mVillagesLocationsService.update(mVillagesLocations);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, mVillagesLocations.getId().toString()))
            .body(mVillagesLocations);
    }

    /**
     * {@code PATCH  /m-villages-locations/:id} : Partial updates given fields of an existing mVillagesLocations, field will ignore if it is null
     *
     * @param id the id of the mVillagesLocations to save.
     * @param mVillagesLocations the mVillagesLocations to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mVillagesLocations,
     * or with status {@code 400 (Bad Request)} if the mVillagesLocations is not valid,
     * or with status {@code 404 (Not Found)} if the mVillagesLocations is not found,
     * or with status {@code 500 (Internal Server Error)} if the mVillagesLocations couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<MVillagesLocations> partialUpdateMVillagesLocations(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody MVillagesLocations mVillagesLocations
    ) throws URISyntaxException {
        log.debug("REST request to partial update MVillagesLocations partially : {}, {}", id, mVillagesLocations);
        if (mVillagesLocations.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, mVillagesLocations.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!mVillagesLocationsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<MVillagesLocations> result = mVillagesLocationsService.partialUpdate(mVillagesLocations);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, mVillagesLocations.getId().toString())
        );
    }

    /**
     * {@code GET  /m-villages-locations} : get all the mVillagesLocations.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mVillagesLocations in body.
     */
    @GetMapping("")
    public ResponseEntity<List<MVillagesLocations>> getAllMVillagesLocations(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of MVillagesLocations");
        Page<MVillagesLocations> page = mVillagesLocationsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /m-villages-locations/:id} : get the "id" mVillagesLocations.
     *
     * @param id the id of the mVillagesLocations to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mVillagesLocations, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<MVillagesLocations> getMVillagesLocations(@PathVariable("id") Long id) {
        log.debug("REST request to get MVillagesLocations : {}", id);
        Optional<MVillagesLocations> mVillagesLocations = mVillagesLocationsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mVillagesLocations);
    }

    /**
     * {@code DELETE  /m-villages-locations/:id} : delete the "id" mVillagesLocations.
     *
     * @param id the id of the mVillagesLocations to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMVillagesLocations(@PathVariable("id") Long id) {
        log.debug("REST request to delete MVillagesLocations : {}", id);
        mVillagesLocationsService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
