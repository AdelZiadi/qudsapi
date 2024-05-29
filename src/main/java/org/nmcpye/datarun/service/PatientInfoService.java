package org.nmcpye.datarun.service;

import java.util.Optional;
import org.nmcpye.datarun.domain.PatientInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link org.nmcpye.datarun.domain.PatientInfo}.
 */
public interface PatientInfoService {
    /**
     * Save a patientInfo.
     *
     * @param patientInfo the entity to save.
     * @return the persisted entity.
     */
    PatientInfo save(PatientInfo patientInfo);

    /**
     * Updates a patientInfo.
     *
     * @param patientInfo the entity to update.
     * @return the persisted entity.
     */
    PatientInfo update(PatientInfo patientInfo);

    /**
     * Partially updates a patientInfo.
     *
     * @param patientInfo the entity to update partially.
     * @return the persisted entity.
     */
    Optional<PatientInfo> partialUpdate(PatientInfo patientInfo);

    /**
     * Get all the patientInfos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PatientInfo> findAll(Pageable pageable);

    /**
     * Get all the patientInfos with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PatientInfo> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" patientInfo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PatientInfo> findOne(Long id);

    /**
     * Delete the "id" patientInfo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
