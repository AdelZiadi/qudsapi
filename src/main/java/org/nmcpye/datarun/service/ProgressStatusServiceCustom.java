package org.nmcpye.datarun.service;

import org.nmcpye.datarun.domain.ProgressStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link ProgressStatus}.
 */
public interface ProgressStatusServiceCustom extends ProgressStatusService {
    /**
     * Get all the progressStatuses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProgressStatus> findAll(Pageable pageable);
}
