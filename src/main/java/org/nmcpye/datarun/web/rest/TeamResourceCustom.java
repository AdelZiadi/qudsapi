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

import java.util.List;


/**
 * REST controller for managing {@link Team}.
 */
@RestController
@RequestMapping("/api/teams/custom")
public class TeamResourceCustom extends TeamResource {
    private final Logger log = LoggerFactory.getLogger(TeamResourceCustom.class);

    private final TeamServiceCustom teamServiceCustom;

    private final TeamRepositoryCustom teamRepositoryCustom;

    public TeamResourceCustom(TeamServiceCustom teamService, TeamRepositoryCustom teamRepository) {
        super(teamService, teamRepository);
        this.teamRepositoryCustom = teamRepository;
        this.teamServiceCustom = teamService;
    }

    /**
     * {@code GET  /teams} : get all the teams.
     *
     * @param pageable  the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is for internal use only and should not be set by clients)
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of teams in body.
     */
    @GetMapping("user")
    public ResponseEntity<List<Team>> getCurrentUserTeams(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get a page of current user's Teams");
        Page<Team> page;
        page = teamServiceCustom.findByCurrentUser(pageable);

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
