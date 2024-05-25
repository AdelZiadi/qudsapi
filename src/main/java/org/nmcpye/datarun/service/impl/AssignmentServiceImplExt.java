package org.nmcpye.datarun.service.impl;

import org.nmcpye.datarun.domain.Assignment;
import org.nmcpye.datarun.repository.AssignmentRepositoryExt;
import org.nmcpye.datarun.security.SecurityUtils;
import org.nmcpye.datarun.service.AssignmentServiceExt;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Primary
@Transactional
public class AssignmentServiceImplExt extends AssignmentServiceImpl implements AssignmentServiceExt {
    AssignmentRepositoryExt assignmentRepositoryExt;

    public AssignmentServiceImplExt(AssignmentRepositoryExt assignmentRepositoryExt) {
        super(assignmentRepositoryExt);
        this.assignmentRepositoryExt = assignmentRepositoryExt;
    }

    @Override
    public Page<Assignment> findByCurrentUser(Pageable pageable) {
        final String userLogin = SecurityUtils.getCurrentUserLogin()
            .orElseThrow(() -> new IllegalStateException("User not logged in"));
        return assignmentRepositoryExt.findByCurrentUser(userLogin, pageable);
    }
}
