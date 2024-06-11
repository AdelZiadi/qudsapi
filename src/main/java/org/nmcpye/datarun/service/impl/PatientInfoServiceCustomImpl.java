package org.nmcpye.datarun.service.impl;

import org.nmcpye.datarun.domain.PatientInfo;
import org.nmcpye.datarun.repository.PatientInfoRepositoryCustom;
import org.nmcpye.datarun.service.PatientInfoServiceCustom;
import org.nmcpye.datarun.utils.CodeGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Primary
@Transactional
public class PatientInfoServiceCustomImpl
    extends PatientInfoServiceImpl
    implements PatientInfoServiceCustom {

    private final Logger log = LoggerFactory.getLogger(PatientInfoServiceImpl.class);

    private final PatientInfoRepositoryCustom patientInfoRepository;

    public PatientInfoServiceCustomImpl(PatientInfoRepositoryCustom patientInfoRepository) {
        super(patientInfoRepository);
        this.patientInfoRepository = patientInfoRepository;
    }

    @Override
    public PatientInfo save(PatientInfo itnsVillage) {
        if (itnsVillage.getUid() == null || itnsVillage.getUid().isEmpty()) {
            itnsVillage.setUid(CodeGenerator.generateUid());
        }
        return patientInfoRepository.save(itnsVillage);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PatientInfo> findAll(Pageable pageable) {
        log.debug("Request to get all PatientInfos");
        return patientInfoRepository.findAllByUser(pageable);
    }

    @Override
    public Page<PatientInfo> findAllWithEagerRelationships(Pageable pageable) {
        return patientInfoRepository.findAllWithEagerRelationshipsByUser(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PatientInfo> findOne(Long id) {
        log.debug("Request to get PatientInfo : {}", id);
        return patientInfoRepository.findOneWithEagerRelationshipsByUser(id);
    }
}
