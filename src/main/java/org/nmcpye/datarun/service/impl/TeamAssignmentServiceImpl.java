package org.nmcpye.datarun.service.impl;

import java.util.Optional;
import org.nmcpye.datarun.domain.TeamAssignment;
import org.nmcpye.datarun.repository.TeamAssignmentRepository;
import org.nmcpye.datarun.service.TeamAssignmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link org.nmcpye.datarun.domain.TeamAssignment}.
 */
@Service
@Transactional
public class TeamAssignmentServiceImpl implements TeamAssignmentService {

    private final Logger log = LoggerFactory.getLogger(TeamAssignmentServiceImpl.class);

    private final TeamAssignmentRepository teamAssignmentRepository;

    public TeamAssignmentServiceImpl(TeamAssignmentRepository teamAssignmentRepository) {
        this.teamAssignmentRepository = teamAssignmentRepository;
    }

    @Override
    public TeamAssignment save(TeamAssignment teamAssignment) {
        log.debug("Request to save TeamAssignment : {}", teamAssignment);
        return teamAssignmentRepository.save(teamAssignment);
    }

    @Override
    public TeamAssignment update(TeamAssignment teamAssignment) {
        log.debug("Request to update TeamAssignment : {}", teamAssignment);
        teamAssignment.setIsPersisted();
        return teamAssignmentRepository.save(teamAssignment);
    }

    @Override
    public Optional<TeamAssignment> partialUpdate(TeamAssignment teamAssignment) {
        log.debug("Request to partially update TeamAssignment : {}", teamAssignment);

        return teamAssignmentRepository
            .findById(teamAssignment.getId())
            .map(existingTeamAssignment -> {
                if (teamAssignment.getPhaseNo() != null) {
                    existingTeamAssignment.setPhaseNo(teamAssignment.getPhaseNo());
                }
                if (teamAssignment.getFieldCode() != null) {
                    existingTeamAssignment.setFieldCode(teamAssignment.getFieldCode());
                }
                if (teamAssignment.getDistrictCode() != null) {
                    existingTeamAssignment.setDistrictCode(teamAssignment.getDistrictCode());
                }
                if (teamAssignment.getGov() != null) {
                    existingTeamAssignment.setGov(teamAssignment.getGov());
                }
                if (teamAssignment.getDistrict() != null) {
                    existingTeamAssignment.setDistrict(teamAssignment.getDistrict());
                }
                if (teamAssignment.getSubdistrict() != null) {
                    existingTeamAssignment.setSubdistrict(teamAssignment.getSubdistrict());
                }
                if (teamAssignment.getVillage() != null) {
                    existingTeamAssignment.setVillage(teamAssignment.getVillage());
                }
                if (teamAssignment.getSubvillage() != null) {
                    existingTeamAssignment.setSubvillage(teamAssignment.getSubvillage());
                }
                if (teamAssignment.getPpdName() != null) {
                    existingTeamAssignment.setPpdName(teamAssignment.getPpdName());
                }
                if (teamAssignment.getDayId() != null) {
                    existingTeamAssignment.setDayId(teamAssignment.getDayId());
                }
                if (teamAssignment.getPopulation() != null) {
                    existingTeamAssignment.setPopulation(teamAssignment.getPopulation());
                }
                if (teamAssignment.getItnsPlanned() != null) {
                    existingTeamAssignment.setItnsPlanned(teamAssignment.getItnsPlanned());
                }
                if (teamAssignment.getTargetType() != null) {
                    existingTeamAssignment.setTargetType(teamAssignment.getTargetType());
                }
                if (teamAssignment.getLongitude() != null) {
                    existingTeamAssignment.setLongitude(teamAssignment.getLongitude());
                }
                if (teamAssignment.getLatitude() != null) {
                    existingTeamAssignment.setLatitude(teamAssignment.getLatitude());
                }
                if (teamAssignment.getStartDayDate() != null) {
                    existingTeamAssignment.setStartDayDate(teamAssignment.getStartDayDate());
                }
                if (teamAssignment.getCreatedBy() != null) {
                    existingTeamAssignment.setCreatedBy(teamAssignment.getCreatedBy());
                }
                if (teamAssignment.getCreatedDate() != null) {
                    existingTeamAssignment.setCreatedDate(teamAssignment.getCreatedDate());
                }
                if (teamAssignment.getLastModifiedBy() != null) {
                    existingTeamAssignment.setLastModifiedBy(teamAssignment.getLastModifiedBy());
                }
                if (teamAssignment.getLastModifiedDate() != null) {
                    existingTeamAssignment.setLastModifiedDate(teamAssignment.getLastModifiedDate());
                }

                return existingTeamAssignment;
            })
            .map(teamAssignmentRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TeamAssignment> findAll(Pageable pageable) {
        log.debug("Request to get all TeamAssignments");
        return teamAssignmentRepository.findAll(pageable);
    }

    public Page<TeamAssignment> findAllWithEagerRelationships(Pageable pageable) {
        return teamAssignmentRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TeamAssignment> findOne(Long id) {
        log.debug("Request to get TeamAssignment : {}", id);
        return teamAssignmentRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TeamAssignment : {}", id);
        teamAssignmentRepository.deleteById(id);
    }
}
