package org.nmcpye.datarun.service.impl;

import org.nmcpye.datarun.domain.Assignment;
import org.nmcpye.datarun.repository.AssignmentRepositoryCustom;
import org.nmcpye.datarun.security.SecurityUtils;
import org.nmcpye.datarun.service.AssignmentServiceCustom;
import org.nmcpye.datarun.utils.CodeGenerator;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Primary
@Transactional
public class AssignmentServiceImplCustom extends AssignmentServiceImpl implements AssignmentServiceCustom {
    AssignmentRepositoryCustom assignmentRepositoryCustom;

    public AssignmentServiceImplCustom(AssignmentRepositoryCustom assignmentRepositoryCustom) {
        super(assignmentRepositoryCustom);
        this.assignmentRepositoryCustom = assignmentRepositoryCustom;
    }

    @Override
    public Page<Assignment> findByCurrentUser(Pageable pageable) {
        final String userLogin = SecurityUtils.getCurrentUserLogin()
            .orElseThrow(() -> new IllegalStateException("User not logged in"));
        return assignmentRepositoryCustom.findByCurrentUser(userLogin, pageable);
    }

    @Override
    public Assignment save(Assignment assignment) {
        if (assignment.getUid() == null || assignment.getUid().isEmpty()) {
            assignment.setUid(CodeGenerator.generateUid());
        }
        return super.save(assignment);
    }
}
