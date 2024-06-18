package org.nmcpye.datarun.service.impl;

import java.util.List;
import java.util.Optional;
import org.nmcpye.datarun.domain.TeamFormAccess;
import org.nmcpye.datarun.repository.TeamFormAccessRepository;
import org.nmcpye.datarun.service.TeamFormAccessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link org.nmcpye.datarun.domain.TeamFormAccess}.
 */
@Service
@Transactional
public class TeamFormAccessServiceImpl implements TeamFormAccessService {

    private final Logger log = LoggerFactory.getLogger(TeamFormAccessServiceImpl.class);

    private final TeamFormAccessRepository teamFormAccessRepository;

    public TeamFormAccessServiceImpl(TeamFormAccessRepository teamFormAccessRepository) {
        this.teamFormAccessRepository = teamFormAccessRepository;
    }

    @Override
    public TeamFormAccess save(TeamFormAccess teamFormAccess) {
        log.debug("Request to save TeamFormAccess : {}", teamFormAccess);
        return teamFormAccessRepository.save(teamFormAccess);
    }

    @Override
    public TeamFormAccess update(TeamFormAccess teamFormAccess) {
        log.debug("Request to update TeamFormAccess : {}", teamFormAccess);
        return teamFormAccessRepository.save(teamFormAccess);
    }

    @Override
    public Optional<TeamFormAccess> partialUpdate(TeamFormAccess teamFormAccess) {
        log.debug("Request to partially update TeamFormAccess : {}", teamFormAccess);

        return teamFormAccessRepository
            .findById(teamFormAccess.getId())
            .map(existingTeamFormAccess -> {
                if (teamFormAccess.getUid() != null) {
                    existingTeamFormAccess.setUid(teamFormAccess.getUid());
                }
                if (teamFormAccess.getCode() != null) {
                    existingTeamFormAccess.setCode(teamFormAccess.getCode());
                }
                if (teamFormAccess.getName() != null) {
                    existingTeamFormAccess.setName(teamFormAccess.getName());
                }
                if (teamFormAccess.getSessionDate() != null) {
                    existingTeamFormAccess.setSessionDate(teamFormAccess.getSessionDate());
                }
                if (teamFormAccess.getSubject() != null) {
                    existingTeamFormAccess.setSubject(teamFormAccess.getSubject());
                }
                if (teamFormAccess.getSessions() != null) {
                    existingTeamFormAccess.setSessions(teamFormAccess.getSessions());
                }
                if (teamFormAccess.getPeople() != null) {
                    existingTeamFormAccess.setPeople(teamFormAccess.getPeople());
                }
                if (teamFormAccess.getComment() != null) {
                    existingTeamFormAccess.setComment(teamFormAccess.getComment());
                }
                if (teamFormAccess.getDeleted() != null) {
                    existingTeamFormAccess.setDeleted(teamFormAccess.getDeleted());
                }
                if (teamFormAccess.getStartEntryTime() != null) {
                    existingTeamFormAccess.setStartEntryTime(teamFormAccess.getStartEntryTime());
                }
                if (teamFormAccess.getFinishedEntryTime() != null) {
                    existingTeamFormAccess.setFinishedEntryTime(teamFormAccess.getFinishedEntryTime());
                }
                if (teamFormAccess.getStatus() != null) {
                    existingTeamFormAccess.setStatus(teamFormAccess.getStatus());
                }

                return existingTeamFormAccess;
            })
            .map(teamFormAccessRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TeamFormAccess> findAll() {
        log.debug("Request to get all TeamFormAccesses");
        return teamFormAccessRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TeamFormAccess> findOne(Long id) {
        log.debug("Request to get TeamFormAccess : {}", id);
        return teamFormAccessRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TeamFormAccess : {}", id);
        teamFormAccessRepository.deleteById(id);
    }
}
