package org.nmcpye.datarun.web.rest;

import org.nmcpye.datarun.domain.Team;
import org.nmcpye.datarun.repository.TeamRepositoryCustom;
import org.nmcpye.datarun.service.TeamServiceCustom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

import java.util.List;
import java.util.Optional;


/**
 * REST controller for managing {@link Team}.
 */
@RestController
@RequestMapping("/api/custom/teams")
public class TeamResourceCustom extends TeamResource {
    private final Logger log = LoggerFactory.getLogger(TeamResourceCustom.class);

    private final TeamServiceCustom teamService;

    private final TeamRepositoryCustom teamRepository;

    public TeamResourceCustom(TeamServiceCustom teamServiceCustom, TeamRepositoryCustom teamRepositoryCustom) {
        super(teamServiceCustom, teamRepositoryCustom);
        this.teamService = teamServiceCustom;
        this.teamRepository = teamRepositoryCustom;
    }

    /**
     * {@code GET  /teams} : get all the teams.
     *
     * @param pageable  the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of teams in body.
     */
    @GetMapping("user")
    public ResponseEntity<List<Team>> getAllByUser(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get a page of current user's Teams");
        Page<Team> page;

        if (eagerload) {
            page = teamService.findAllWithEagerRelationships(pageable);
        } else {
            page = teamService.findAll(pageable);
        }

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /teams/:id} : get the "id" team.
     *
     * @param id the id of the team to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the team, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user/{id}")
    public ResponseEntity<Team> getOneByUser(@PathVariable("id") Long id) {
        log.debug("REST request to get Team : {}", id);
        Optional<Team> team = teamService.findOne(id);
        return ResponseUtil.wrapOrNotFound(team);
    }
}
