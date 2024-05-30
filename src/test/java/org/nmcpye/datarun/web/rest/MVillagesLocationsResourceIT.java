package org.nmcpye.datarun.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.nmcpye.datarun.domain.MVillagesLocationsAsserts.*;
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
import org.nmcpye.datarun.domain.MVillagesLocations;
import org.nmcpye.datarun.domain.enumeration.PublicLocationType;
import org.nmcpye.datarun.repository.MVillagesLocationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link MVillagesLocationsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class MVillagesLocationsResourceIT {

    private static final String DEFAULT_UID = "AAAAAAAAAA";
    private static final String UPDATED_UID = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final Integer DEFAULT_MAPPING_STATUS = 1;
    private static final Integer UPDATED_MAPPING_STATUS = 2;

    private static final Integer DEFAULT_DISTRICT_CODE = 1;
    private static final Integer UPDATED_DISTRICT_CODE = 2;

    private static final String DEFAULT_VILLAGE_UID = "AAAAAAAAAA";
    private static final String UPDATED_VILLAGE_UID = "BBBBBBBBBB";

    private static final String DEFAULT_SUBDISTRICT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SUBDISTRICT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_VILLAGE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_VILLAGE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SUBVILLAGE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SUBVILLAGE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_URBAN_RURAL_ID = 1;
    private static final Integer UPDATED_URBAN_RURAL_ID = 2;

    private static final String DEFAULT_URBAN_RURAL = "AAAAAAAAAA";
    private static final String UPDATED_URBAN_RURAL = "BBBBBBBBBB";

    private static final String DEFAULT_SETTLEMENT = "AAAAAAAAAA";
    private static final String UPDATED_SETTLEMENT = "BBBBBBBBBB";

    private static final Double DEFAULT_POP_2004 = 1D;
    private static final Double UPDATED_POP_2004 = 2D;

    private static final Double DEFAULT_POP_2022 = 1D;
    private static final Double UPDATED_POP_2022 = 2D;

    private static final Double DEFAULT_LONGITUDE = 1D;
    private static final Double UPDATED_LONGITUDE = 2D;

    private static final Double DEFAULT_LATITUDE = 1D;
    private static final Double UPDATED_LATITUDE = 2D;

    private static final String DEFAULT_PPC_CODE_GIS = "AAAAAAAAAA";
    private static final String UPDATED_PPC_CODE_GIS = "BBBBBBBBBB";

    private static final PublicLocationType DEFAULT_LEVEL = PublicLocationType.DISTRICT;
    private static final PublicLocationType UPDATED_LEVEL = PublicLocationType.SUBDISTRICT;

    private static final String ENTITY_API_URL = "/api/m-villages-locations";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private MVillagesLocationsRepository mVillagesLocationsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMVillagesLocationsMockMvc;

    private MVillagesLocations mVillagesLocations;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MVillagesLocations createEntity(EntityManager em) {
        MVillagesLocations mVillagesLocations = new MVillagesLocations()
            .uid(DEFAULT_UID)
            .code(DEFAULT_CODE)
            .mappingStatus(DEFAULT_MAPPING_STATUS)
            .districtCode(DEFAULT_DISTRICT_CODE)
            .villageUid(DEFAULT_VILLAGE_UID)
            .subdistrictName(DEFAULT_SUBDISTRICT_NAME)
            .villageName(DEFAULT_VILLAGE_NAME)
            .subvillageName(DEFAULT_SUBVILLAGE_NAME)
            .name(DEFAULT_NAME)
            .urbanRuralId(DEFAULT_URBAN_RURAL_ID)
            .urbanRural(DEFAULT_URBAN_RURAL)
            .settlement(DEFAULT_SETTLEMENT)
            .pop2004(DEFAULT_POP_2004)
            .pop2022(DEFAULT_POP_2022)
            .longitude(DEFAULT_LONGITUDE)
            .latitude(DEFAULT_LATITUDE)
            .ppcCodeGis(DEFAULT_PPC_CODE_GIS)
            .level(DEFAULT_LEVEL);
        return mVillagesLocations;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MVillagesLocations createUpdatedEntity(EntityManager em) {
        MVillagesLocations mVillagesLocations = new MVillagesLocations()
            .uid(UPDATED_UID)
            .code(UPDATED_CODE)
            .mappingStatus(UPDATED_MAPPING_STATUS)
            .districtCode(UPDATED_DISTRICT_CODE)
            .villageUid(UPDATED_VILLAGE_UID)
            .subdistrictName(UPDATED_SUBDISTRICT_NAME)
            .villageName(UPDATED_VILLAGE_NAME)
            .subvillageName(UPDATED_SUBVILLAGE_NAME)
            .name(UPDATED_NAME)
            .urbanRuralId(UPDATED_URBAN_RURAL_ID)
            .urbanRural(UPDATED_URBAN_RURAL)
            .settlement(UPDATED_SETTLEMENT)
            .pop2004(UPDATED_POP_2004)
            .pop2022(UPDATED_POP_2022)
            .longitude(UPDATED_LONGITUDE)
            .latitude(UPDATED_LATITUDE)
            .ppcCodeGis(UPDATED_PPC_CODE_GIS)
            .level(UPDATED_LEVEL);
        return mVillagesLocations;
    }

    @BeforeEach
    public void initTest() {
        mVillagesLocations = createEntity(em);
    }

    @Test
    @Transactional
    void createMVillagesLocations() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the MVillagesLocations
        var returnedMVillagesLocations = om.readValue(
            restMVillagesLocationsMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(mVillagesLocations)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            MVillagesLocations.class
        );

        // Validate the MVillagesLocations in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertMVillagesLocationsUpdatableFieldsEquals(
            returnedMVillagesLocations,
            getPersistedMVillagesLocations(returnedMVillagesLocations)
        );
    }

    @Test
    @Transactional
    void createMVillagesLocationsWithExistingId() throws Exception {
        // Create the MVillagesLocations with an existing ID
        mVillagesLocations.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restMVillagesLocationsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(mVillagesLocations)))
            .andExpect(status().isBadRequest());

        // Validate the MVillagesLocations in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkCodeIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        mVillagesLocations.setCode(null);

        // Create the MVillagesLocations, which fails.

        restMVillagesLocationsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(mVillagesLocations)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPpcCodeGisIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        mVillagesLocations.setPpcCodeGis(null);

        // Create the MVillagesLocations, which fails.

        restMVillagesLocationsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(mVillagesLocations)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllMVillagesLocations() throws Exception {
        // Initialize the database
        mVillagesLocationsRepository.saveAndFlush(mVillagesLocations);

        // Get all the mVillagesLocationsList
        restMVillagesLocationsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mVillagesLocations.getId().intValue())))
            .andExpect(jsonPath("$.[*].uid").value(hasItem(DEFAULT_UID)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].mappingStatus").value(hasItem(DEFAULT_MAPPING_STATUS)))
            .andExpect(jsonPath("$.[*].districtCode").value(hasItem(DEFAULT_DISTRICT_CODE)))
            .andExpect(jsonPath("$.[*].villageUid").value(hasItem(DEFAULT_VILLAGE_UID)))
            .andExpect(jsonPath("$.[*].subdistrictName").value(hasItem(DEFAULT_SUBDISTRICT_NAME)))
            .andExpect(jsonPath("$.[*].villageName").value(hasItem(DEFAULT_VILLAGE_NAME)))
            .andExpect(jsonPath("$.[*].subvillageName").value(hasItem(DEFAULT_SUBVILLAGE_NAME)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].urbanRuralId").value(hasItem(DEFAULT_URBAN_RURAL_ID)))
            .andExpect(jsonPath("$.[*].urbanRural").value(hasItem(DEFAULT_URBAN_RURAL)))
            .andExpect(jsonPath("$.[*].settlement").value(hasItem(DEFAULT_SETTLEMENT)))
            .andExpect(jsonPath("$.[*].pop2004").value(hasItem(DEFAULT_POP_2004.doubleValue())))
            .andExpect(jsonPath("$.[*].pop2022").value(hasItem(DEFAULT_POP_2022.doubleValue())))
            .andExpect(jsonPath("$.[*].longitude").value(hasItem(DEFAULT_LONGITUDE.doubleValue())))
            .andExpect(jsonPath("$.[*].latitude").value(hasItem(DEFAULT_LATITUDE.doubleValue())))
            .andExpect(jsonPath("$.[*].ppcCodeGis").value(hasItem(DEFAULT_PPC_CODE_GIS)))
            .andExpect(jsonPath("$.[*].level").value(hasItem(DEFAULT_LEVEL.toString())));
    }

    @Test
    @Transactional
    void getMVillagesLocations() throws Exception {
        // Initialize the database
        mVillagesLocationsRepository.saveAndFlush(mVillagesLocations);

        // Get the mVillagesLocations
        restMVillagesLocationsMockMvc
            .perform(get(ENTITY_API_URL_ID, mVillagesLocations.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(mVillagesLocations.getId().intValue()))
            .andExpect(jsonPath("$.uid").value(DEFAULT_UID))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.mappingStatus").value(DEFAULT_MAPPING_STATUS))
            .andExpect(jsonPath("$.districtCode").value(DEFAULT_DISTRICT_CODE))
            .andExpect(jsonPath("$.villageUid").value(DEFAULT_VILLAGE_UID))
            .andExpect(jsonPath("$.subdistrictName").value(DEFAULT_SUBDISTRICT_NAME))
            .andExpect(jsonPath("$.villageName").value(DEFAULT_VILLAGE_NAME))
            .andExpect(jsonPath("$.subvillageName").value(DEFAULT_SUBVILLAGE_NAME))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.urbanRuralId").value(DEFAULT_URBAN_RURAL_ID))
            .andExpect(jsonPath("$.urbanRural").value(DEFAULT_URBAN_RURAL))
            .andExpect(jsonPath("$.settlement").value(DEFAULT_SETTLEMENT))
            .andExpect(jsonPath("$.pop2004").value(DEFAULT_POP_2004.doubleValue()))
            .andExpect(jsonPath("$.pop2022").value(DEFAULT_POP_2022.doubleValue()))
            .andExpect(jsonPath("$.longitude").value(DEFAULT_LONGITUDE.doubleValue()))
            .andExpect(jsonPath("$.latitude").value(DEFAULT_LATITUDE.doubleValue()))
            .andExpect(jsonPath("$.ppcCodeGis").value(DEFAULT_PPC_CODE_GIS))
            .andExpect(jsonPath("$.level").value(DEFAULT_LEVEL.toString()));
    }

    @Test
    @Transactional
    void getNonExistingMVillagesLocations() throws Exception {
        // Get the mVillagesLocations
        restMVillagesLocationsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingMVillagesLocations() throws Exception {
        // Initialize the database
        mVillagesLocationsRepository.saveAndFlush(mVillagesLocations);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the mVillagesLocations
        MVillagesLocations updatedMVillagesLocations = mVillagesLocationsRepository.findById(mVillagesLocations.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedMVillagesLocations are not directly saved in db
        em.detach(updatedMVillagesLocations);
        updatedMVillagesLocations
            .uid(UPDATED_UID)
            .code(UPDATED_CODE)
            .mappingStatus(UPDATED_MAPPING_STATUS)
            .districtCode(UPDATED_DISTRICT_CODE)
            .villageUid(UPDATED_VILLAGE_UID)
            .subdistrictName(UPDATED_SUBDISTRICT_NAME)
            .villageName(UPDATED_VILLAGE_NAME)
            .subvillageName(UPDATED_SUBVILLAGE_NAME)
            .name(UPDATED_NAME)
            .urbanRuralId(UPDATED_URBAN_RURAL_ID)
            .urbanRural(UPDATED_URBAN_RURAL)
            .settlement(UPDATED_SETTLEMENT)
            .pop2004(UPDATED_POP_2004)
            .pop2022(UPDATED_POP_2022)
            .longitude(UPDATED_LONGITUDE)
            .latitude(UPDATED_LATITUDE)
            .ppcCodeGis(UPDATED_PPC_CODE_GIS)
            .level(UPDATED_LEVEL);

        restMVillagesLocationsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedMVillagesLocations.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedMVillagesLocations))
            )
            .andExpect(status().isOk());

        // Validate the MVillagesLocations in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedMVillagesLocationsToMatchAllProperties(updatedMVillagesLocations);
    }

    @Test
    @Transactional
    void putNonExistingMVillagesLocations() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        mVillagesLocations.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMVillagesLocationsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, mVillagesLocations.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(mVillagesLocations))
            )
            .andExpect(status().isBadRequest());

        // Validate the MVillagesLocations in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchMVillagesLocations() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        mVillagesLocations.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMVillagesLocationsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(mVillagesLocations))
            )
            .andExpect(status().isBadRequest());

        // Validate the MVillagesLocations in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamMVillagesLocations() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        mVillagesLocations.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMVillagesLocationsMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(mVillagesLocations)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the MVillagesLocations in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateMVillagesLocationsWithPatch() throws Exception {
        // Initialize the database
        mVillagesLocationsRepository.saveAndFlush(mVillagesLocations);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the mVillagesLocations using partial update
        MVillagesLocations partialUpdatedMVillagesLocations = new MVillagesLocations();
        partialUpdatedMVillagesLocations.setId(mVillagesLocations.getId());

        partialUpdatedMVillagesLocations
            .uid(UPDATED_UID)
            .code(UPDATED_CODE)
            .districtCode(UPDATED_DISTRICT_CODE)
            .villageUid(UPDATED_VILLAGE_UID)
            .subdistrictName(UPDATED_SUBDISTRICT_NAME)
            .subvillageName(UPDATED_SUBVILLAGE_NAME)
            .longitude(UPDATED_LONGITUDE)
            .latitude(UPDATED_LATITUDE);

        restMVillagesLocationsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMVillagesLocations.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedMVillagesLocations))
            )
            .andExpect(status().isOk());

        // Validate the MVillagesLocations in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertMVillagesLocationsUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedMVillagesLocations, mVillagesLocations),
            getPersistedMVillagesLocations(mVillagesLocations)
        );
    }

    @Test
    @Transactional
    void fullUpdateMVillagesLocationsWithPatch() throws Exception {
        // Initialize the database
        mVillagesLocationsRepository.saveAndFlush(mVillagesLocations);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the mVillagesLocations using partial update
        MVillagesLocations partialUpdatedMVillagesLocations = new MVillagesLocations();
        partialUpdatedMVillagesLocations.setId(mVillagesLocations.getId());

        partialUpdatedMVillagesLocations
            .uid(UPDATED_UID)
            .code(UPDATED_CODE)
            .mappingStatus(UPDATED_MAPPING_STATUS)
            .districtCode(UPDATED_DISTRICT_CODE)
            .villageUid(UPDATED_VILLAGE_UID)
            .subdistrictName(UPDATED_SUBDISTRICT_NAME)
            .villageName(UPDATED_VILLAGE_NAME)
            .subvillageName(UPDATED_SUBVILLAGE_NAME)
            .name(UPDATED_NAME)
            .urbanRuralId(UPDATED_URBAN_RURAL_ID)
            .urbanRural(UPDATED_URBAN_RURAL)
            .settlement(UPDATED_SETTLEMENT)
            .pop2004(UPDATED_POP_2004)
            .pop2022(UPDATED_POP_2022)
            .longitude(UPDATED_LONGITUDE)
            .latitude(UPDATED_LATITUDE)
            .ppcCodeGis(UPDATED_PPC_CODE_GIS)
            .level(UPDATED_LEVEL);

        restMVillagesLocationsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMVillagesLocations.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedMVillagesLocations))
            )
            .andExpect(status().isOk());

        // Validate the MVillagesLocations in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertMVillagesLocationsUpdatableFieldsEquals(
            partialUpdatedMVillagesLocations,
            getPersistedMVillagesLocations(partialUpdatedMVillagesLocations)
        );
    }

    @Test
    @Transactional
    void patchNonExistingMVillagesLocations() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        mVillagesLocations.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMVillagesLocationsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, mVillagesLocations.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(mVillagesLocations))
            )
            .andExpect(status().isBadRequest());

        // Validate the MVillagesLocations in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchMVillagesLocations() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        mVillagesLocations.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMVillagesLocationsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(mVillagesLocations))
            )
            .andExpect(status().isBadRequest());

        // Validate the MVillagesLocations in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamMVillagesLocations() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        mVillagesLocations.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMVillagesLocationsMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(mVillagesLocations)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the MVillagesLocations in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteMVillagesLocations() throws Exception {
        // Initialize the database
        mVillagesLocationsRepository.saveAndFlush(mVillagesLocations);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the mVillagesLocations
        restMVillagesLocationsMockMvc
            .perform(delete(ENTITY_API_URL_ID, mVillagesLocations.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return mVillagesLocationsRepository.count();
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

    protected MVillagesLocations getPersistedMVillagesLocations(MVillagesLocations mVillagesLocations) {
        return mVillagesLocationsRepository.findById(mVillagesLocations.getId()).orElseThrow();
    }

    protected void assertPersistedMVillagesLocationsToMatchAllProperties(MVillagesLocations expectedMVillagesLocations) {
        assertMVillagesLocationsAllPropertiesEquals(expectedMVillagesLocations, getPersistedMVillagesLocations(expectedMVillagesLocations));
    }

    protected void assertPersistedMVillagesLocationsToMatchUpdatableProperties(MVillagesLocations expectedMVillagesLocations) {
        assertMVillagesLocationsAllUpdatablePropertiesEquals(
            expectedMVillagesLocations,
            getPersistedMVillagesLocations(expectedMVillagesLocations)
        );
    }
}
