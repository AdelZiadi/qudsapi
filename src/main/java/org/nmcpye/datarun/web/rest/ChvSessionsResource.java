package org.nmcpye.datarun.web.rest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.nmcpye.datarun.domain.ChvSessions;
import org.nmcpye.datarun.repository.ChvSessionsRepository;
import org.nmcpye.datarun.service.ChvSessionsService;
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
 * REST controller for managing {@link org.nmcpye.datarun.domain.ChvSessions}.
 */
@RestController
@RequestMapping("/api/chv-sessions")
public class ChvSessionsResource {

    private final Logger log = LoggerFactory.getLogger(ChvSessionsResource.class);

    private static final String ENTITY_NAME = "chvSessions";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ChvSessionsService chvSessionsService;

    private final ChvSessionsRepository chvSessionsRepository;

    public ChvSessionsResource(ChvSessionsService chvSessionsService, ChvSessionsRepository chvSessionsRepository) {
        this.chvSessionsService = chvSessionsService;
        this.chvSessionsRepository = chvSessionsRepository;
    }

    /**
     * {@code POST  /chv-sessions} : Create a new chvSessions.
     *
     * @param chvSessions the chvSessions to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new chvSessions, or with status {@code 400 (Bad Request)} if the chvSessions has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ChvSessions> createChvSessions(@Valid @RequestBody ChvSessions chvSessions) throws URISyntaxException {
        log.debug("REST request to save ChvSessions : {}", chvSessions);
        if (chvSessions.getId() != null) {
            throw new BadRequestAlertException("A new chvSessions cannot already have an ID", ENTITY_NAME, "idexists");
        }
        chvSessions = chvSessionsService.save(chvSessions);
        return ResponseEntity.created(new URI("/api/chv-sessions/" + chvSessions.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, chvSessions.getId().toString()))
            .body(chvSessions);
    }

    /**
     * {@code PUT  /chv-sessions/:id} : Updates an existing chvSessions.
     *
     * @param id the id of the chvSessions to save.
     * @param chvSessions the chvSessions to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated chvSessions,
     * or with status {@code 400 (Bad Request)} if the chvSessions is not valid,
     * or with status {@code 500 (Internal Server Error)} if the chvSessions couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ChvSessions> updateChvSessions(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ChvSessions chvSessions
    ) throws URISyntaxException {
        log.debug("REST request to update ChvSessions : {}, {}", id, chvSessions);
        if (chvSessions.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, chvSessions.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!chvSessionsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        chvSessions = chvSessionsService.update(chvSessions);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, chvSessions.getId().toString()))
            .body(chvSessions);
    }

    /**
     * {@code PATCH  /chv-sessions/:id} : Partial updates given fields of an existing chvSessions, field will ignore if it is null
     *
     * @param id the id of the chvSessions to save.
     * @param chvSessions the chvSessions to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated chvSessions,
     * or with status {@code 400 (Bad Request)} if the chvSessions is not valid,
     * or with status {@code 404 (Not Found)} if the chvSessions is not found,
     * or with status {@code 500 (Internal Server Error)} if the chvSessions couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ChvSessions> partialUpdateChvSessions(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ChvSessions chvSessions
    ) throws URISyntaxException {
        log.debug("REST request to partial update ChvSessions partially : {}, {}", id, chvSessions);
        if (chvSessions.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, chvSessions.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!chvSessionsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ChvSessions> result = chvSessionsService.partialUpdate(chvSessions);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, chvSessions.getId().toString())
        );
    }

    /**
     * {@code GET  /chv-sessions} : get all the chvSessions.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of chvSessions in body.
     */
    @GetMapping("")
    public ResponseEntity<List<ChvSessions>> getAllChvSessions(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get a page of ChvSessions");
        Page<ChvSessions> page;
        if (eagerload) {
            page = chvSessionsService.findAllWithEagerRelationships(pageable);
        } else {
            page = chvSessionsService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /chv-sessions/:id} : get the "id" chvSessions.
     *
     * @param id the id of the chvSessions to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the chvSessions, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ChvSessions> getChvSessions(@PathVariable("id") Long id) {
        log.debug("REST request to get ChvSessions : {}", id);
        Optional<ChvSessions> chvSessions = chvSessionsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(chvSessions);
    }

    /**
     * {@code DELETE  /chv-sessions/:id} : delete the "id" chvSessions.
     *
     * @param id the id of the chvSessions to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChvSessions(@PathVariable("id") Long id) {
        log.debug("REST request to delete ChvSessions : {}", id);
        chvSessionsService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
