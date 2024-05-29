package org.nmcpye.datarun.service;

import org.nmcpye.datarun.domain.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link Project}.
 */
public interface ProjectServiceCustom extends ProjectService {
    Page<Project> findAll(Pageable pageable);
}
