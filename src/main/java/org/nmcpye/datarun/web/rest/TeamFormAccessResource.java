package org.nmcpye.datarun.web.rest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.nmcpye.datarun.domain.TeamFormAccess;
import org.nmcpye.datarun.repository.TeamFormAccessRepository;
import org.nmcpye.datarun.service.TeamFormAccessService;
import org.nmcpye.datarun.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.nmcpye.datarun.domain.TeamFormAccess}.
 */
@RestController
@RequestMapping("/api/team-form-accesses")
public class TeamFormAccessResource {

    private final Logger log = LoggerFactory.getLogger(TeamFormAccessResource.class);

    private static final String ENTITY_NAME = "teamFormAccess";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TeamFormAccessService teamFormAccessService;

    private final TeamFormAccessRepository teamFormAccessRepository;

    public TeamFormAccessResource(TeamFormAccessService teamFormAccessService, TeamFormAccessRepository teamFormAccessRepository) {
        this.teamFormAccessService = teamFormAccessService;
        this.teamFormAccessRepository = teamFormAccessRepository;
    }

    /**
     * {@code POST  /team-form-accesses} : Create a new teamFormAccess.
     *
     * @param teamFormAccess the teamFormAccess to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new teamFormAccess, or with status {@code 400 (Bad Request)} if the teamFormAccess has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<TeamFormAccess> createTeamFormAccess(@Valid @RequestBody TeamFormAccess teamFormAccess)
        throws URISyntaxException {
        log.debug("REST request to save TeamFormAccess : {}", teamFormAccess);
        if (teamFormAccess.getId() != null) {
            throw new BadRequestAlertException("A new teamFormAccess cannot already have an ID", ENTITY_NAME, "idexists");
        }
        teamFormAccess = teamFormAccessService.save(teamFormAccess);
        return ResponseEntity.created(new URI("/api/team-form-accesses/" + teamFormAccess.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, teamFormAccess.getId().toString()))
            .body(teamFormAccess);
    }

    /**
     * {@code PUT  /team-form-accesses/:id} : Updates an existing teamFormAccess.
     *
     * @param id the id of the teamFormAccess to save.
     * @param teamFormAccess the teamFormAccess to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated teamFormAccess,
     * or with status {@code 400 (Bad Request)} if the teamFormAccess is not valid,
     * or with status {@code 500 (Internal Server Error)} if the teamFormAccess couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<TeamFormAccess> updateTeamFormAccess(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody TeamFormAccess teamFormAccess
    ) throws URISyntaxException {
        log.debug("REST request to update TeamFormAccess : {}, {}", id, teamFormAccess);
        if (teamFormAccess.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, teamFormAccess.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!teamFormAccessRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        teamFormAccess = teamFormAccessService.update(teamFormAccess);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, teamFormAccess.getId().toString()))
            .body(teamFormAccess);
    }

    /**
     * {@code PATCH  /team-form-accesses/:id} : Partial updates given fields of an existing teamFormAccess, field will ignore if it is null
     *
     * @param id the id of the teamFormAccess to save.
     * @param teamFormAccess the teamFormAccess to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated teamFormAccess,
     * or with status {@code 400 (Bad Request)} if the teamFormAccess is not valid,
     * or with status {@code 404 (Not Found)} if the teamFormAccess is not found,
     * or with status {@code 500 (Internal Server Error)} if the teamFormAccess couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TeamFormAccess> partialUpdateTeamFormAccess(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody TeamFormAccess teamFormAccess
    ) throws URISyntaxException {
        log.debug("REST request to partial update TeamFormAccess partially : {}, {}", id, teamFormAccess);
        if (teamFormAccess.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, teamFormAccess.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!teamFormAccessRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TeamFormAccess> result = teamFormAccessService.partialUpdate(teamFormAccess);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, teamFormAccess.getId().toString())
        );
    }

    /**
     * {@code GET  /team-form-accesses} : get all the teamFormAccesses.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of teamFormAccesses in body.
     */
    @GetMapping("")
    public List<TeamFormAccess> getAllTeamFormAccesses() {
        log.debug("REST request to get all TeamFormAccesses");
        return teamFormAccessService.findAll();
    }

    /**
     * {@code GET  /team-form-accesses/:id} : get the "id" teamFormAccess.
     *
     * @param id the id of the teamFormAccess to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the teamFormAccess, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<TeamFormAccess> getTeamFormAccess(@PathVariable("id") Long id) {
        log.debug("REST request to get TeamFormAccess : {}", id);
        Optional<TeamFormAccess> teamFormAccess = teamFormAccessService.findOne(id);
        return ResponseUtil.wrapOrNotFound(teamFormAccess);
    }

    /**
     * {@code DELETE  /team-form-accesses/:id} : delete the "id" teamFormAccess.
     *
     * @param id the id of the teamFormAccess to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeamFormAccess(@PathVariable("id") Long id) {
        log.debug("REST request to delete TeamFormAccess : {}", id);
        teamFormAccessService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
