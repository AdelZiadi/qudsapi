package org.nmcpye.datarun.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.nmcpye.datarun.domain.CampaignAsserts.*;
import static org.nmcpye.datarun.web.rest.TestUtil.createUpdateProxyForBean;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nmcpye.datarun.IntegrationTest;
import org.nmcpye.datarun.domain.Campaign;
import org.nmcpye.datarun.repository.CampaignRepository;
import org.nmcpye.datarun.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link CampaignResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class CampaignResourceIT {

    private static final String DEFAULT_CAMPAIGN_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CAMPAIGN_CODE = "BBBBBBBBBB";

    private static final Instant DEFAULT_CAMPAIGN_STARTED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CAMPAIGN_STARTED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_CAMPAIGN_DAYS = 0;
    private static final Integer UPDATED_CAMPAIGN_DAYS = 1;

    private static final Integer DEFAULT_CAMPAIGN_YEAR = 0;
    private static final Integer UPDATED_CAMPAIGN_YEAR = 1;

    private static final String DEFAULT_CAMPAIGN_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_CAMPAIGN_NOTE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ENABLED = false;
    private static final Boolean UPDATED_ENABLED = true;

    private static final String ENTITY_API_URL = "/api/campaigns";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private CampaignRepository campaignRepository;

    @Mock
    private CampaignRepository campaignRepositoryMock;

    @Mock
    private CampaignService campaignServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCampaignMockMvc;

    private Campaign campaign;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Campaign createEntity(EntityManager em) {
        Campaign campaign = new Campaign()
            .campaignCode(DEFAULT_CAMPAIGN_CODE)
            .campaignStartedDate(DEFAULT_CAMPAIGN_STARTED_DATE)
            .campaignDays(DEFAULT_CAMPAIGN_DAYS)
            .campaignYear(DEFAULT_CAMPAIGN_YEAR)
            .campaignNote(DEFAULT_CAMPAIGN_NOTE)
            .enabled(DEFAULT_ENABLED);
        return campaign;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Campaign createUpdatedEntity(EntityManager em) {
        Campaign campaign = new Campaign()
            .campaignCode(UPDATED_CAMPAIGN_CODE)
            .campaignStartedDate(UPDATED_CAMPAIGN_STARTED_DATE)
            .campaignDays(UPDATED_CAMPAIGN_DAYS)
            .campaignYear(UPDATED_CAMPAIGN_YEAR)
            .campaignNote(UPDATED_CAMPAIGN_NOTE)
            .enabled(UPDATED_ENABLED);
        return campaign;
    }

    @BeforeEach
    public void initTest() {
        campaign = createEntity(em);
    }

    @Test
    @Transactional
    void createCampaign() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Campaign
        var returnedCampaign = om.readValue(
            restCampaignMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(campaign)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Campaign.class
        );

        // Validate the Campaign in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertCampaignUpdatableFieldsEquals(returnedCampaign, getPersistedCampaign(returnedCampaign));
    }

    @Test
    @Transactional
    void createCampaignWithExistingId() throws Exception {
        // Create the Campaign with an existing ID
        campaign.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCampaignMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(campaign)))
            .andExpect(status().isBadRequest());

        // Validate the Campaign in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkCampaignCodeIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        campaign.setCampaignCode(null);

        // Create the Campaign, which fails.

        restCampaignMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(campaign)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCampaignStartedDateIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        campaign.setCampaignStartedDate(null);

        // Create the Campaign, which fails.

        restCampaignMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(campaign)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEnabledIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        campaign.setEnabled(null);

        // Create the Campaign, which fails.

        restCampaignMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(campaign)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllCampaigns() throws Exception {
        // Initialize the database
        campaignRepository.saveAndFlush(campaign);

        // Get all the campaignList
        restCampaignMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(campaign.getId().intValue())))
            .andExpect(jsonPath("$.[*].campaignCode").value(hasItem(DEFAULT_CAMPAIGN_CODE)))
            .andExpect(jsonPath("$.[*].campaignStartedDate").value(hasItem(DEFAULT_CAMPAIGN_STARTED_DATE.toString())))
            .andExpect(jsonPath("$.[*].campaignDays").value(hasItem(DEFAULT_CAMPAIGN_DAYS)))
            .andExpect(jsonPath("$.[*].campaignYear").value(hasItem(DEFAULT_CAMPAIGN_YEAR)))
            .andExpect(jsonPath("$.[*].campaignNote").value(hasItem(DEFAULT_CAMPAIGN_NOTE)))
            .andExpect(jsonPath("$.[*].enabled").value(hasItem(DEFAULT_ENABLED.booleanValue())));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllCampaignsWithEagerRelationshipsIsEnabled() throws Exception {
        when(campaignServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restCampaignMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(campaignServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllCampaignsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(campaignServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restCampaignMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(campaignRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @Transactional
    void getCampaign() throws Exception {
        // Initialize the database
        campaignRepository.saveAndFlush(campaign);

        // Get the campaign
        restCampaignMockMvc
            .perform(get(ENTITY_API_URL_ID, campaign.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(campaign.getId().intValue()))
            .andExpect(jsonPath("$.campaignCode").value(DEFAULT_CAMPAIGN_CODE))
            .andExpect(jsonPath("$.campaignStartedDate").value(DEFAULT_CAMPAIGN_STARTED_DATE.toString()))
            .andExpect(jsonPath("$.campaignDays").value(DEFAULT_CAMPAIGN_DAYS))
            .andExpect(jsonPath("$.campaignYear").value(DEFAULT_CAMPAIGN_YEAR))
            .andExpect(jsonPath("$.campaignNote").value(DEFAULT_CAMPAIGN_NOTE))
            .andExpect(jsonPath("$.enabled").value(DEFAULT_ENABLED.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingCampaign() throws Exception {
        // Get the campaign
        restCampaignMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCampaign() throws Exception {
        // Initialize the database
        campaignRepository.saveAndFlush(campaign);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the campaign
        Campaign updatedCampaign = campaignRepository.findById(campaign.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedCampaign are not directly saved in db
        em.detach(updatedCampaign);
        updatedCampaign
            .campaignCode(UPDATED_CAMPAIGN_CODE)
            .campaignStartedDate(UPDATED_CAMPAIGN_STARTED_DATE)
            .campaignDays(UPDATED_CAMPAIGN_DAYS)
            .campaignYear(UPDATED_CAMPAIGN_YEAR)
            .campaignNote(UPDATED_CAMPAIGN_NOTE)
            .enabled(UPDATED_ENABLED);

        restCampaignMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedCampaign.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedCampaign))
            )
            .andExpect(status().isOk());

        // Validate the Campaign in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedCampaignToMatchAllProperties(updatedCampaign);
    }

    @Test
    @Transactional
    void putNonExistingCampaign() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        campaign.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCampaignMockMvc
            .perform(
                put(ENTITY_API_URL_ID, campaign.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(campaign))
            )
            .andExpect(status().isBadRequest());

        // Validate the Campaign in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCampaign() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        campaign.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCampaignMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(campaign))
            )
            .andExpect(status().isBadRequest());

        // Validate the Campaign in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCampaign() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        campaign.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCampaignMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(campaign)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Campaign in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCampaignWithPatch() throws Exception {
        // Initialize the database
        campaignRepository.saveAndFlush(campaign);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the campaign using partial update
        Campaign partialUpdatedCampaign = new Campaign();
        partialUpdatedCampaign.setId(campaign.getId());

        partialUpdatedCampaign.campaignCode(UPDATED_CAMPAIGN_CODE).campaignYear(UPDATED_CAMPAIGN_YEAR);

        restCampaignMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCampaign.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCampaign))
            )
            .andExpect(status().isOk());

        // Validate the Campaign in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCampaignUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedCampaign, campaign), getPersistedCampaign(campaign));
    }

    @Test
    @Transactional
    void fullUpdateCampaignWithPatch() throws Exception {
        // Initialize the database
        campaignRepository.saveAndFlush(campaign);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the campaign using partial update
        Campaign partialUpdatedCampaign = new Campaign();
        partialUpdatedCampaign.setId(campaign.getId());

        partialUpdatedCampaign
            .campaignCode(UPDATED_CAMPAIGN_CODE)
            .campaignStartedDate(UPDATED_CAMPAIGN_STARTED_DATE)
            .campaignDays(UPDATED_CAMPAIGN_DAYS)
            .campaignYear(UPDATED_CAMPAIGN_YEAR)
            .campaignNote(UPDATED_CAMPAIGN_NOTE)
            .enabled(UPDATED_ENABLED);

        restCampaignMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCampaign.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCampaign))
            )
            .andExpect(status().isOk());

        // Validate the Campaign in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCampaignUpdatableFieldsEquals(partialUpdatedCampaign, getPersistedCampaign(partialUpdatedCampaign));
    }

    @Test
    @Transactional
    void patchNonExistingCampaign() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        campaign.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCampaignMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, campaign.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(campaign))
            )
            .andExpect(status().isBadRequest());

        // Validate the Campaign in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCampaign() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        campaign.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCampaignMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(campaign))
            )
            .andExpect(status().isBadRequest());

        // Validate the Campaign in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCampaign() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        campaign.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCampaignMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(campaign)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Campaign in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCampaign() throws Exception {
        // Initialize the database
        campaignRepository.saveAndFlush(campaign);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the campaign
        restCampaignMockMvc
            .perform(delete(ENTITY_API_URL_ID, campaign.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return campaignRepository.count();
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

    protected Campaign getPersistedCampaign(Campaign campaign) {
        return campaignRepository.findById(campaign.getId()).orElseThrow();
    }

    protected void assertPersistedCampaignToMatchAllProperties(Campaign expectedCampaign) {
        assertCampaignAllPropertiesEquals(expectedCampaign, getPersistedCampaign(expectedCampaign));
    }

    protected void assertPersistedCampaignToMatchUpdatableProperties(Campaign expectedCampaign) {
        assertCampaignAllUpdatablePropertiesEquals(expectedCampaign, getPersistedCampaign(expectedCampaign));
    }
}
