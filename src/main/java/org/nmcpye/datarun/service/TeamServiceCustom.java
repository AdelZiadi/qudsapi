package org.nmcpye.datarun.service;

import org.nmcpye.datarun.domain.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/**
 * Service Interface for managing {@link Team}.
 */
public interface TeamServiceCustom extends TeamService {
    Page<Team> findByCurrentUser(Pageable pageable);
}
