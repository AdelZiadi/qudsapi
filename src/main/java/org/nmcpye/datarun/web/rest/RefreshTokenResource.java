package org.nmcpye.datarun.web.rest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.nmcpye.datarun.domain.RefreshToken;
import org.nmcpye.datarun.repository.RefreshTokenRepository;
import org.nmcpye.datarun.service.RefreshTokenService;
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
 * REST controller for managing {@link org.nmcpye.datarun.domain.RefreshToken}.
 */
@RestController
@RequestMapping("/api/refresh-tokens")
public class RefreshTokenResource {

    private final Logger log = LoggerFactory.getLogger(RefreshTokenResource.class);

    private static final String ENTITY_NAME = "refreshToken";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RefreshTokenService refreshTokenService;

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenResource(RefreshTokenService refreshTokenService, RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenService = refreshTokenService;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    /**
     * {@code POST  /refresh-tokens} : Create a new refreshToken.
     *
     * @param refreshToken the refreshToken to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new refreshToken, or with status {@code 400 (Bad Request)} if the refreshToken has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<RefreshToken> createRefreshToken(@Valid @RequestBody RefreshToken refreshToken) throws URISyntaxException {
        log.debug("REST request to save RefreshToken : {}", refreshToken);
        if (refreshToken.getId() != null) {
            throw new BadRequestAlertException("A new refreshToken cannot already have an ID", ENTITY_NAME, "idexists");
        }
        refreshToken = refreshTokenService.save(refreshToken);
        return ResponseEntity.created(new URI("/api/refresh-tokens/" + refreshToken.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, refreshToken.getId().toString()))
            .body(refreshToken);
    }

    /**
     * {@code PUT  /refresh-tokens/:id} : Updates an existing refreshToken.
     *
     * @param id the id of the refreshToken to save.
     * @param refreshToken the refreshToken to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated refreshToken,
     * or with status {@code 400 (Bad Request)} if the refreshToken is not valid,
     * or with status {@code 500 (Internal Server Error)} if the refreshToken couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<RefreshToken> updateRefreshToken(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody RefreshToken refreshToken
    ) throws URISyntaxException {
        log.debug("REST request to update RefreshToken : {}, {}", id, refreshToken);
        if (refreshToken.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, refreshToken.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!refreshTokenRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        refreshToken = refreshTokenService.update(refreshToken);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, refreshToken.getId().toString()))
            .body(refreshToken);
    }

    /**
     * {@code PATCH  /refresh-tokens/:id} : Partial updates given fields of an existing refreshToken, field will ignore if it is null
     *
     * @param id the id of the refreshToken to save.
     * @param refreshToken the refreshToken to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated refreshToken,
     * or with status {@code 400 (Bad Request)} if the refreshToken is not valid,
     * or with status {@code 404 (Not Found)} if the refreshToken is not found,
     * or with status {@code 500 (Internal Server Error)} if the refreshToken couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RefreshToken> partialUpdateRefreshToken(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody RefreshToken refreshToken
    ) throws URISyntaxException {
        log.debug("REST request to partial update RefreshToken partially : {}, {}", id, refreshToken);
        if (refreshToken.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, refreshToken.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!refreshTokenRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RefreshToken> result = refreshTokenService.partialUpdate(refreshToken);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, refreshToken.getId().toString())
        );
    }

    /**
     * {@code GET  /refresh-tokens} : get all the refreshTokens.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of refreshTokens in body.
     */
    @GetMapping("")
    public ResponseEntity<List<RefreshToken>> getAllRefreshTokens(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get a page of RefreshTokens");
        Page<RefreshToken> page;
        if (eagerload) {
            page = refreshTokenService.findAllWithEagerRelationships(pageable);
        } else {
            page = refreshTokenService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /refresh-tokens/:id} : get the "id" refreshToken.
     *
     * @param id the id of the refreshToken to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the refreshToken, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<RefreshToken> getRefreshToken(@PathVariable("id") Long id) {
        log.debug("REST request to get RefreshToken : {}", id);
        Optional<RefreshToken> refreshToken = refreshTokenService.findOne(id);
        return ResponseUtil.wrapOrNotFound(refreshToken);
    }

    /**
     * {@code DELETE  /refresh-tokens/:id} : delete the "id" refreshToken.
     *
     * @param id the id of the refreshToken to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRefreshToken(@PathVariable("id") Long id) {
        log.debug("REST request to delete RefreshToken : {}", id);
        refreshTokenService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
