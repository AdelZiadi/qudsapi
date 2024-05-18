package org.nmcpye.datarun.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.nmcpye.datarun.domain.ProgressStatus;
import org.nmcpye.datarun.repository.ProgressStatusRepository;
import org.nmcpye.datarun.service.ProgressStatusService;
import org.nmcpye.datarun.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.nmcpye.datarun.domain.ProgressStatus}.
 */
@RestController
@RequestMapping("/api/progress-statuses")
public class ProgressStatusResource {

    private final Logger log = LoggerFactory.getLogger(ProgressStatusResource.class);

    private static final String ENTITY_NAME = "progressStatus";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProgressStatusService progressStatusService;

    private final ProgressStatusRepository progressStatusRepository;

    public ProgressStatusResource(ProgressStatusService progressStatusService, ProgressStatusRepository progressStatusRepository) {
        this.progressStatusService = progressStatusService;
        this.progressStatusRepository = progressStatusRepository;
    }

    /**
     * {@code POST  /progress-statuses} : Create a new progressStatus.
     *
     * @param progressStatus the progressStatus to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new progressStatus, or with status {@code 400 (Bad Request)} if the progressStatus has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ProgressStatus> createProgressStatus(@RequestBody ProgressStatus progressStatus) throws URISyntaxException {
        log.debug("REST request to save ProgressStatus : {}", progressStatus);
        if (progressStatus.getId() != null) {
            throw new BadRequestAlertException("A new progressStatus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        progressStatus = progressStatusService.save(progressStatus);
        return ResponseEntity.created(new URI("/api/progress-statuses/" + progressStatus.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, progressStatus.getId().toString()))
            .body(progressStatus);
    }

    /**
     * {@code PUT  /progress-statuses/:id} : Updates an existing progressStatus.
     *
     * @param id the id of the progressStatus to save.
     * @param progressStatus the progressStatus to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated progressStatus,
     * or with status {@code 400 (Bad Request)} if the progressStatus is not valid,
     * or with status {@code 500 (Internal Server Error)} if the progressStatus couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProgressStatus> updateProgressStatus(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ProgressStatus progressStatus
    ) throws URISyntaxException {
        log.debug("REST request to update ProgressStatus : {}, {}", id, progressStatus);
        if (progressStatus.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, progressStatus.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!progressStatusRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        progressStatus = progressStatusService.update(progressStatus);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, progressStatus.getId().toString()))
            .body(progressStatus);
    }

    /**
     * {@code PATCH  /progress-statuses/:id} : Partial updates given fields of an existing progressStatus, field will ignore if it is null
     *
     * @param id the id of the progressStatus to save.
     * @param progressStatus the progressStatus to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated progressStatus,
     * or with status {@code 400 (Bad Request)} if the progressStatus is not valid,
     * or with status {@code 404 (Not Found)} if the progressStatus is not found,
     * or with status {@code 500 (Internal Server Error)} if the progressStatus couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ProgressStatus> partialUpdateProgressStatus(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ProgressStatus progressStatus
    ) throws URISyntaxException {
        log.debug("REST request to partial update ProgressStatus partially : {}, {}", id, progressStatus);
        if (progressStatus.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, progressStatus.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!progressStatusRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ProgressStatus> result = progressStatusService.partialUpdate(progressStatus);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, progressStatus.getId().toString())
        );
    }

    /**
     * {@code GET  /progress-statuses} : get all the progressStatuses.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of progressStatuses in body.
     */
    @GetMapping("")
    public List<ProgressStatus> getAllProgressStatuses() {
        log.debug("REST request to get all ProgressStatuses");
        return progressStatusService.findAll();
    }

    /**
     * {@code GET  /progress-statuses/:id} : get the "id" progressStatus.
     *
     * @param id the id of the progressStatus to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the progressStatus, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProgressStatus> getProgressStatus(@PathVariable("id") Long id) {
        log.debug("REST request to get ProgressStatus : {}", id);
        Optional<ProgressStatus> progressStatus = progressStatusService.findOne(id);
        return ResponseUtil.wrapOrNotFound(progressStatus);
    }

    /**
     * {@code DELETE  /progress-statuses/:id} : delete the "id" progressStatus.
     *
     * @param id the id of the progressStatus to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgressStatus(@PathVariable("id") Long id) {
        log.debug("REST request to delete ProgressStatus : {}", id);
        progressStatusService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
