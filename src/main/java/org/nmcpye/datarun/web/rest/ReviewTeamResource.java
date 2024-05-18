package org.nmcpye.datarun.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.nmcpye.datarun.domain.ReviewTeam;
import org.nmcpye.datarun.repository.ReviewTeamRepository;
import org.nmcpye.datarun.service.ReviewTeamService;
import org.nmcpye.datarun.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.nmcpye.datarun.domain.ReviewTeam}.
 */
@RestController
@RequestMapping("/api/review-teams")
public class ReviewTeamResource {

    private final Logger log = LoggerFactory.getLogger(ReviewTeamResource.class);

    private static final String ENTITY_NAME = "reviewTeam";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ReviewTeamService reviewTeamService;

    private final ReviewTeamRepository reviewTeamRepository;

    public ReviewTeamResource(ReviewTeamService reviewTeamService, ReviewTeamRepository reviewTeamRepository) {
        this.reviewTeamService = reviewTeamService;
        this.reviewTeamRepository = reviewTeamRepository;
    }

    /**
     * {@code POST  /review-teams} : Create a new reviewTeam.
     *
     * @param reviewTeam the reviewTeam to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new reviewTeam, or with status {@code 400 (Bad Request)} if the reviewTeam has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ReviewTeam> createReviewTeam(@RequestBody ReviewTeam reviewTeam) throws URISyntaxException {
        log.debug("REST request to save ReviewTeam : {}", reviewTeam);
        if (reviewTeam.getId() != null) {
            throw new BadRequestAlertException("A new reviewTeam cannot already have an ID", ENTITY_NAME, "idexists");
        }
        reviewTeam = reviewTeamService.save(reviewTeam);
        return ResponseEntity.created(new URI("/api/review-teams/" + reviewTeam.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, reviewTeam.getId().toString()))
            .body(reviewTeam);
    }

    /**
     * {@code PUT  /review-teams/:id} : Updates an existing reviewTeam.
     *
     * @param id the id of the reviewTeam to save.
     * @param reviewTeam the reviewTeam to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated reviewTeam,
     * or with status {@code 400 (Bad Request)} if the reviewTeam is not valid,
     * or with status {@code 500 (Internal Server Error)} if the reviewTeam couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ReviewTeam> updateReviewTeam(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ReviewTeam reviewTeam
    ) throws URISyntaxException {
        log.debug("REST request to update ReviewTeam : {}, {}", id, reviewTeam);
        if (reviewTeam.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, reviewTeam.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!reviewTeamRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        reviewTeam = reviewTeamService.update(reviewTeam);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, reviewTeam.getId().toString()))
            .body(reviewTeam);
    }

    /**
     * {@code PATCH  /review-teams/:id} : Partial updates given fields of an existing reviewTeam, field will ignore if it is null
     *
     * @param id the id of the reviewTeam to save.
     * @param reviewTeam the reviewTeam to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated reviewTeam,
     * or with status {@code 400 (Bad Request)} if the reviewTeam is not valid,
     * or with status {@code 404 (Not Found)} if the reviewTeam is not found,
     * or with status {@code 500 (Internal Server Error)} if the reviewTeam couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ReviewTeam> partialUpdateReviewTeam(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ReviewTeam reviewTeam
    ) throws URISyntaxException {
        log.debug("REST request to partial update ReviewTeam partially : {}, {}", id, reviewTeam);
        if (reviewTeam.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, reviewTeam.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!reviewTeamRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ReviewTeam> result = reviewTeamService.partialUpdate(reviewTeam);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, reviewTeam.getId().toString())
        );
    }

    /**
     * {@code GET  /review-teams} : get all the reviewTeams.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of reviewTeams in body.
     */
    @GetMapping("")
    public List<ReviewTeam> getAllReviewTeams() {
        log.debug("REST request to get all ReviewTeams");
        return reviewTeamService.findAll();
    }

    /**
     * {@code GET  /review-teams/:id} : get the "id" reviewTeam.
     *
     * @param id the id of the reviewTeam to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the reviewTeam, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ReviewTeam> getReviewTeam(@PathVariable("id") Long id) {
        log.debug("REST request to get ReviewTeam : {}", id);
        Optional<ReviewTeam> reviewTeam = reviewTeamService.findOne(id);
        return ResponseUtil.wrapOrNotFound(reviewTeam);
    }

    /**
     * {@code DELETE  /review-teams/:id} : delete the "id" reviewTeam.
     *
     * @param id the id of the reviewTeam to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReviewTeam(@PathVariable("id") Long id) {
        log.debug("REST request to delete ReviewTeam : {}", id);
        reviewTeamService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
