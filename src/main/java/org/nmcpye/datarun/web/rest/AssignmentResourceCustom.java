package org.nmcpye.datarun.web.rest;

import org.nmcpye.datarun.domain.Assignment;
import org.nmcpye.datarun.repository.AssignmentRepositoryExt;
import org.nmcpye.datarun.service.AssignmentServiceExt;
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
 * REST Extended controller for managing {@link Assignment}.
 */
@RestController
@RequestMapping("/api/assignments/custom")
public class AssignmentResourceExt extends AssignmentResource {

    private final Logger log = LoggerFactory.getLogger(AssignmentResourceExt.class);

    private final AssignmentServiceExt assignmentServiceExt;

    private final AssignmentRepositoryExt assignmentRepositoryExt;

    public AssignmentResourceExt(AssignmentServiceExt assignmentService, AssignmentRepositoryExt assignmentRepository) {
        super(assignmentService, assignmentRepository);
        this.assignmentRepositoryExt = assignmentRepository;
        this.assignmentServiceExt = assignmentService;
    }

    /**
     * {@code GET  /assignments} : get all the assignments.
     *
     * @param pageable  the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is for internal use only and should not be set by clients)
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of assignments in body.
     */
    @GetMapping("user")
    public ResponseEntity<List<Assignment>> getCurrentUserAssignments(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get a page of Assignments");
        Page<Assignment> page;
        page = assignmentServiceExt.findByCurrentUser(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
