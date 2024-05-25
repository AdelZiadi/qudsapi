package org.nmcpye.datarun.service;

import org.nmcpye.datarun.domain.Assignment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AssignmentServiceExt extends AssignmentService {
    Page<Assignment> findByCurrentUser(Pageable pageable);
}
