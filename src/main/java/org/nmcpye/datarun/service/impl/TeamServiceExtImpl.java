package org.nmcpye.datarun.service.impl;

import org.nmcpye.datarun.domain.Team;
import org.nmcpye.datarun.repository.TeamRepository;
import org.nmcpye.datarun.repository.TeamRepositoryExt;
import org.nmcpye.datarun.security.SecurityUtils;
import org.nmcpye.datarun.service.TeamServiceExt;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Primary
@Transactional
public class TeamServiceExtImpl extends TeamServiceImpl implements TeamServiceExt {
    TeamRepositoryExt teamRepositoryExt;

    public TeamServiceExtImpl(TeamRepository teamRepository, TeamRepositoryExt teamRepositoryExt) {
        super(teamRepository);
        this.teamRepositoryExt = teamRepositoryExt;
    }

    @Override
    public Page<Team> findByCurrentUser(Pageable pageable) {
        String userLogin = SecurityUtils.getCurrentUserLogin()
            .orElseThrow(() -> new IllegalStateException("User not logged in"));
        return teamRepositoryExt.findByCurrentUser(userLogin, pageable);
    }
}
