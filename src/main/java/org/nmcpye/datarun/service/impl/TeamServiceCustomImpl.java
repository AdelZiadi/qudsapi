package org.nmcpye.datarun.service.impl;

import org.nmcpye.datarun.domain.Team;
import org.nmcpye.datarun.repository.TeamRepository;
import org.nmcpye.datarun.repository.TeamRepositoryCustom;
import org.nmcpye.datarun.security.SecurityUtils;
import org.nmcpye.datarun.service.TeamServiceCustom;
import org.nmcpye.datarun.utils.CodeGenerator;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Primary
@Transactional
public class TeamServiceCustomImpl extends TeamServiceImpl implements TeamServiceCustom {
    TeamRepositoryCustom teamRepositoryCustom;

    public TeamServiceCustomImpl(TeamRepository teamRepository, TeamRepositoryCustom teamRepositoryCustom) {
        super(teamRepository);
        this.teamRepositoryCustom = teamRepositoryCustom;
    }

    @Override
    public Team save(Team team) {
        if (team.getUid() == null || team.getUid().isEmpty()) {
            team.setUid(CodeGenerator.generateUid());
        }
        return super.save(team);
    }

    @Override
    public Page<Team> findByCurrentUser(Pageable pageable) {
        String userLogin = SecurityUtils.getCurrentUserLogin()
            .orElseThrow(() -> new IllegalStateException("User not logged in"));
        return teamRepositoryCustom.findByCurrentUser(userLogin, pageable);
    }
}
