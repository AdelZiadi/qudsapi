package org.nmcpye.datarun.service;

import java.util.List;
import java.util.Optional;
import org.nmcpye.datarun.domain.ProgressStatus;

/**
 * Service Interface for managing {@link org.nmcpye.datarun.domain.ProgressStatus}.
 */
public interface ProgressStatusService {
    /**
     * Save a progressStatus.
     *
     * @param progressStatus the entity to save.
     * @return the persisted entity.
     */
    ProgressStatus save(ProgressStatus progressStatus);

    /**
     * Updates a progressStatus.
     *
     * @param progressStatus the entity to update.
     * @return the persisted entity.
     */
    ProgressStatus update(ProgressStatus progressStatus);

    /**
     * Partially updates a progressStatus.
     *
     * @param progressStatus the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ProgressStatus> partialUpdate(ProgressStatus progressStatus);

    /**
     * Get all the progressStatuses.
     *
     * @return the list of entities.
     */
    List<ProgressStatus> findAll();

    /**
     * Get the "id" progressStatus.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProgressStatus> findOne(Long id);

    /**
     * Delete the "id" progressStatus.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
