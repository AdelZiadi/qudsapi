package org.nmcpye.datarun.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.nmcpye.datarun.domain.TeamFormAccessAsserts.*;
import static org.nmcpye.datarun.web.rest.TestUtil.createUpdateProxyForBean;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nmcpye.datarun.IntegrationTest;
import org.nmcpye.datarun.domain.TeamFormAccess;
import org.nmcpye.datarun.domain.enumeration.MSessionSubject;
import org.nmcpye.datarun.domain.enumeration.SyncableStatus;
import org.nmcpye.datarun.repository.TeamFormAccessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link TeamFormAccessResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TeamFormAccessResourceIT {

    private static final String DEFAULT_UID = "AAAAAAAAAA";
    private static final String UPDATED_UID = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Instant DEFAULT_SESSION_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_SESSION_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final MSessionSubject DEFAULT_SUBJECT = MSessionSubject.ITNs;
    private static final MSessionSubject UPDATED_SUBJECT = MSessionSubject.BreadingSite;

    private static final Integer DEFAULT_SESSIONS = 1;
    private static final Integer UPDATED_SESSIONS = 2;

    private static final Integer DEFAULT_PEOPLE = 1;
    private static final Integer UPDATED_PEOPLE = 2;

    private static final String DEFAULT_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_COMMENT = "BBBBBBBBBB";

    private static final Boolean DEFAULT_DELETED = false;
    private static final Boolean UPDATED_DELETED = true;

    private static final Instant DEFAULT_START_ENTRY_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_START_ENTRY_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_FINISHED_ENTRY_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_FINISHED_ENTRY_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final SyncableStatus DEFAULT_STATUS = SyncableStatus.ACTIVE;
    private static final SyncableStatus UPDATED_STATUS = SyncableStatus.COMPLETED;

    private static final String ENTITY_API_URL = "/api/team-form-accesses";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TeamFormAccessRepository teamFormAccessRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTeamFormAccessMockMvc;

    private TeamFormAccess teamFormAccess;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TeamFormAccess createEntity(EntityManager em) {
        TeamFormAccess teamFormAccess = new TeamFormAccess()
            .uid(DEFAULT_UID)
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .sessionDate(DEFAULT_SESSION_DATE)
            .subject(DEFAULT_SUBJECT)
            .sessions(DEFAULT_SESSIONS)
            .people(DEFAULT_PEOPLE)
            .comment(DEFAULT_COMMENT)
            .deleted(DEFAULT_DELETED)
            .startEntryTime(DEFAULT_START_ENTRY_TIME)
            .finishedEntryTime(DEFAULT_FINISHED_ENTRY_TIME)
            .status(DEFAULT_STATUS);
        return teamFormAccess;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TeamFormAccess createUpdatedEntity(EntityManager em) {
        TeamFormAccess teamFormAccess = new TeamFormAccess()
            .uid(UPDATED_UID)
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .sessionDate(UPDATED_SESSION_DATE)
            .subject(UPDATED_SUBJECT)
            .sessions(UPDATED_SESSIONS)
            .people(UPDATED_PEOPLE)
            .comment(UPDATED_COMMENT)
            .deleted(UPDATED_DELETED)
            .startEntryTime(UPDATED_START_ENTRY_TIME)
            .finishedEntryTime(UPDATED_FINISHED_ENTRY_TIME)
            .status(UPDATED_STATUS);
        return teamFormAccess;
    }

    @BeforeEach
    public void initTest() {
        teamFormAccess = createEntity(em);
    }

    @Test
    @Transactional
    void createTeamFormAccess() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the TeamFormAccess
        var returnedTeamFormAccess = om.readValue(
            restTeamFormAccessMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(teamFormAccess)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            TeamFormAccess.class
        );

        // Validate the TeamFormAccess in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertTeamFormAccessUpdatableFieldsEquals(returnedTeamFormAccess, getPersistedTeamFormAccess(returnedTeamFormAccess));
    }

    @Test
    @Transactional
    void createTeamFormAccessWithExistingId() throws Exception {
        // Create the TeamFormAccess with an existing ID
        teamFormAccess.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTeamFormAccessMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(teamFormAccess)))
            .andExpect(status().isBadRequest());

        // Validate the TeamFormAccess in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkUidIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        teamFormAccess.setUid(null);

        // Create the TeamFormAccess, which fails.

        restTeamFormAccessMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(teamFormAccess)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSessionDateIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        teamFormAccess.setSessionDate(null);

        // Create the TeamFormAccess, which fails.

        restTeamFormAccessMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(teamFormAccess)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSessionsIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        teamFormAccess.setSessions(null);

        // Create the TeamFormAccess, which fails.

        restTeamFormAccessMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(teamFormAccess)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPeopleIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        teamFormAccess.setPeople(null);

        // Create the TeamFormAccess, which fails.

        restTeamFormAccessMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(teamFormAccess)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTeamFormAccesses() throws Exception {
        // Initialize the database
        teamFormAccessRepository.saveAndFlush(teamFormAccess);

        // Get all the teamFormAccessList
        restTeamFormAccessMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(teamFormAccess.getId().intValue())))
            .andExpect(jsonPath("$.[*].uid").value(hasItem(DEFAULT_UID)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].sessionDate").value(hasItem(DEFAULT_SESSION_DATE.toString())))
            .andExpect(jsonPath("$.[*].subject").value(hasItem(DEFAULT_SUBJECT.toString())))
            .andExpect(jsonPath("$.[*].sessions").value(hasItem(DEFAULT_SESSIONS)))
            .andExpect(jsonPath("$.[*].people").value(hasItem(DEFAULT_PEOPLE)))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT)))
            .andExpect(jsonPath("$.[*].deleted").value(hasItem(DEFAULT_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].startEntryTime").value(hasItem(DEFAULT_START_ENTRY_TIME.toString())))
            .andExpect(jsonPath("$.[*].finishedEntryTime").value(hasItem(DEFAULT_FINISHED_ENTRY_TIME.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }

    @Test
    @Transactional
    void getTeamFormAccess() throws Exception {
        // Initialize the database
        teamFormAccessRepository.saveAndFlush(teamFormAccess);

        // Get the teamFormAccess
        restTeamFormAccessMockMvc
            .perform(get(ENTITY_API_URL_ID, teamFormAccess.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(teamFormAccess.getId().intValue()))
            .andExpect(jsonPath("$.uid").value(DEFAULT_UID))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.sessionDate").value(DEFAULT_SESSION_DATE.toString()))
            .andExpect(jsonPath("$.subject").value(DEFAULT_SUBJECT.toString()))
            .andExpect(jsonPath("$.sessions").value(DEFAULT_SESSIONS))
            .andExpect(jsonPath("$.people").value(DEFAULT_PEOPLE))
            .andExpect(jsonPath("$.comment").value(DEFAULT_COMMENT))
            .andExpect(jsonPath("$.deleted").value(DEFAULT_DELETED.booleanValue()))
            .andExpect(jsonPath("$.startEntryTime").value(DEFAULT_START_ENTRY_TIME.toString()))
            .andExpect(jsonPath("$.finishedEntryTime").value(DEFAULT_FINISHED_ENTRY_TIME.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    void getNonExistingTeamFormAccess() throws Exception {
        // Get the teamFormAccess
        restTeamFormAccessMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTeamFormAccess() throws Exception {
        // Initialize the database
        teamFormAccessRepository.saveAndFlush(teamFormAccess);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the teamFormAccess
        TeamFormAccess updatedTeamFormAccess = teamFormAccessRepository.findById(teamFormAccess.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedTeamFormAccess are not directly saved in db
        em.detach(updatedTeamFormAccess);
        updatedTeamFormAccess
            .uid(UPDATED_UID)
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .sessionDate(UPDATED_SESSION_DATE)
            .subject(UPDATED_SUBJECT)
            .sessions(UPDATED_SESSIONS)
            .people(UPDATED_PEOPLE)
            .comment(UPDATED_COMMENT)
            .deleted(UPDATED_DELETED)
            .startEntryTime(UPDATED_START_ENTRY_TIME)
            .finishedEntryTime(UPDATED_FINISHED_ENTRY_TIME)
            .status(UPDATED_STATUS);

        restTeamFormAccessMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTeamFormAccess.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedTeamFormAccess))
            )
            .andExpect(status().isOk());

        // Validate the TeamFormAccess in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedTeamFormAccessToMatchAllProperties(updatedTeamFormAccess);
    }

    @Test
    @Transactional
    void putNonExistingTeamFormAccess() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        teamFormAccess.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTeamFormAccessMockMvc
            .perform(
                put(ENTITY_API_URL_ID, teamFormAccess.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(teamFormAccess))
            )
            .andExpect(status().isBadRequest());

        // Validate the TeamFormAccess in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTeamFormAccess() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        teamFormAccess.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTeamFormAccessMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(teamFormAccess))
            )
            .andExpect(status().isBadRequest());

        // Validate the TeamFormAccess in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTeamFormAccess() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        teamFormAccess.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTeamFormAccessMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(teamFormAccess)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TeamFormAccess in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTeamFormAccessWithPatch() throws Exception {
        // Initialize the database
        teamFormAccessRepository.saveAndFlush(teamFormAccess);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the teamFormAccess using partial update
        TeamFormAccess partialUpdatedTeamFormAccess = new TeamFormAccess();
        partialUpdatedTeamFormAccess.setId(teamFormAccess.getId());

        partialUpdatedTeamFormAccess
            .uid(UPDATED_UID)
            .code(UPDATED_CODE)
            .sessions(UPDATED_SESSIONS)
            .people(UPDATED_PEOPLE)
            .comment(UPDATED_COMMENT);

        restTeamFormAccessMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTeamFormAccess.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedTeamFormAccess))
            )
            .andExpect(status().isOk());

        // Validate the TeamFormAccess in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertTeamFormAccessUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedTeamFormAccess, teamFormAccess),
            getPersistedTeamFormAccess(teamFormAccess)
        );
    }

    @Test
    @Transactional
    void fullUpdateTeamFormAccessWithPatch() throws Exception {
        // Initialize the database
        teamFormAccessRepository.saveAndFlush(teamFormAccess);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the teamFormAccess using partial update
        TeamFormAccess partialUpdatedTeamFormAccess = new TeamFormAccess();
        partialUpdatedTeamFormAccess.setId(teamFormAccess.getId());

        partialUpdatedTeamFormAccess
            .uid(UPDATED_UID)
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .sessionDate(UPDATED_SESSION_DATE)
            .subject(UPDATED_SUBJECT)
            .sessions(UPDATED_SESSIONS)
            .people(UPDATED_PEOPLE)
            .comment(UPDATED_COMMENT)
            .deleted(UPDATED_DELETED)
            .startEntryTime(UPDATED_START_ENTRY_TIME)
            .finishedEntryTime(UPDATED_FINISHED_ENTRY_TIME)
            .status(UPDATED_STATUS);

        restTeamFormAccessMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTeamFormAccess.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedTeamFormAccess))
            )
            .andExpect(status().isOk());

        // Validate the TeamFormAccess in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertTeamFormAccessUpdatableFieldsEquals(partialUpdatedTeamFormAccess, getPersistedTeamFormAccess(partialUpdatedTeamFormAccess));
    }

    @Test
    @Transactional
    void patchNonExistingTeamFormAccess() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        teamFormAccess.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTeamFormAccessMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, teamFormAccess.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(teamFormAccess))
            )
            .andExpect(status().isBadRequest());

        // Validate the TeamFormAccess in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTeamFormAccess() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        teamFormAccess.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTeamFormAccessMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(teamFormAccess))
            )
            .andExpect(status().isBadRequest());

        // Validate the TeamFormAccess in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTeamFormAccess() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        teamFormAccess.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTeamFormAccessMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(teamFormAccess)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TeamFormAccess in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTeamFormAccess() throws Exception {
        // Initialize the database
        teamFormAccessRepository.saveAndFlush(teamFormAccess);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the teamFormAccess
        restTeamFormAccessMockMvc
            .perform(delete(ENTITY_API_URL_ID, teamFormAccess.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return teamFormAccessRepository.count();
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

    protected TeamFormAccess getPersistedTeamFormAccess(TeamFormAccess teamFormAccess) {
        return teamFormAccessRepository.findById(teamFormAccess.getId()).orElseThrow();
    }

    protected void assertPersistedTeamFormAccessToMatchAllProperties(TeamFormAccess expectedTeamFormAccess) {
        assertTeamFormAccessAllPropertiesEquals(expectedTeamFormAccess, getPersistedTeamFormAccess(expectedTeamFormAccess));
    }

    protected void assertPersistedTeamFormAccessToMatchUpdatableProperties(TeamFormAccess expectedTeamFormAccess) {
        assertTeamFormAccessAllUpdatablePropertiesEquals(expectedTeamFormAccess, getPersistedTeamFormAccess(expectedTeamFormAccess));
    }
}
