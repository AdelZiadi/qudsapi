package org.nmcpye.datarun.web.rest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.nmcpye.datarun.domain.ChvSession;
import org.nmcpye.datarun.repository.ChvSessionRepository;
import org.nmcpye.datarun.service.ChvSessionService;
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
 * REST controller for managing {@link org.nmcpye.datarun.domain.ChvSession}.
 */
@RestController
@RequestMapping("/api/chv-sessions")
public class ChvSessionResource {

    private final Logger log = LoggerFactory.getLogger(ChvSessionResource.class);

    private static final String ENTITY_NAME = "chvSession";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ChvSessionService chvSessionService;

    private final ChvSessionRepository chvSessionRepository;

    public ChvSessionResource(ChvSessionService chvSessionService, ChvSessionRepository chvSessionRepository) {
        this.chvSessionService = chvSessionService;
        this.chvSessionRepository = chvSessionRepository;
    }

    /**
     * {@code POST  /chv-sessions} : Create a new chvSession.
     *
     * @param chvSession the chvSession to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new chvSession, or with status {@code 400 (Bad Request)} if the chvSession has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ChvSession> createChvSession(@Valid @RequestBody ChvSession chvSession) throws URISyntaxException {
        log.debug("REST request to save ChvSession : {}", chvSession);
        if (chvSession.getId() != null) {
            throw new BadRequestAlertException("A new chvSession cannot already have an ID", ENTITY_NAME, "idexists");
        }
        chvSession = chvSessionService.save(chvSession);
        return ResponseEntity.created(new URI("/api/chv-sessions/" + chvSession.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, chvSession.getId().toString()))
            .body(chvSession);
    }

    /**
     * {@code PUT  /chv-sessions/:id} : Updates an existing chvSession.
     *
     * @param id the id of the chvSession to save.
     * @param chvSession the chvSession to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated chvSession,
     * or with status {@code 400 (Bad Request)} if the chvSession is not valid,
     * or with status {@code 500 (Internal Server Error)} if the chvSession couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ChvSession> updateChvSession(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ChvSession chvSession
    ) throws URISyntaxException {
        log.debug("REST request to update ChvSession : {}, {}", id, chvSession);
        if (chvSession.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, chvSession.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!chvSessionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        chvSession = chvSessionService.update(chvSession);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, chvSession.getId().toString()))
            .body(chvSession);
    }

    /**
     * {@code PATCH  /chv-sessions/:id} : Partial updates given fields of an existing chvSession, field will ignore if it is null
     *
     * @param id the id of the chvSession to save.
     * @param chvSession the chvSession to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated chvSession,
     * or with status {@code 400 (Bad Request)} if the chvSession is not valid,
     * or with status {@code 404 (Not Found)} if the chvSession is not found,
     * or with status {@code 500 (Internal Server Error)} if the chvSession couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ChvSession> partialUpdateChvSession(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ChvSession chvSession
    ) throws URISyntaxException {
        log.debug("REST request to partial update ChvSession partially : {}, {}", id, chvSession);
        if (chvSession.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, chvSession.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!chvSessionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ChvSession> result = chvSessionService.partialUpdate(chvSession);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, chvSession.getId().toString())
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
    public ResponseEntity<List<ChvSession>> getAllChvSessions(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get a page of ChvSessions");
        Page<ChvSession> page;
        if (eagerload) {
            page = chvSessionService.findAllWithEagerRelationships(pageable);
        } else {
            page = chvSessionService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /chv-sessions/:id} : get the "id" chvSession.
     *
     * @param id the id of the chvSession to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the chvSession, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ChvSession> getChvSession(@PathVariable("id") Long id) {
        log.debug("REST request to get ChvSession : {}", id);
        Optional<ChvSession> chvSession = chvSessionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(chvSession);
    }

    /**
     * {@code DELETE  /chv-sessions/:id} : delete the "id" chvSession.
     *
     * @param id the id of the chvSession to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChvSession(@PathVariable("id") Long id) {
        log.debug("REST request to delete ChvSession : {}", id);
        chvSessionService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
