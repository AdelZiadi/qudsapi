package org.nmcpye.datarun.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.nmcpye.datarun.domain.TeamAssignmentAsserts.*;
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
import org.nmcpye.datarun.domain.TeamAssignment;
import org.nmcpye.datarun.repository.TeamAssignmentRepository;
import org.nmcpye.datarun.service.TeamAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link TeamAssignmentResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class TeamAssignmentResourceIT {

    private static final Integer DEFAULT_PHASE_NO = 1;
    private static final Integer UPDATED_PHASE_NO = 2;

    private static final Long DEFAULT_FIELD_CODE = 1L;
    private static final Long UPDATED_FIELD_CODE = 2L;

    private static final Integer DEFAULT_DISTRICT_CODE = 1;
    private static final Integer UPDATED_DISTRICT_CODE = 2;

    private static final String DEFAULT_GOV = "AAAAAAAAAA";
    private static final String UPDATED_GOV = "BBBBBBBBBB";

    private static final String DEFAULT_DISTRICT = "AAAAAAAAAA";
    private static final String UPDATED_DISTRICT = "BBBBBBBBBB";

    private static final String DEFAULT_SUBDISTRICT = "AAAAAAAAAA";
    private static final String UPDATED_SUBDISTRICT = "BBBBBBBBBB";

    private static final String DEFAULT_VILLAGE = "AAAAAAAAAA";
    private static final String UPDATED_VILLAGE = "BBBBBBBBBB";

    private static final String DEFAULT_SUBVILLAGE = "AAAAAAAAAA";
    private static final String UPDATED_SUBVILLAGE = "BBBBBBBBBB";

    private static final String DEFAULT_PPD_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PPD_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_DAY_ID = 1;
    private static final Integer UPDATED_DAY_ID = 2;

    private static final Double DEFAULT_POPULATION = 1D;
    private static final Double UPDATED_POPULATION = 2D;

    private static final Integer DEFAULT_ITNS_PLANNED = 1;
    private static final Integer UPDATED_ITNS_PLANNED = 2;

    private static final Integer DEFAULT_TARGET_TYPE = 1;
    private static final Integer UPDATED_TARGET_TYPE = 2;

    private static final Double DEFAULT_LONGITUDE = 1D;
    private static final Double UPDATED_LONGITUDE = 2D;

    private static final Double DEFAULT_LATITUDE = 1D;
    private static final Double UPDATED_LATITUDE = 2D;

    private static final Instant DEFAULT_START_DAY_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_START_DAY_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/team-assignments";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TeamAssignmentRepository teamAssignmentRepository;

    @Mock
    private TeamAssignmentRepository teamAssignmentRepositoryMock;

    @Mock
    private TeamAssignmentService teamAssignmentServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTeamAssignmentMockMvc;

    private TeamAssignment teamAssignment;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TeamAssignment createEntity(EntityManager em) {
        TeamAssignment teamAssignment = new TeamAssignment()
            .phaseNo(DEFAULT_PHASE_NO)
            .fieldCode(DEFAULT_FIELD_CODE)
            .districtCode(DEFAULT_DISTRICT_CODE)
            .gov(DEFAULT_GOV)
            .district(DEFAULT_DISTRICT)
            .subdistrict(DEFAULT_SUBDISTRICT)
            .village(DEFAULT_VILLAGE)
            .subvillage(DEFAULT_SUBVILLAGE)
            .ppdName(DEFAULT_PPD_NAME)
            .dayId(DEFAULT_DAY_ID)
            .population(DEFAULT_POPULATION)
            .itnsPlanned(DEFAULT_ITNS_PLANNED)
            .targetType(DEFAULT_TARGET_TYPE)
            .longitude(DEFAULT_LONGITUDE)
            .latitude(DEFAULT_LATITUDE)
            .startDayDate(DEFAULT_START_DAY_DATE);
        return teamAssignment;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TeamAssignment createUpdatedEntity(EntityManager em) {
        TeamAssignment teamAssignment = new TeamAssignment()
            .phaseNo(UPDATED_PHASE_NO)
            .fieldCode(UPDATED_FIELD_CODE)
            .districtCode(UPDATED_DISTRICT_CODE)
            .gov(UPDATED_GOV)
            .district(UPDATED_DISTRICT)
            .subdistrict(UPDATED_SUBDISTRICT)
            .village(UPDATED_VILLAGE)
            .subvillage(UPDATED_SUBVILLAGE)
            .ppdName(UPDATED_PPD_NAME)
            .dayId(UPDATED_DAY_ID)
            .population(UPDATED_POPULATION)
            .itnsPlanned(UPDATED_ITNS_PLANNED)
            .targetType(UPDATED_TARGET_TYPE)
            .longitude(UPDATED_LONGITUDE)
            .latitude(UPDATED_LATITUDE)
            .startDayDate(UPDATED_START_DAY_DATE);
        return teamAssignment;
    }

    @BeforeEach
    public void initTest() {
        teamAssignment = createEntity(em);
    }

    @Test
    @Transactional
    void createTeamAssignment() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the TeamAssignment
        var returnedTeamAssignment = om.readValue(
            restTeamAssignmentMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(teamAssignment)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            TeamAssignment.class
        );

        // Validate the TeamAssignment in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertTeamAssignmentUpdatableFieldsEquals(returnedTeamAssignment, getPersistedTeamAssignment(returnedTeamAssignment));
    }

    @Test
    @Transactional
    void createTeamAssignmentWithExistingId() throws Exception {
        // Create the TeamAssignment with an existing ID
        teamAssignment.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTeamAssignmentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(teamAssignment)))
            .andExpect(status().isBadRequest());

        // Validate the TeamAssignment in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTeamAssignments() throws Exception {
        // Initialize the database
        teamAssignmentRepository.saveAndFlush(teamAssignment);

        // Get all the teamAssignmentList
        restTeamAssignmentMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(teamAssignment.getId().intValue())))
            .andExpect(jsonPath("$.[*].phaseNo").value(hasItem(DEFAULT_PHASE_NO)))
            .andExpect(jsonPath("$.[*].fieldCode").value(hasItem(DEFAULT_FIELD_CODE.intValue())))
            .andExpect(jsonPath("$.[*].districtCode").value(hasItem(DEFAULT_DISTRICT_CODE)))
            .andExpect(jsonPath("$.[*].gov").value(hasItem(DEFAULT_GOV)))
            .andExpect(jsonPath("$.[*].district").value(hasItem(DEFAULT_DISTRICT)))
            .andExpect(jsonPath("$.[*].subdistrict").value(hasItem(DEFAULT_SUBDISTRICT)))
            .andExpect(jsonPath("$.[*].village").value(hasItem(DEFAULT_VILLAGE)))
            .andExpect(jsonPath("$.[*].subvillage").value(hasItem(DEFAULT_SUBVILLAGE)))
            .andExpect(jsonPath("$.[*].ppdName").value(hasItem(DEFAULT_PPD_NAME)))
            .andExpect(jsonPath("$.[*].dayId").value(hasItem(DEFAULT_DAY_ID)))
            .andExpect(jsonPath("$.[*].population").value(hasItem(DEFAULT_POPULATION.doubleValue())))
            .andExpect(jsonPath("$.[*].itnsPlanned").value(hasItem(DEFAULT_ITNS_PLANNED)))
            .andExpect(jsonPath("$.[*].targetType").value(hasItem(DEFAULT_TARGET_TYPE)))
            .andExpect(jsonPath("$.[*].longitude").value(hasItem(DEFAULT_LONGITUDE.doubleValue())))
            .andExpect(jsonPath("$.[*].latitude").value(hasItem(DEFAULT_LATITUDE.doubleValue())))
            .andExpect(jsonPath("$.[*].startDayDate").value(hasItem(DEFAULT_START_DAY_DATE.toString())));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllTeamAssignmentsWithEagerRelationshipsIsEnabled() throws Exception {
        when(teamAssignmentServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restTeamAssignmentMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(teamAssignmentServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllTeamAssignmentsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(teamAssignmentServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restTeamAssignmentMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(teamAssignmentRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @Transactional
    void getTeamAssignment() throws Exception {
        // Initialize the database
        teamAssignmentRepository.saveAndFlush(teamAssignment);

        // Get the teamAssignment
        restTeamAssignmentMockMvc
            .perform(get(ENTITY_API_URL_ID, teamAssignment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(teamAssignment.getId().intValue()))
            .andExpect(jsonPath("$.phaseNo").value(DEFAULT_PHASE_NO))
            .andExpect(jsonPath("$.fieldCode").value(DEFAULT_FIELD_CODE.intValue()))
            .andExpect(jsonPath("$.districtCode").value(DEFAULT_DISTRICT_CODE))
            .andExpect(jsonPath("$.gov").value(DEFAULT_GOV))
            .andExpect(jsonPath("$.district").value(DEFAULT_DISTRICT))
            .andExpect(jsonPath("$.subdistrict").value(DEFAULT_SUBDISTRICT))
            .andExpect(jsonPath("$.village").value(DEFAULT_VILLAGE))
            .andExpect(jsonPath("$.subvillage").value(DEFAULT_SUBVILLAGE))
            .andExpect(jsonPath("$.ppdName").value(DEFAULT_PPD_NAME))
            .andExpect(jsonPath("$.dayId").value(DEFAULT_DAY_ID))
            .andExpect(jsonPath("$.population").value(DEFAULT_POPULATION.doubleValue()))
            .andExpect(jsonPath("$.itnsPlanned").value(DEFAULT_ITNS_PLANNED))
            .andExpect(jsonPath("$.targetType").value(DEFAULT_TARGET_TYPE))
            .andExpect(jsonPath("$.longitude").value(DEFAULT_LONGITUDE.doubleValue()))
            .andExpect(jsonPath("$.latitude").value(DEFAULT_LATITUDE.doubleValue()))
            .andExpect(jsonPath("$.startDayDate").value(DEFAULT_START_DAY_DATE.toString()));
    }

    @Test
    @Transactional
    void getNonExistingTeamAssignment() throws Exception {
        // Get the teamAssignment
        restTeamAssignmentMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTeamAssignment() throws Exception {
        // Initialize the database
        teamAssignmentRepository.saveAndFlush(teamAssignment);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the teamAssignment
        TeamAssignment updatedTeamAssignment = teamAssignmentRepository.findById(teamAssignment.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedTeamAssignment are not directly saved in db
        em.detach(updatedTeamAssignment);
        updatedTeamAssignment
            .phaseNo(UPDATED_PHASE_NO)
            .fieldCode(UPDATED_FIELD_CODE)
            .districtCode(UPDATED_DISTRICT_CODE)
            .gov(UPDATED_GOV)
            .district(UPDATED_DISTRICT)
            .subdistrict(UPDATED_SUBDISTRICT)
            .village(UPDATED_VILLAGE)
            .subvillage(UPDATED_SUBVILLAGE)
            .ppdName(UPDATED_PPD_NAME)
            .dayId(UPDATED_DAY_ID)
            .population(UPDATED_POPULATION)
            .itnsPlanned(UPDATED_ITNS_PLANNED)
            .targetType(UPDATED_TARGET_TYPE)
            .longitude(UPDATED_LONGITUDE)
            .latitude(UPDATED_LATITUDE)
            .startDayDate(UPDATED_START_DAY_DATE);

        restTeamAssignmentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTeamAssignment.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedTeamAssignment))
            )
            .andExpect(status().isOk());

        // Validate the TeamAssignment in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedTeamAssignmentToMatchAllProperties(updatedTeamAssignment);
    }

    @Test
    @Transactional
    void putNonExistingTeamAssignment() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        teamAssignment.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTeamAssignmentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, teamAssignment.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(teamAssignment))
            )
            .andExpect(status().isBadRequest());

        // Validate the TeamAssignment in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTeamAssignment() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        teamAssignment.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTeamAssignmentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(teamAssignment))
            )
            .andExpect(status().isBadRequest());

        // Validate the TeamAssignment in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTeamAssignment() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        teamAssignment.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTeamAssignmentMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(teamAssignment)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TeamAssignment in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTeamAssignmentWithPatch() throws Exception {
        // Initialize the database
        teamAssignmentRepository.saveAndFlush(teamAssignment);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the teamAssignment using partial update
        TeamAssignment partialUpdatedTeamAssignment = new TeamAssignment();
        partialUpdatedTeamAssignment.setId(teamAssignment.getId());

        partialUpdatedTeamAssignment
            .fieldCode(UPDATED_FIELD_CODE)
            .gov(UPDATED_GOV)
            .district(UPDATED_DISTRICT)
            .subdistrict(UPDATED_SUBDISTRICT)
            .subvillage(UPDATED_SUBVILLAGE)
            .ppdName(UPDATED_PPD_NAME)
            .itnsPlanned(UPDATED_ITNS_PLANNED)
            .targetType(UPDATED_TARGET_TYPE)
            .latitude(UPDATED_LATITUDE);

        restTeamAssignmentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTeamAssignment.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedTeamAssignment))
            )
            .andExpect(status().isOk());

        // Validate the TeamAssignment in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertTeamAssignmentUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedTeamAssignment, teamAssignment),
            getPersistedTeamAssignment(teamAssignment)
        );
    }

    @Test
    @Transactional
    void fullUpdateTeamAssignmentWithPatch() throws Exception {
        // Initialize the database
        teamAssignmentRepository.saveAndFlush(teamAssignment);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the teamAssignment using partial update
        TeamAssignment partialUpdatedTeamAssignment = new TeamAssignment();
        partialUpdatedTeamAssignment.setId(teamAssignment.getId());

        partialUpdatedTeamAssignment
            .phaseNo(UPDATED_PHASE_NO)
            .fieldCode(UPDATED_FIELD_CODE)
            .districtCode(UPDATED_DISTRICT_CODE)
            .gov(UPDATED_GOV)
            .district(UPDATED_DISTRICT)
            .subdistrict(UPDATED_SUBDISTRICT)
            .village(UPDATED_VILLAGE)
            .subvillage(UPDATED_SUBVILLAGE)
            .ppdName(UPDATED_PPD_NAME)
            .dayId(UPDATED_DAY_ID)
            .population(UPDATED_POPULATION)
            .itnsPlanned(UPDATED_ITNS_PLANNED)
            .targetType(UPDATED_TARGET_TYPE)
            .longitude(UPDATED_LONGITUDE)
            .latitude(UPDATED_LATITUDE)
            .startDayDate(UPDATED_START_DAY_DATE);

        restTeamAssignmentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTeamAssignment.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedTeamAssignment))
            )
            .andExpect(status().isOk());

        // Validate the TeamAssignment in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertTeamAssignmentUpdatableFieldsEquals(partialUpdatedTeamAssignment, getPersistedTeamAssignment(partialUpdatedTeamAssignment));
    }

    @Test
    @Transactional
    void patchNonExistingTeamAssignment() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        teamAssignment.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTeamAssignmentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, teamAssignment.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(teamAssignment))
            )
            .andExpect(status().isBadRequest());

        // Validate the TeamAssignment in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTeamAssignment() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        teamAssignment.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTeamAssignmentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(teamAssignment))
            )
            .andExpect(status().isBadRequest());

        // Validate the TeamAssignment in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTeamAssignment() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        teamAssignment.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTeamAssignmentMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(teamAssignment)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TeamAssignment in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTeamAssignment() throws Exception {
        // Initialize the database
        teamAssignmentRepository.saveAndFlush(teamAssignment);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the teamAssignment
        restTeamAssignmentMockMvc
            .perform(delete(ENTITY_API_URL_ID, teamAssignment.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return teamAssignmentRepository.count();
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

    protected TeamAssignment getPersistedTeamAssignment(TeamAssignment teamAssignment) {
        return teamAssignmentRepository.findById(teamAssignment.getId()).orElseThrow();
    }

    protected void assertPersistedTeamAssignmentToMatchAllProperties(TeamAssignment expectedTeamAssignment) {
        assertTeamAssignmentAllPropertiesEquals(expectedTeamAssignment, getPersistedTeamAssignment(expectedTeamAssignment));
    }

    protected void assertPersistedTeamAssignmentToMatchUpdatableProperties(TeamAssignment expectedTeamAssignment) {
        assertTeamAssignmentAllUpdatablePropertiesEquals(expectedTeamAssignment, getPersistedTeamAssignment(expectedTeamAssignment));
    }
}
