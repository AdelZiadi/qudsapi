package org.nmcpye.datarun.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.nmcpye.datarun.domain.ChvSessionsAsserts.*;
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
import org.nmcpye.datarun.domain.ChvSessions;
import org.nmcpye.datarun.domain.enumeration.MSessionSubject;
import org.nmcpye.datarun.repository.ChvSessionsRepository;
import org.nmcpye.datarun.service.ChvSessionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ChvSessionsResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class ChvSessionsResourceIT {

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

    private static final Instant DEFAULT_START_ENTRY_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_START_ENTRY_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Boolean DEFAULT_DELETED = false;
    private static final Boolean UPDATED_DELETED = true;

    private static final String ENTITY_API_URL = "/api/chv-sessions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ChvSessionsRepository chvSessionsRepository;

    @Mock
    private ChvSessionsRepository chvSessionsRepositoryMock;

    @Mock
    private ChvSessionsService chvSessionsServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restChvSessionsMockMvc;

    private ChvSessions chvSessions;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ChvSessions createEntity(EntityManager em) {
        ChvSessions chvSessions = new ChvSessions()
            .uid(DEFAULT_UID)
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .sessionDate(DEFAULT_SESSION_DATE)
            .subject(DEFAULT_SUBJECT)
            .sessions(DEFAULT_SESSIONS)
            .people(DEFAULT_PEOPLE)
            .comment(DEFAULT_COMMENT)
            .startEntryTime(DEFAULT_START_ENTRY_TIME)
            .deleted(DEFAULT_DELETED);
        return chvSessions;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ChvSessions createUpdatedEntity(EntityManager em) {
        ChvSessions chvSessions = new ChvSessions()
            .uid(UPDATED_UID)
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .sessionDate(UPDATED_SESSION_DATE)
            .subject(UPDATED_SUBJECT)
            .sessions(UPDATED_SESSIONS)
            .people(UPDATED_PEOPLE)
            .comment(UPDATED_COMMENT)
            .startEntryTime(UPDATED_START_ENTRY_TIME)
            .deleted(UPDATED_DELETED);
        return chvSessions;
    }

    @BeforeEach
    public void initTest() {
        chvSessions = createEntity(em);
    }

    @Test
    @Transactional
    void createChvSessions() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the ChvSessions
        var returnedChvSessions = om.readValue(
            restChvSessionsMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(chvSessions)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            ChvSessions.class
        );

        // Validate the ChvSessions in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertChvSessionsUpdatableFieldsEquals(returnedChvSessions, getPersistedChvSessions(returnedChvSessions));
    }

    @Test
    @Transactional
    void createChvSessionsWithExistingId() throws Exception {
        // Create the ChvSessions with an existing ID
        chvSessions.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restChvSessionsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(chvSessions)))
            .andExpect(status().isBadRequest());

        // Validate the ChvSessions in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkSessionDateIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        chvSessions.setSessionDate(null);

        // Create the ChvSessions, which fails.

        restChvSessionsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(chvSessions)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSessionsIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        chvSessions.setSessions(null);

        // Create the ChvSessions, which fails.

        restChvSessionsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(chvSessions)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPeopleIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        chvSessions.setPeople(null);

        // Create the ChvSessions, which fails.

        restChvSessionsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(chvSessions)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllChvSessions() throws Exception {
        // Initialize the database
        chvSessionsRepository.saveAndFlush(chvSessions);

        // Get all the chvSessionsList
        restChvSessionsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(chvSessions.getId().intValue())))
            .andExpect(jsonPath("$.[*].uid").value(hasItem(DEFAULT_UID)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].sessionDate").value(hasItem(DEFAULT_SESSION_DATE.toString())))
            .andExpect(jsonPath("$.[*].subject").value(hasItem(DEFAULT_SUBJECT.toString())))
            .andExpect(jsonPath("$.[*].sessions").value(hasItem(DEFAULT_SESSIONS)))
            .andExpect(jsonPath("$.[*].people").value(hasItem(DEFAULT_PEOPLE)))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT)))
            .andExpect(jsonPath("$.[*].startEntryTime").value(hasItem(DEFAULT_START_ENTRY_TIME.toString())))
            .andExpect(jsonPath("$.[*].deleted").value(hasItem(DEFAULT_DELETED.booleanValue())));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllChvSessionsWithEagerRelationshipsIsEnabled() throws Exception {
        when(chvSessionsServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restChvSessionsMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(chvSessionsServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllChvSessionsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(chvSessionsServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restChvSessionsMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(chvSessionsRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @Transactional
    void getChvSessions() throws Exception {
        // Initialize the database
        chvSessionsRepository.saveAndFlush(chvSessions);

        // Get the chvSessions
        restChvSessionsMockMvc
            .perform(get(ENTITY_API_URL_ID, chvSessions.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(chvSessions.getId().intValue()))
            .andExpect(jsonPath("$.uid").value(DEFAULT_UID))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.sessionDate").value(DEFAULT_SESSION_DATE.toString()))
            .andExpect(jsonPath("$.subject").value(DEFAULT_SUBJECT.toString()))
            .andExpect(jsonPath("$.sessions").value(DEFAULT_SESSIONS))
            .andExpect(jsonPath("$.people").value(DEFAULT_PEOPLE))
            .andExpect(jsonPath("$.comment").value(DEFAULT_COMMENT))
            .andExpect(jsonPath("$.startEntryTime").value(DEFAULT_START_ENTRY_TIME.toString()))
            .andExpect(jsonPath("$.deleted").value(DEFAULT_DELETED.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingChvSessions() throws Exception {
        // Get the chvSessions
        restChvSessionsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingChvSessions() throws Exception {
        // Initialize the database
        chvSessionsRepository.saveAndFlush(chvSessions);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the chvSessions
        ChvSessions updatedChvSessions = chvSessionsRepository.findById(chvSessions.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedChvSessions are not directly saved in db
        em.detach(updatedChvSessions);
        updatedChvSessions
            .uid(UPDATED_UID)
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .sessionDate(UPDATED_SESSION_DATE)
            .subject(UPDATED_SUBJECT)
            .sessions(UPDATED_SESSIONS)
            .people(UPDATED_PEOPLE)
            .comment(UPDATED_COMMENT)
            .startEntryTime(UPDATED_START_ENTRY_TIME)
            .deleted(UPDATED_DELETED);

        restChvSessionsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedChvSessions.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedChvSessions))
            )
            .andExpect(status().isOk());

        // Validate the ChvSessions in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedChvSessionsToMatchAllProperties(updatedChvSessions);
    }

    @Test
    @Transactional
    void putNonExistingChvSessions() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        chvSessions.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restChvSessionsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, chvSessions.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(chvSessions))
            )
            .andExpect(status().isBadRequest());

        // Validate the ChvSessions in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchChvSessions() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        chvSessions.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restChvSessionsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(chvSessions))
            )
            .andExpect(status().isBadRequest());

        // Validate the ChvSessions in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamChvSessions() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        chvSessions.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restChvSessionsMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(chvSessions)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ChvSessions in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateChvSessionsWithPatch() throws Exception {
        // Initialize the database
        chvSessionsRepository.saveAndFlush(chvSessions);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the chvSessions using partial update
        ChvSessions partialUpdatedChvSessions = new ChvSessions();
        partialUpdatedChvSessions.setId(chvSessions.getId());

        partialUpdatedChvSessions
            .uid(UPDATED_UID)
            .name(UPDATED_NAME)
            .sessionDate(UPDATED_SESSION_DATE)
            .comment(UPDATED_COMMENT)
            .deleted(UPDATED_DELETED);

        restChvSessionsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedChvSessions.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedChvSessions))
            )
            .andExpect(status().isOk());

        // Validate the ChvSessions in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertChvSessionsUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedChvSessions, chvSessions),
            getPersistedChvSessions(chvSessions)
        );
    }

    @Test
    @Transactional
    void fullUpdateChvSessionsWithPatch() throws Exception {
        // Initialize the database
        chvSessionsRepository.saveAndFlush(chvSessions);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the chvSessions using partial update
        ChvSessions partialUpdatedChvSessions = new ChvSessions();
        partialUpdatedChvSessions.setId(chvSessions.getId());

        partialUpdatedChvSessions
            .uid(UPDATED_UID)
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .sessionDate(UPDATED_SESSION_DATE)
            .subject(UPDATED_SUBJECT)
            .sessions(UPDATED_SESSIONS)
            .people(UPDATED_PEOPLE)
            .comment(UPDATED_COMMENT)
            .startEntryTime(UPDATED_START_ENTRY_TIME)
            .deleted(UPDATED_DELETED);

        restChvSessionsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedChvSessions.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedChvSessions))
            )
            .andExpect(status().isOk());

        // Validate the ChvSessions in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertChvSessionsUpdatableFieldsEquals(partialUpdatedChvSessions, getPersistedChvSessions(partialUpdatedChvSessions));
    }

    @Test
    @Transactional
    void patchNonExistingChvSessions() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        chvSessions.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restChvSessionsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, chvSessions.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(chvSessions))
            )
            .andExpect(status().isBadRequest());

        // Validate the ChvSessions in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchChvSessions() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        chvSessions.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restChvSessionsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(chvSessions))
            )
            .andExpect(status().isBadRequest());

        // Validate the ChvSessions in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamChvSessions() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        chvSessions.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restChvSessionsMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(chvSessions)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ChvSessions in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteChvSessions() throws Exception {
        // Initialize the database
        chvSessionsRepository.saveAndFlush(chvSessions);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the chvSessions
        restChvSessionsMockMvc
            .perform(delete(ENTITY_API_URL_ID, chvSessions.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return chvSessionsRepository.count();
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

    protected ChvSessions getPersistedChvSessions(ChvSessions chvSessions) {
        return chvSessionsRepository.findById(chvSessions.getId()).orElseThrow();
    }

    protected void assertPersistedChvSessionsToMatchAllProperties(ChvSessions expectedChvSessions) {
        assertChvSessionsAllPropertiesEquals(expectedChvSessions, getPersistedChvSessions(expectedChvSessions));
    }

    protected void assertPersistedChvSessionsToMatchUpdatableProperties(ChvSessions expectedChvSessions) {
        assertChvSessionsAllUpdatablePropertiesEquals(expectedChvSessions, getPersistedChvSessions(expectedChvSessions));
    }
}
