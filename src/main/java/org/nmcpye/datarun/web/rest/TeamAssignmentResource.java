package org.nmcpye.datarun.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.nmcpye.datarun.domain.TeamAssignment;
import org.nmcpye.datarun.repository.TeamAssignmentRepository;
import org.nmcpye.datarun.service.TeamAssignmentService;
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
 * REST controller for managing {@link org.nmcpye.datarun.domain.TeamAssignment}.
 */
@RestController
@RequestMapping("/api/team-assignments")
public class TeamAssignmentResource {

    private final Logger log = LoggerFactory.getLogger(TeamAssignmentResource.class);

    private static final String ENTITY_NAME = "teamAssignment";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TeamAssignmentService teamAssignmentService;

    private final TeamAssignmentRepository teamAssignmentRepository;

    public TeamAssignmentResource(TeamAssignmentService teamAssignmentService, TeamAssignmentRepository teamAssignmentRepository) {
        this.teamAssignmentService = teamAssignmentService;
        this.teamAssignmentRepository = teamAssignmentRepository;
    }

    /**
     * {@code POST  /team-assignments} : Create a new teamAssignment.
     *
     * @param teamAssignment the teamAssignment to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new teamAssignment, or with status {@code 400 (Bad Request)} if the teamAssignment has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<TeamAssignment> createTeamAssignment(@RequestBody TeamAssignment teamAssignment) throws URISyntaxException {
        log.debug("REST request to save TeamAssignment : {}", teamAssignment);
        if (teamAssignment.getId() != null) {
            throw new BadRequestAlertException("A new teamAssignment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        teamAssignment = teamAssignmentService.save(teamAssignment);
        return ResponseEntity.created(new URI("/api/team-assignments/" + teamAssignment.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, teamAssignment.getId().toString()))
            .body(teamAssignment);
    }

    /**
     * {@code PUT  /team-assignments/:id} : Updates an existing teamAssignment.
     *
     * @param id the id of the teamAssignment to save.
     * @param teamAssignment the teamAssignment to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated teamAssignment,
     * or with status {@code 400 (Bad Request)} if the teamAssignment is not valid,
     * or with status {@code 500 (Internal Server Error)} if the teamAssignment couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<TeamAssignment> updateTeamAssignment(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TeamAssignment teamAssignment
    ) throws URISyntaxException {
        log.debug("REST request to update TeamAssignment : {}, {}", id, teamAssignment);
        if (teamAssignment.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, teamAssignment.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!teamAssignmentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        teamAssignment = teamAssignmentService.update(teamAssignment);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, teamAssignment.getId().toString()))
            .body(teamAssignment);
    }

    /**
     * {@code PATCH  /team-assignments/:id} : Partial updates given fields of an existing teamAssignment, field will ignore if it is null
     *
     * @param id the id of the teamAssignment to save.
     * @param teamAssignment the teamAssignment to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated teamAssignment,
     * or with status {@code 400 (Bad Request)} if the teamAssignment is not valid,
     * or with status {@code 404 (Not Found)} if the teamAssignment is not found,
     * or with status {@code 500 (Internal Server Error)} if the teamAssignment couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TeamAssignment> partialUpdateTeamAssignment(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TeamAssignment teamAssignment
    ) throws URISyntaxException {
        log.debug("REST request to partial update TeamAssignment partially : {}, {}", id, teamAssignment);
        if (teamAssignment.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, teamAssignment.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!teamAssignmentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TeamAssignment> result = teamAssignmentService.partialUpdate(teamAssignment);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, teamAssignment.getId().toString())
        );
    }

    /**
     * {@code GET  /team-assignments} : get all the teamAssignments.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of teamAssignments in body.
     */
    @GetMapping("")
    public ResponseEntity<List<TeamAssignment>> getAllTeamAssignments(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get a page of TeamAssignments");
        Page<TeamAssignment> page;
        if (eagerload) {
            page = teamAssignmentService.findAllWithEagerRelationships(pageable);
        } else {
            page = teamAssignmentService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /team-assignments/:id} : get the "id" teamAssignment.
     *
     * @param id the id of the teamAssignment to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the teamAssignment, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<TeamAssignment> getTeamAssignment(@PathVariable("id") Long id) {
        log.debug("REST request to get TeamAssignment : {}", id);
        Optional<TeamAssignment> teamAssignment = teamAssignmentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(teamAssignment);
    }

    /**
     * {@code DELETE  /team-assignments/:id} : delete the "id" teamAssignment.
     *
     * @param id the id of the teamAssignment to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeamAssignment(@PathVariable("id") Long id) {
        log.debug("REST request to delete TeamAssignment : {}", id);
        teamAssignmentService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
