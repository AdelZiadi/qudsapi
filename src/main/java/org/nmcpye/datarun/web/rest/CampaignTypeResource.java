package org.nmcpye.datarun.web.rest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.nmcpye.datarun.domain.CampaignType;
import org.nmcpye.datarun.repository.CampaignTypeRepository;
import org.nmcpye.datarun.service.CampaignTypeService;
import org.nmcpye.datarun.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.nmcpye.datarun.domain.CampaignType}.
 */
@RestController
@RequestMapping("/api/campaign-types")
public class CampaignTypeResource {

    private final Logger log = LoggerFactory.getLogger(CampaignTypeResource.class);

    private static final String ENTITY_NAME = "campaignType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CampaignTypeService campaignTypeService;

    private final CampaignTypeRepository campaignTypeRepository;

    public CampaignTypeResource(CampaignTypeService campaignTypeService, CampaignTypeRepository campaignTypeRepository) {
        this.campaignTypeService = campaignTypeService;
        this.campaignTypeRepository = campaignTypeRepository;
    }

    /**
     * {@code POST  /campaign-types} : Create a new campaignType.
     *
     * @param campaignType the campaignType to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new campaignType, or with status {@code 400 (Bad Request)} if the campaignType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<CampaignType> createCampaignType(@Valid @RequestBody CampaignType campaignType) throws URISyntaxException {
        log.debug("REST request to save CampaignType : {}", campaignType);
        if (campaignType.getId() != null) {
            throw new BadRequestAlertException("A new campaignType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        campaignType = campaignTypeService.save(campaignType);
        return ResponseEntity.created(new URI("/api/campaign-types/" + campaignType.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, campaignType.getId().toString()))
            .body(campaignType);
    }

    /**
     * {@code PUT  /campaign-types/:id} : Updates an existing campaignType.
     *
     * @param id the id of the campaignType to save.
     * @param campaignType the campaignType to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated campaignType,
     * or with status {@code 400 (Bad Request)} if the campaignType is not valid,
     * or with status {@code 500 (Internal Server Error)} if the campaignType couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CampaignType> updateCampaignType(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody CampaignType campaignType
    ) throws URISyntaxException {
        log.debug("REST request to update CampaignType : {}, {}", id, campaignType);
        if (campaignType.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, campaignType.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!campaignTypeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        campaignType = campaignTypeService.update(campaignType);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, campaignType.getId().toString()))
            .body(campaignType);
    }

    /**
     * {@code PATCH  /campaign-types/:id} : Partial updates given fields of an existing campaignType, field will ignore if it is null
     *
     * @param id the id of the campaignType to save.
     * @param campaignType the campaignType to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated campaignType,
     * or with status {@code 400 (Bad Request)} if the campaignType is not valid,
     * or with status {@code 404 (Not Found)} if the campaignType is not found,
     * or with status {@code 500 (Internal Server Error)} if the campaignType couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CampaignType> partialUpdateCampaignType(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody CampaignType campaignType
    ) throws URISyntaxException {
        log.debug("REST request to partial update CampaignType partially : {}, {}", id, campaignType);
        if (campaignType.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, campaignType.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!campaignTypeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CampaignType> result = campaignTypeService.partialUpdate(campaignType);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, campaignType.getId().toString())
        );
    }

    /**
     * {@code GET  /campaign-types} : get all the campaignTypes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of campaignTypes in body.
     */
    @GetMapping("")
    public List<CampaignType> getAllCampaignTypes() {
        log.debug("REST request to get all CampaignTypes");
        return campaignTypeService.findAll();
    }

    /**
     * {@code GET  /campaign-types/:id} : get the "id" campaignType.
     *
     * @param id the id of the campaignType to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the campaignType, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CampaignType> getCampaignType(@PathVariable("id") Long id) {
        log.debug("REST request to get CampaignType : {}", id);
        Optional<CampaignType> campaignType = campaignTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(campaignType);
    }

    /**
     * {@code DELETE  /campaign-types/:id} : delete the "id" campaignType.
     *
     * @param id the id of the campaignType to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCampaignType(@PathVariable("id") Long id) {
        log.debug("REST request to delete CampaignType : {}", id);
        campaignTypeService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
