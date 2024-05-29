package org.nmcpye.datarun.service.impl;

import org.nmcpye.datarun.domain.ItnsVillage;
import org.nmcpye.datarun.repository.ItnsVillageRepository;
import org.nmcpye.datarun.service.ItnsVillageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link org.nmcpye.datarun.domain.ItnsVillage}.
 */
@Service
@Transactional
public class ItnsVillageServiceImpl implements ItnsVillageService {

    private final Logger log = LoggerFactory.getLogger(ItnsVillageServiceImpl.class);

    private final ItnsVillageRepository itnsVillageRepository;

    public ItnsVillageServiceImpl(ItnsVillageRepository itnsVillageRepository) {
        this.itnsVillageRepository = itnsVillageRepository;
    }

    @Override
    public ItnsVillage save(ItnsVillage itnsVillage) {
        log.debug("Request to save ItnsVillage : {}", itnsVillage);
        return itnsVillageRepository.save(itnsVillage);
    }

    @Override
    public ItnsVillage update(ItnsVillage itnsVillage) {
        log.debug("Request to update ItnsVillage : {}", itnsVillage);
        itnsVillage.setIsPersisted();
        return itnsVillageRepository.save(itnsVillage);
    }

    @Override
    public Optional<ItnsVillage> partialUpdate(ItnsVillage itnsVillage) {
        log.debug("Request to partially update ItnsVillage : {}", itnsVillage);

        return itnsVillageRepository
            .findById(itnsVillage.getId())
            .map(existingItnsVillage -> {
                if (itnsVillage.getUid() != null) {
                    existingItnsVillage.setUid(itnsVillage.getUid());
                }
                if (itnsVillage.getCode() != null) {
                    existingItnsVillage.setCode(itnsVillage.getCode());
                }
                if (itnsVillage.getName() != null) {
                    existingItnsVillage.setName(itnsVillage.getName());
                }
                if (itnsVillage.getSubmissionUuid() != null) {
                    existingItnsVillage.setSubmissionUuid(itnsVillage.getSubmissionUuid());
                }
                if (itnsVillage.getSubmissionId() != null) {
                    existingItnsVillage.setSubmissionId(itnsVillage.getSubmissionId());
                }
                if (itnsVillage.getWorkDayDate() != null) {
                    existingItnsVillage.setWorkDayDate(itnsVillage.getWorkDayDate());
                }
                if (itnsVillage.getSurveytype() != null) {
                    existingItnsVillage.setSurveytype(itnsVillage.getSurveytype());
                }
                if (itnsVillage.getOtherReasonComment() != null) {
                    existingItnsVillage.setOtherReasonComment(itnsVillage.getOtherReasonComment());
                }
                if (itnsVillage.getReasonNotcomplete() != null) {
                    existingItnsVillage.setReasonNotcomplete(itnsVillage.getReasonNotcomplete());
                }
                if (itnsVillage.getSettlement() != null) {
                    existingItnsVillage.setSettlement(itnsVillage.getSettlement());
                }
                if (itnsVillage.getSettlementName() != null) {
                    existingItnsVillage.setSettlementName(itnsVillage.getSettlementName());
                }
                if (itnsVillage.getTlCommenet() != null) {
                    existingItnsVillage.setTlCommenet(itnsVillage.getTlCommenet());
                }
                if (itnsVillage.getTimeSpentHours() != null) {
                    existingItnsVillage.setTimeSpentHours(itnsVillage.getTimeSpentHours());
                }
                if (itnsVillage.getTimeSpentMinutes() != null) {
                    existingItnsVillage.setTimeSpentMinutes(itnsVillage.getTimeSpentMinutes());
                }
                if (itnsVillage.getDifficulties() != null) {
                    existingItnsVillage.setDifficulties(itnsVillage.getDifficulties());
                }
                if (itnsVillage.getLocationCaptured() != null) {
                    existingItnsVillage.setLocationCaptured(itnsVillage.getLocationCaptured());
                }
                if (itnsVillage.getLocationCaptureTime() != null) {
                    existingItnsVillage.setLocationCaptureTime(itnsVillage.getLocationCaptureTime());
                }
                if (itnsVillage.getHoProof() != null) {
                    existingItnsVillage.setHoProof(itnsVillage.getHoProof());
                }
                if (itnsVillage.getStartEntryTime() != null) {
                    existingItnsVillage.setStartEntryTime(itnsVillage.getStartEntryTime());
                }
                if (itnsVillage.getEndEntryTime() != null) {
                    existingItnsVillage.setEndEntryTime(itnsVillage.getEndEntryTime());
                }
                if (itnsVillage.getFinishedEntryTime() != null) {
                    existingItnsVillage.setFinishedEntryTime(itnsVillage.getFinishedEntryTime());
                }
                if (itnsVillage.getHoProofUrl() != null) {
                    existingItnsVillage.setHoProofUrl(itnsVillage.getHoProofUrl());
                }
                if (itnsVillage.getSubmissionTime() != null) {
                    existingItnsVillage.setSubmissionTime(itnsVillage.getSubmissionTime());
                }
                if (itnsVillage.getUntargetingOtherSpecify() != null) {
                    existingItnsVillage.setUntargetingOtherSpecify(itnsVillage.getUntargetingOtherSpecify());
                }
                if (itnsVillage.getOtherVillageName() != null) {
                    existingItnsVillage.setOtherVillageName(itnsVillage.getOtherVillageName());
                }
                if (itnsVillage.getOtherVillageCode() != null) {
                    existingItnsVillage.setOtherVillageCode(itnsVillage.getOtherVillageCode());
                }
                if (itnsVillage.getOtherTeamNo() != null) {
                    existingItnsVillage.setOtherTeamNo(itnsVillage.getOtherTeamNo());
                }
                if (itnsVillage.getDeleted() != null) {
                    existingItnsVillage.setDeleted(itnsVillage.getDeleted());
                }
                if (itnsVillage.getCreatedBy() != null) {
                    existingItnsVillage.setCreatedBy(itnsVillage.getCreatedBy());
                }
                if (itnsVillage.getCreatedDate() != null) {
                    existingItnsVillage.setCreatedDate(itnsVillage.getCreatedDate());
                }
                if (itnsVillage.getLastModifiedBy() != null) {
                    existingItnsVillage.setLastModifiedBy(itnsVillage.getLastModifiedBy());
                }
                if (itnsVillage.getLastModifiedDate() != null) {
                    existingItnsVillage.setLastModifiedDate(itnsVillage.getLastModifiedDate());
                }

                return existingItnsVillage;
            })
            .map(itnsVillageRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ItnsVillage> findAll(Pageable pageable) {
        log.debug("Request to get all ItnsVillages");
        return itnsVillageRepository.findAll(pageable);
    }

    public Page<ItnsVillage> findAllWithEagerRelationships(Pageable pageable) {
        return itnsVillageRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ItnsVillage> findOne(Long id) {
        log.debug("Request to get ItnsVillage : {}", id);
        return itnsVillageRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ItnsVillage : {}", id);
        itnsVillageRepository.deleteById(id);
    }
}
