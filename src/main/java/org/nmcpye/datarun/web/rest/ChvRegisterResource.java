package org.nmcpye.datarun.web.rest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.nmcpye.datarun.domain.ChvRegister;
import org.nmcpye.datarun.repository.ChvRegisterRepository;
import org.nmcpye.datarun.service.ChvRegisterService;
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
 * REST controller for managing {@link org.nmcpye.datarun.domain.ChvRegister}.
 */
@RestController
@RequestMapping("/api/chv-registers")
public class ChvRegisterResource {

    private final Logger log = LoggerFactory.getLogger(ChvRegisterResource.class);

    private static final String ENTITY_NAME = "chvRegister";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ChvRegisterService chvRegisterService;

    private final ChvRegisterRepository chvRegisterRepository;

    public ChvRegisterResource(ChvRegisterService chvRegisterService, ChvRegisterRepository chvRegisterRepository) {
        this.chvRegisterService = chvRegisterService;
        this.chvRegisterRepository = chvRegisterRepository;
    }

    /**
     * {@code POST  /chv-registers} : Create a new chvRegister.
     *
     * @param chvRegister the chvRegister to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new chvRegister, or with status {@code 400 (Bad Request)} if the chvRegister has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ChvRegister> createChvRegister(@Valid @RequestBody ChvRegister chvRegister) throws URISyntaxException {
        log.debug("REST request to save ChvRegister : {}", chvRegister);
        if (chvRegister.getId() != null) {
            throw new BadRequestAlertException("A new chvRegister cannot already have an ID", ENTITY_NAME, "idexists");
        }
        chvRegister = chvRegisterService.save(chvRegister);
        return ResponseEntity.created(new URI("/api/chv-registers/" + chvRegister.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, chvRegister.getId().toString()))
            .body(chvRegister);
    }

    /**
     * {@code PUT  /chv-registers/:id} : Updates an existing chvRegister.
     *
     * @param id the id of the chvRegister to save.
     * @param chvRegister the chvRegister to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated chvRegister,
     * or with status {@code 400 (Bad Request)} if the chvRegister is not valid,
     * or with status {@code 500 (Internal Server Error)} if the chvRegister couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ChvRegister> updateChvRegister(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ChvRegister chvRegister
    ) throws URISyntaxException {
        log.debug("REST request to update ChvRegister : {}, {}", id, chvRegister);
        if (chvRegister.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, chvRegister.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!chvRegisterRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        chvRegister = chvRegisterService.update(chvRegister);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, chvRegister.getId().toString()))
            .body(chvRegister);
    }

    /**
     * {@code PATCH  /chv-registers/:id} : Partial updates given fields of an existing chvRegister, field will ignore if it is null
     *
     * @param id the id of the chvRegister to save.
     * @param chvRegister the chvRegister to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated chvRegister,
     * or with status {@code 400 (Bad Request)} if the chvRegister is not valid,
     * or with status {@code 404 (Not Found)} if the chvRegister is not found,
     * or with status {@code 500 (Internal Server Error)} if the chvRegister couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ChvRegister> partialUpdateChvRegister(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ChvRegister chvRegister
    ) throws URISyntaxException {
        log.debug("REST request to partial update ChvRegister partially : {}, {}", id, chvRegister);
        if (chvRegister.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, chvRegister.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!chvRegisterRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ChvRegister> result = chvRegisterService.partialUpdate(chvRegister);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, chvRegister.getId().toString())
        );
    }

    /**
     * {@code GET  /chv-registers} : get all the chvRegisters.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of chvRegisters in body.
     */
    @GetMapping("")
    public ResponseEntity<List<ChvRegister>> getAllChvRegisters(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get a page of ChvRegisters");
        Page<ChvRegister> page;
        if (eagerload) {
            page = chvRegisterService.findAllWithEagerRelationships(pageable);
        } else {
            page = chvRegisterService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /chv-registers/:id} : get the "id" chvRegister.
     *
     * @param id the id of the chvRegister to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the chvRegister, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ChvRegister> getChvRegister(@PathVariable("id") Long id) {
        log.debug("REST request to get ChvRegister : {}", id);
        Optional<ChvRegister> chvRegister = chvRegisterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(chvRegister);
    }

    /**
     * {@code DELETE  /chv-registers/:id} : delete the "id" chvRegister.
     *
     * @param id the id of the chvRegister to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChvRegister(@PathVariable("id") Long id) {
        log.debug("REST request to delete ChvRegister : {}", id);
        chvRegisterService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
