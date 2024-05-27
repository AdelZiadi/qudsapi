package org.nmcpye.datarun.web.rest;

import org.nmcpye.datarun.domain.Assignment;
import org.nmcpye.datarun.repository.AssignmentRepositoryCustom;
import org.nmcpye.datarun.service.AssignmentServiceCustom;
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
 * REST Extended controller for managing {@link Assignment}.
 */
@RestController
@RequestMapping("/api/custom/assignments")
public class AssignmentResourceCustom extends AssignmentResource {

    private final Logger log = LoggerFactory.getLogger(AssignmentResourceCustom.class);

    private final AssignmentServiceCustom assignmentService;

    private final AssignmentRepositoryCustom assignmentRepository;

    public AssignmentResourceCustom(AssignmentServiceCustom assignmentService, AssignmentRepositoryCustom assignmentRepository) {
        super(assignmentService, assignmentRepository);
        this.assignmentRepository = assignmentRepository;
        this.assignmentService = assignmentService;
    }

    /**
     * {@code GET  /assignments} : get all the assignments.
     *
     * @param pageable  the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is for internal use only and should not be set by clients)
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of assignments in body.
     */
    @GetMapping("user")
    public ResponseEntity<List<Assignment>> getAllByUser(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get a page of Assignments");
        Page<Assignment> page;
        if (eagerload) {
            page = assignmentService.findAllWithEagerRelationships(pageable);
        } else {
            page = assignmentService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /assignments/:id} : get the "id" assignment.
     *
     * @param id the id of the assignment to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the assignment, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user/{id}")
    public ResponseEntity<Assignment> getOneByUser(@PathVariable("id") Long id) {
        log.debug("REST request to get Assignment : {}", id);
        Optional<Assignment> assignment = assignmentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(assignment);
    }

}
