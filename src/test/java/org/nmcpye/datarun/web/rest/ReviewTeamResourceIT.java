package org.nmcpye.datarun.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.nmcpye.datarun.domain.ReviewTeamAsserts.*;
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
import org.nmcpye.datarun.domain.ReviewTeam;
import org.nmcpye.datarun.repository.ReviewTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ReviewTeamResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ReviewTeamResourceIT {

    private static final String DEFAULT_PROGRESS_ORNAME = "AAAAAAAAAA";
    private static final String UPDATED_PROGRESS_ORNAME = "BBBBBBBBBB";

    private static final String DEFAULT_PROGRESS_OR_USER = "AAAAAAAAAA";
    private static final String UPDATED_PROGRESS_OR_USER = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/review-teams";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ReviewTeamRepository reviewTeamRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restReviewTeamMockMvc;

    private ReviewTeam reviewTeam;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ReviewTeam createEntity(EntityManager em) {
        ReviewTeam reviewTeam = new ReviewTeam().progressOrname(DEFAULT_PROGRESS_ORNAME).progressOrUser(DEFAULT_PROGRESS_OR_USER);
        return reviewTeam;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ReviewTeam createUpdatedEntity(EntityManager em) {
        ReviewTeam reviewTeam = new ReviewTeam().progressOrname(UPDATED_PROGRESS_ORNAME).progressOrUser(UPDATED_PROGRESS_OR_USER);
        return reviewTeam;
    }

    @BeforeEach
    public void initTest() {
        reviewTeam = createEntity(em);
    }

    @Test
    @Transactional
    void createReviewTeam() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the ReviewTeam
        var returnedReviewTeam = om.readValue(
            restReviewTeamMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(reviewTeam)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            ReviewTeam.class
        );

        // Validate the ReviewTeam in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertReviewTeamUpdatableFieldsEquals(returnedReviewTeam, getPersistedReviewTeam(returnedReviewTeam));
    }

    @Test
    @Transactional
    void createReviewTeamWithExistingId() throws Exception {
        // Create the ReviewTeam with an existing ID
        reviewTeam.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restReviewTeamMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(reviewTeam)))
            .andExpect(status().isBadRequest());

        // Validate the ReviewTeam in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllReviewTeams() throws Exception {
        // Initialize the database
        reviewTeamRepository.saveAndFlush(reviewTeam);

        // Get all the reviewTeamList
        restReviewTeamMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(reviewTeam.getId().intValue())))
            .andExpect(jsonPath("$.[*].progressOrname").value(hasItem(DEFAULT_PROGRESS_ORNAME)))
            .andExpect(jsonPath("$.[*].progressOrUser").value(hasItem(DEFAULT_PROGRESS_OR_USER)));
    }

    @Test
    @Transactional
    void getReviewTeam() throws Exception {
        // Initialize the database
        reviewTeamRepository.saveAndFlush(reviewTeam);

        // Get the reviewTeam
        restReviewTeamMockMvc
            .perform(get(ENTITY_API_URL_ID, reviewTeam.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(reviewTeam.getId().intValue()))
            .andExpect(jsonPath("$.progressOrname").value(DEFAULT_PROGRESS_ORNAME))
            .andExpect(jsonPath("$.progressOrUser").value(DEFAULT_PROGRESS_OR_USER));
    }

    @Test
    @Transactional
    void getNonExistingReviewTeam() throws Exception {
        // Get the reviewTeam
        restReviewTeamMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingReviewTeam() throws Exception {
        // Initialize the database
        reviewTeamRepository.saveAndFlush(reviewTeam);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the reviewTeam
        ReviewTeam updatedReviewTeam = reviewTeamRepository.findById(reviewTeam.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedReviewTeam are not directly saved in db
        em.detach(updatedReviewTeam);
        updatedReviewTeam.progressOrname(UPDATED_PROGRESS_ORNAME).progressOrUser(UPDATED_PROGRESS_OR_USER);

        restReviewTeamMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedReviewTeam.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedReviewTeam))
            )
            .andExpect(status().isOk());

        // Validate the ReviewTeam in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedReviewTeamToMatchAllProperties(updatedReviewTeam);
    }

    @Test
    @Transactional
    void putNonExistingReviewTeam() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        reviewTeam.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReviewTeamMockMvc
            .perform(
                put(ENTITY_API_URL_ID, reviewTeam.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(reviewTeam))
            )
            .andExpect(status().isBadRequest());

        // Validate the ReviewTeam in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchReviewTeam() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        reviewTeam.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReviewTeamMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(reviewTeam))
            )
            .andExpect(status().isBadRequest());

        // Validate the ReviewTeam in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamReviewTeam() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        reviewTeam.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReviewTeamMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(reviewTeam)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ReviewTeam in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateReviewTeamWithPatch() throws Exception {
        // Initialize the database
        reviewTeamRepository.saveAndFlush(reviewTeam);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the reviewTeam using partial update
        ReviewTeam partialUpdatedReviewTeam = new ReviewTeam();
        partialUpdatedReviewTeam.setId(reviewTeam.getId());

        restReviewTeamMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedReviewTeam.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedReviewTeam))
            )
            .andExpect(status().isOk());

        // Validate the ReviewTeam in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertReviewTeamUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedReviewTeam, reviewTeam),
            getPersistedReviewTeam(reviewTeam)
        );
    }

    @Test
    @Transactional
    void fullUpdateReviewTeamWithPatch() throws Exception {
        // Initialize the database
        reviewTeamRepository.saveAndFlush(reviewTeam);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the reviewTeam using partial update
        ReviewTeam partialUpdatedReviewTeam = new ReviewTeam();
        partialUpdatedReviewTeam.setId(reviewTeam.getId());

        partialUpdatedReviewTeam.progressOrname(UPDATED_PROGRESS_ORNAME).progressOrUser(UPDATED_PROGRESS_OR_USER);

        restReviewTeamMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedReviewTeam.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedReviewTeam))
            )
            .andExpect(status().isOk());

        // Validate the ReviewTeam in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertReviewTeamUpdatableFieldsEquals(partialUpdatedReviewTeam, getPersistedReviewTeam(partialUpdatedReviewTeam));
    }

    @Test
    @Transactional
    void patchNonExistingReviewTeam() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        reviewTeam.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReviewTeamMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, reviewTeam.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(reviewTeam))
            )
            .andExpect(status().isBadRequest());

        // Validate the ReviewTeam in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchReviewTeam() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        reviewTeam.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReviewTeamMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(reviewTeam))
            )
            .andExpect(status().isBadRequest());

        // Validate the ReviewTeam in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamReviewTeam() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        reviewTeam.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReviewTeamMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(reviewTeam)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ReviewTeam in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteReviewTeam() throws Exception {
        // Initialize the database
        reviewTeamRepository.saveAndFlush(reviewTeam);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the reviewTeam
        restReviewTeamMockMvc
            .perform(delete(ENTITY_API_URL_ID, reviewTeam.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return reviewTeamRepository.count();
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

    protected ReviewTeam getPersistedReviewTeam(ReviewTeam reviewTeam) {
        return reviewTeamRepository.findById(reviewTeam.getId()).orElseThrow();
    }

    protected void assertPersistedReviewTeamToMatchAllProperties(ReviewTeam expectedReviewTeam) {
        assertReviewTeamAllPropertiesEquals(expectedReviewTeam, getPersistedReviewTeam(expectedReviewTeam));
    }

    protected void assertPersistedReviewTeamToMatchUpdatableProperties(ReviewTeam expectedReviewTeam) {
        assertReviewTeamAllUpdatablePropertiesEquals(expectedReviewTeam, getPersistedReviewTeam(expectedReviewTeam));
    }
}
