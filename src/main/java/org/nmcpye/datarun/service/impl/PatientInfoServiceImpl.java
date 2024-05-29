package org.nmcpye.datarun.service.impl;

import java.util.Optional;
import org.nmcpye.datarun.domain.PatientInfo;
import org.nmcpye.datarun.repository.PatientInfoRepository;
import org.nmcpye.datarun.service.PatientInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link org.nmcpye.datarun.domain.PatientInfo}.
 */
@Service
@Transactional
public class PatientInfoServiceImpl implements PatientInfoService {

    private final Logger log = LoggerFactory.getLogger(PatientInfoServiceImpl.class);

    private final PatientInfoRepository patientInfoRepository;

    public PatientInfoServiceImpl(PatientInfoRepository patientInfoRepository) {
        this.patientInfoRepository = patientInfoRepository;
    }

    @Override
    public PatientInfo save(PatientInfo patientInfo) {
        log.debug("Request to save PatientInfo : {}", patientInfo);
        return patientInfoRepository.save(patientInfo);
    }

    @Override
    public PatientInfo update(PatientInfo patientInfo) {
        log.debug("Request to update PatientInfo : {}", patientInfo);
        patientInfo.setIsPersisted();
        return patientInfoRepository.save(patientInfo);
    }

    @Override
    public Optional<PatientInfo> partialUpdate(PatientInfo patientInfo) {
        log.debug("Request to partially update PatientInfo : {}", patientInfo);

        return patientInfoRepository
            .findById(patientInfo.getId())
            .map(existingPatientInfo -> {
                if (patientInfo.getUid() != null) {
                    existingPatientInfo.setUid(patientInfo.getUid());
                }
                if (patientInfo.getCode() != null) {
                    existingPatientInfo.setCode(patientInfo.getCode());
                }
                if (patientInfo.getName() != null) {
                    existingPatientInfo.setName(patientInfo.getName());
                }
                if (patientInfo.getAge() != null) {
                    existingPatientInfo.setAge(patientInfo.getAge());
                }
                if (patientInfo.getGender() != null) {
                    existingPatientInfo.setGender(patientInfo.getGender());
                }
                if (patientInfo.getCreatedBy() != null) {
                    existingPatientInfo.setCreatedBy(patientInfo.getCreatedBy());
                }
                if (patientInfo.getCreatedDate() != null) {
                    existingPatientInfo.setCreatedDate(patientInfo.getCreatedDate());
                }
                if (patientInfo.getLastModifiedBy() != null) {
                    existingPatientInfo.setLastModifiedBy(patientInfo.getLastModifiedBy());
                }
                if (patientInfo.getLastModifiedDate() != null) {
                    existingPatientInfo.setLastModifiedDate(patientInfo.getLastModifiedDate());
                }

                return existingPatientInfo;
            })
            .map(patientInfoRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PatientInfo> findAll(Pageable pageable) {
        log.debug("Request to get all PatientInfos");
        return patientInfoRepository.findAll(pageable);
    }

    public Page<PatientInfo> findAllWithEagerRelationships(Pageable pageable) {
        return patientInfoRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PatientInfo> findOne(Long id) {
        log.debug("Request to get PatientInfo : {}", id);
        return patientInfoRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete PatientInfo : {}", id);
        patientInfoRepository.deleteById(id);
    }
}
