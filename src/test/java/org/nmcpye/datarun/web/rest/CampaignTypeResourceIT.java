package org.nmcpye.datarun.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.nmcpye.datarun.domain.CampaignTypeAsserts.*;
import static org.nmcpye.datarun.web.rest.TestUtil.createUpdateProxyForBean;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nmcpye.datarun.IntegrationTest;
import org.nmcpye.datarun.domain.CampaignType;
import org.nmcpye.datarun.repository.CampaignTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link CampaignTypeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CampaignTypeResourceIT {

    private static final String DEFAULT_CAMPAIGN_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_CAMPAIGN_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/campaign-types";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private CampaignTypeRepository campaignTypeRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCampaignTypeMockMvc;

    private CampaignType campaignType;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CampaignType createEntity(EntityManager em) {
        CampaignType campaignType = new CampaignType().campaignType(DEFAULT_CAMPAIGN_TYPE).description(DEFAULT_DESCRIPTION);
        return campaignType;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CampaignType createUpdatedEntity(EntityManager em) {
        CampaignType campaignType = new CampaignType().campaignType(UPDATED_CAMPAIGN_TYPE).description(UPDATED_DESCRIPTION);
        return campaignType;
    }

    @BeforeEach
    public void initTest() {
        campaignType = createEntity(em);
    }

    @Test
    @Transactional
    void createCampaignType() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the CampaignType
        var returnedCampaignType = om.readValue(
            restCampaignTypeMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(campaignType)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            CampaignType.class
        );

        // Validate the CampaignType in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertCampaignTypeUpdatableFieldsEquals(returnedCampaignType, getPersistedCampaignType(returnedCampaignType));
    }

    @Test
    @Transactional
    void createCampaignTypeWithExistingId() throws Exception {
        // Create the CampaignType with an existing ID
        campaignType.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCampaignTypeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(campaignType)))
            .andExpect(status().isBadRequest());

        // Validate the CampaignType in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkCampaignTypeIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        campaignType.setCampaignType(null);

        // Create the CampaignType, which fails.

        restCampaignTypeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(campaignType)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllCampaignTypes() throws Exception {
        // Initialize the database
        campaignTypeRepository.saveAndFlush(campaignType);

        // Get all the campaignTypeList
        restCampaignTypeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(campaignType.getId().intValue())))
            .andExpect(jsonPath("$.[*].campaignType").value(hasItem(DEFAULT_CAMPAIGN_TYPE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }

    @Test
    @Transactional
    void getCampaignType() throws Exception {
        // Initialize the database
        campaignTypeRepository.saveAndFlush(campaignType);

        // Get the campaignType
        restCampaignTypeMockMvc
            .perform(get(ENTITY_API_URL_ID, campaignType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(campaignType.getId().intValue()))
            .andExpect(jsonPath("$.campaignType").value(DEFAULT_CAMPAIGN_TYPE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }

    @Test
    @Transactional
    void getNonExistingCampaignType() throws Exception {
        // Get the campaignType
        restCampaignTypeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCampaignType() throws Exception {
        // Initialize the database
        campaignTypeRepository.saveAndFlush(campaignType);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the campaignType
        CampaignType updatedCampaignType = campaignTypeRepository.findById(campaignType.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedCampaignType are not directly saved in db
        em.detach(updatedCampaignType);
        updatedCampaignType.campaignType(UPDATED_CAMPAIGN_TYPE).description(UPDATED_DESCRIPTION);

        restCampaignTypeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedCampaignType.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedCampaignType))
            )
            .andExpect(status().isOk());

        // Validate the CampaignType in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedCampaignTypeToMatchAllProperties(updatedCampaignType);
    }

    @Test
    @Transactional
    void putNonExistingCampaignType() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        campaignType.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCampaignTypeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, campaignType.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(campaignType))
            )
            .andExpect(status().isBadRequest());

        // Validate the CampaignType in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCampaignType() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        campaignType.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCampaignTypeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(campaignType))
            )
            .andExpect(status().isBadRequest());

        // Validate the CampaignType in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCampaignType() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        campaignType.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCampaignTypeMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(campaignType)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CampaignType in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCampaignTypeWithPatch() throws Exception {
        // Initialize the database
        campaignTypeRepository.saveAndFlush(campaignType);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the campaignType using partial update
        CampaignType partialUpdatedCampaignType = new CampaignType();
        partialUpdatedCampaignType.setId(campaignType.getId());

        restCampaignTypeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCampaignType.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCampaignType))
            )
            .andExpect(status().isOk());

        // Validate the CampaignType in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCampaignTypeUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedCampaignType, campaignType),
            getPersistedCampaignType(campaignType)
        );
    }

    @Test
    @Transactional
    void fullUpdateCampaignTypeWithPatch() throws Exception {
        // Initialize the database
        campaignTypeRepository.saveAndFlush(campaignType);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the campaignType using partial update
        CampaignType partialUpdatedCampaignType = new CampaignType();
        partialUpdatedCampaignType.setId(campaignType.getId());

        partialUpdatedCampaignType.campaignType(UPDATED_CAMPAIGN_TYPE).description(UPDATED_DESCRIPTION);

        restCampaignTypeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCampaignType.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCampaignType))
            )
            .andExpect(status().isOk());

        // Validate the CampaignType in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCampaignTypeUpdatableFieldsEquals(partialUpdatedCampaignType, getPersistedCampaignType(partialUpdatedCampaignType));
    }

    @Test
    @Transactional
    void patchNonExistingCampaignType() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        campaignType.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCampaignTypeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, campaignType.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(campaignType))
            )
            .andExpect(status().isBadRequest());

        // Validate the CampaignType in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCampaignType() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        campaignType.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCampaignTypeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(campaignType))
            )
            .andExpect(status().isBadRequest());

        // Validate the CampaignType in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCampaignType() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        campaignType.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCampaignTypeMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(campaignType)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CampaignType in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCampaignType() throws Exception {
        // Initialize the database
        campaignTypeRepository.saveAndFlush(campaignType);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the campaignType
        restCampaignTypeMockMvc
            .perform(delete(ENTITY_API_URL_ID, campaignType.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return campaignTypeRepository.count();
    }

    protected void assertIncrementedRepositoryCount(long countBefore) {
        assertThat(countBefore + 1).isEqualTo(getRepositoryCount());
    }

    protected void assertDecrementedRepositoryCount(long countBefore) {
        assertThat(countBefore - 1).isEqualTo(getRepositoryCount());
    }

    protected void assertSameRepositoryCount(long countBefore) {
        assertThat(countBefore).isEqualTo(getRepositoryCount());
    }

    protected CampaignType getPersistedCampaignType(CampaignType campaignType) {
        return campaignTypeRepository.findById(campaignType.getId()).orElseThrow();
    }

    protected void assertPersistedCampaignTypeToMatchAllProperties(CampaignType expectedCampaignType) {
        assertCampaignTypeAllPropertiesEquals(expectedCampaignType, getPersistedCampaignType(expectedCampaignType));
    }

    protected void assertPersistedCampaignTypeToMatchUpdatableProperties(CampaignType expectedCampaignType) {
        assertCampaignTypeAllUpdatablePropertiesEquals(expectedCampaignType, getPersistedCampaignType(expectedCampaignType));
    }
}
