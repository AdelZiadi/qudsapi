package org.nmcpye.datarun.service;

import org.nmcpye.datarun.domain.Assignment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AssignmentServiceCustom extends AssignmentService {
    Page<Assignment> findByCurrentUser(Pageable pageable);
}
