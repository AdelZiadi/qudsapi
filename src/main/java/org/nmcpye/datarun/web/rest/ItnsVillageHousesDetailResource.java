package org.nmcpye.datarun.web.rest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.nmcpye.datarun.domain.ItnsVillageHousesDetail;
import org.nmcpye.datarun.repository.ItnsVillageHousesDetailRepository;
import org.nmcpye.datarun.service.ItnsVillageHousesDetailService;
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
 * REST controller for managing {@link org.nmcpye.datarun.domain.ItnsVillageHousesDetail}.
 */
@RestController
@RequestMapping("/api/itns-village-houses-details")
public class ItnsVillageHousesDetailResource {

    private final Logger log = LoggerFactory.getLogger(ItnsVillageHousesDetailResource.class);

    private static final String ENTITY_NAME = "itnsVillageHousesDetail";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ItnsVillageHousesDetailService itnsVillageHousesDetailService;

    private final ItnsVillageHousesDetailRepository itnsVillageHousesDetailRepository;

    public ItnsVillageHousesDetailResource(
        ItnsVillageHousesDetailService itnsVillageHousesDetailService,
        ItnsVillageHousesDetailRepository itnsVillageHousesDetailRepository
    ) {
        this.itnsVillageHousesDetailService = itnsVillageHousesDetailService;
        this.itnsVillageHousesDetailRepository = itnsVillageHousesDetailRepository;
    }

    /**
     * {@code POST  /itns-village-houses-details} : Create a new itnsVillageHousesDetail.
     *
     * @param itnsVillageHousesDetail the itnsVillageHousesDetail to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new itnsVillageHousesDetail, or with status {@code 400 (Bad Request)} if the itnsVillageHousesDetail has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ItnsVillageHousesDetail> createItnsVillageHousesDetail(
        @Valid @RequestBody ItnsVillageHousesDetail itnsVillageHousesDetail
    ) throws URISyntaxException {
        log.debug("REST request to save ItnsVillageHousesDetail : {}", itnsVillageHousesDetail);
        if (itnsVillageHousesDetail.getId() != null) {
            throw new BadRequestAlertException("A new itnsVillageHousesDetail cannot already have an ID", ENTITY_NAME, "idexists");
        }
        itnsVillageHousesDetail = itnsVillageHousesDetailService.save(itnsVillageHousesDetail);
        return ResponseEntity.created(new URI("/api/itns-village-houses-details/" + itnsVillageHousesDetail.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, itnsVillageHousesDetail.getId().toString()))
            .body(itnsVillageHousesDetail);
    }

    /**
     * {@code PUT  /itns-village-houses-details/:id} : Updates an existing itnsVillageHousesDetail.
     *
     * @param id the id of the itnsVillageHousesDetail to save.
     * @param itnsVillageHousesDetail the itnsVillageHousesDetail to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated itnsVillageHousesDetail,
     * or with status {@code 400 (Bad Request)} if the itnsVillageHousesDetail is not valid,
     * or with status {@code 500 (Internal Server Error)} if the itnsVillageHousesDetail couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ItnsVillageHousesDetail> updateItnsVillageHousesDetail(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ItnsVillageHousesDetail itnsVillageHousesDetail
    ) throws URISyntaxException {
        log.debug("REST request to update ItnsVillageHousesDetail : {}, {}", id, itnsVillageHousesDetail);
        if (itnsVillageHousesDetail.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, itnsVillageHousesDetail.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!itnsVillageHousesDetailRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        itnsVillageHousesDetail = itnsVillageHousesDetailService.update(itnsVillageHousesDetail);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, itnsVillageHousesDetail.getId().toString()))
            .body(itnsVillageHousesDetail);
    }

    /**
     * {@code PATCH  /itns-village-houses-details/:id} : Partial updates given fields of an existing itnsVillageHousesDetail, field will ignore if it is null
     *
     * @param id the id of the itnsVillageHousesDetail to save.
     * @param itnsVillageHousesDetail the itnsVillageHousesDetail to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated itnsVillageHousesDetail,
     * or with status {@code 400 (Bad Request)} if the itnsVillageHousesDetail is not valid,
     * or with status {@code 404 (Not Found)} if the itnsVillageHousesDetail is not found,
     * or with status {@code 500 (Internal Server Error)} if the itnsVillageHousesDetail couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ItnsVillageHousesDetail> partialUpdateItnsVillageHousesDetail(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ItnsVillageHousesDetail itnsVillageHousesDetail
    ) throws URISyntaxException {
        log.debug("REST request to partial update ItnsVillageHousesDetail partially : {}, {}", id, itnsVillageHousesDetail);
        if (itnsVillageHousesDetail.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, itnsVillageHousesDetail.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!itnsVillageHousesDetailRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ItnsVillageHousesDetail> result = itnsVillageHousesDetailService.partialUpdate(itnsVillageHousesDetail);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, itnsVillageHousesDetail.getId().toString())
        );
    }

    /**
     * {@code GET  /itns-village-houses-details} : get all the itnsVillageHousesDetails.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of itnsVillageHousesDetails in body.
     */
    @GetMapping("")
    public ResponseEntity<List<ItnsVillageHousesDetail>> getAllItnsVillageHousesDetails(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of ItnsVillageHousesDetails");
        Page<ItnsVillageHousesDetail> page = itnsVillageHousesDetailService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /itns-village-houses-details/:id} : get the "id" itnsVillageHousesDetail.
     *
     * @param id the id of the itnsVillageHousesDetail to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the itnsVillageHousesDetail, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ItnsVillageHousesDetail> getItnsVillageHousesDetail(@PathVariable("id") Long id) {
        log.debug("REST request to get ItnsVillageHousesDetail : {}", id);
        Optional<ItnsVillageHousesDetail> itnsVillageHousesDetail = itnsVillageHousesDetailService.findOne(id);
        return ResponseUtil.wrapOrNotFound(itnsVillageHousesDetail);
    }

    /**
     * {@code DELETE  /itns-village-houses-details/:id} : delete the "id" itnsVillageHousesDetail.
     *
     * @param id the id of the itnsVillageHousesDetail to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItnsVillageHousesDetail(@PathVariable("id") Long id) {
        log.debug("REST request to delete ItnsVillageHousesDetail : {}", id);
        itnsVillageHousesDetailService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
