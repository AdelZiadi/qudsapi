package org.nmcpye.datarun.drun.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.nmcpye.datarun.domain.Activity;
import org.nmcpye.datarun.domain.Assignment;
import org.nmcpye.datarun.domain.ChvRegister;
import org.nmcpye.datarun.domain.Team;
import org.nmcpye.datarun.drun.repository.ActivityRepositoryCustom;
import org.nmcpye.datarun.drun.repository.AssignmentRepositoryCustom;
import org.nmcpye.datarun.drun.repository.ChvRegisterRepositoryCustom;
import org.nmcpye.datarun.drun.repository.TeamRepositoryCustom;
import org.nmcpye.datarun.drun.service.ChvRegisterServiceCustom;
import org.nmcpye.datarun.drun.service.IdentifiableServiceImpl;
import org.nmcpye.datarun.service.dto.drun.SaveSummaryDTO;
import org.nmcpye.datarun.service.impl.ChvRegisterServiceImpl;
import org.nmcpye.datarun.utils.CodeGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Primary
@Transactional
public class ChvRegisterServiceCustomImpl
    extends IdentifiableServiceImpl<ChvRegister>
    implements ChvRegisterServiceCustom {

    private final Logger log = LoggerFactory.getLogger(ChvRegisterServiceImpl.class);

    private final ChvRegisterRepositoryCustom repository;
    private final ActivityRepositoryCustom activityRepository;
    private final AssignmentRepositoryCustom locationRepository;
    private final TeamRepositoryCustom teamRepository;

    public ChvRegisterServiceCustomImpl(ChvRegisterRepositoryCustom repository, ActivityRepositoryCustom activityRepository, AssignmentRepositoryCustom locationRepository, TeamRepositoryCustom teamRepository) {
        super(repository);
        this.repository = repository;
        this.activityRepository = activityRepository;
        this.locationRepository = locationRepository;
        this.teamRepository = teamRepository;
    }

    public SaveSummaryDTO saveWithReferences(ChvRegister chvRegister) {
        SaveSummaryDTO summaryDTO = new SaveSummaryDTO();


        try {
            Optional<Activity> activityOpt = activityRepository.findByUid(chvRegister.getActivity().getUid());
            Optional<Assignment> locationOpt = locationRepository.findByUid(chvRegister.getLocation().getUid());
            Optional<Team> teamOpt = teamRepository.findByUid(chvRegister.getTeam().getUid());

            if (activityOpt.isPresent() && locationOpt.isPresent() && teamOpt.isPresent()) {
                chvRegister.setActivity(activityOpt.get());
                chvRegister.setLocation(locationOpt.get());
                chvRegister.setTeam(teamOpt.get());

                if (chvRegister.getUid() != null || !chvRegister.getUid().isEmpty()) {
                    repository.findByUid(chvRegister.getUid()).ifPresent(entity -> {
                        chvRegister.setId(entity.getId());
                        chvRegister.setIsPersisted();
                    });
                } else {
                    chvRegister.setUid(CodeGenerator.generateUid());
                }
                repository.save(chvRegister);
                summaryDTO.setSuccessfulUids(List.of(chvRegister.getUid()));
                summaryDTO.setFailedUids(new HashMap<>());
            } else {
                summaryDTO.setSuccessfulUids(List.of());
                Map<String, String> failedUids = new HashMap<>();
                if (!activityOpt.isPresent()) {
                    failedUids.put(chvRegister.getActivity().getUid(), "Activity not found");
                }
                if (!locationOpt.isPresent()) {
                    failedUids.put(chvRegister.getLocation().getUid(), "Location not found");
                }
                if (!teamOpt.isPresent()) {
                    failedUids.put(chvRegister.getTeam().getUid(), "Team not found");
                }
                summaryDTO.setFailedUids(failedUids);
            }
        } catch (Exception e) {
            summaryDTO.setSuccessfulUids(List.of());
            Map<String, String> failedUids = new HashMap<>();
            failedUids.put(chvRegister.getUid(), e.getMessage());
            summaryDTO.setFailedUids(failedUids);
        }

        return summaryDTO;
    }

    @Override
    public ChvRegister saveWithRelations(ChvRegister chvRegister) {

        Activity activityOpt = activityRepository.findByUid(chvRegister.getActivity().getUid()).orElseThrow(() ->
            new EntityNotFoundException("Activity not found: " + chvRegister.getActivity().getUid()));
//        Assignment locationOpt = locationRepository.findByUid(chvRegister.getLocation().getUid()).orElseThrow(() ->
//            new EntityNotFoundException("Location not found: " + chvRegister.getLocation().getUid()));
        Team teamOpt = teamRepository.findByUid(chvRegister.getTeam().getUid()).orElseThrow(() ->
            new EntityNotFoundException("Team not found: " + chvRegister.getTeam().getUid()));

        chvRegister.setActivity(activityOpt);
//        chvRegister.setLocation(locationOpt);
        chvRegister.setTeam(teamOpt);

        if (chvRegister.getUid() == null || chvRegister.getUid().isEmpty()) {
            chvRegister.setUid(CodeGenerator.generateUid());
        }

        return repository.save(chvRegister);
    }

    @Override
    public Optional<ChvRegister> partialUpdate(ChvRegister chvRegister) {
        log.debug("Request to partially update ChvRegister : {}", chvRegister);

        return repository
            .findById(chvRegister.getId())
            .map(existingChvRegister -> {
                if (chvRegister.getUid() != null) {
                    existingChvRegister.setUid(chvRegister.getUid());
                }
                if (chvRegister.getCode() != null) {
                    existingChvRegister.setCode(chvRegister.getCode());
                }
                if (chvRegister.getName() != null) {
                    existingChvRegister.setName(chvRegister.getName());
                }
                if (chvRegister.getLocationName() != null) {
                    existingChvRegister.setLocationName(chvRegister.getLocationName());
                }
                if (chvRegister.getAge() != null) {
                    existingChvRegister.setAge(chvRegister.getAge());
                }
                if (chvRegister.getGender() != null) {
                    existingChvRegister.setGender(chvRegister.getGender());
                }
                if (chvRegister.getVisitDate() != null) {
                    existingChvRegister.setVisitDate(chvRegister.getVisitDate());
                }
                if (chvRegister.getPregnant() != null) {
                    existingChvRegister.setPregnant(chvRegister.getPregnant());
                }
                if (chvRegister.getTestResult() != null) {
                    existingChvRegister.setTestResult(chvRegister.getTestResult());
                }
                if (chvRegister.getDetectionType() != null) {
                    existingChvRegister.setDetectionType(chvRegister.getDetectionType());
                }
                if (chvRegister.getSeverity() != null) {
                    existingChvRegister.setSeverity(chvRegister.getSeverity());
                }
                if (chvRegister.getTreatment() != null) {
                    existingChvRegister.setTreatment(chvRegister.getTreatment());
                }
                if (chvRegister.getDeleted() != null) {
                    existingChvRegister.setDeleted(chvRegister.getDeleted());
                }
                if (chvRegister.getStartEntryTime() != null) {
                    existingChvRegister.setStartEntryTime(chvRegister.getStartEntryTime());
                }
                if (chvRegister.getFinishedEntryTime() != null) {
                    existingChvRegister.setFinishedEntryTime(chvRegister.getFinishedEntryTime());
                }
                if (chvRegister.getComment() != null) {
                    existingChvRegister.setComment(chvRegister.getComment());
                }
                if (chvRegister.getStatus() != null) {
                    existingChvRegister.setStatus(chvRegister.getStatus());
                }
                if (chvRegister.getCreatedBy() != null) {
                    existingChvRegister.setCreatedBy(chvRegister.getCreatedBy());
                }
                if (chvRegister.getCreatedDate() != null) {
                    existingChvRegister.setCreatedDate(chvRegister.getCreatedDate());
                }
                if (chvRegister.getLastModifiedBy() != null) {
                    existingChvRegister.setLastModifiedBy(chvRegister.getLastModifiedBy());
                }
                if (chvRegister.getLastModifiedDate() != null) {
                    existingChvRegister.setLastModifiedDate(chvRegister.getLastModifiedDate());
                }

                return existingChvRegister;
            })
            .map(repository::save);
    }
}