package org.nmcpye.datarun.drun.service.impl;

import org.nmcpye.datarun.domain.ChvRegister;
import org.nmcpye.datarun.drun.repository.ChvRegisterRepositoryCustom;
import org.nmcpye.datarun.drun.service.IdentifiableServiceImpl;
import org.nmcpye.datarun.drun.service.ChvRegisterServiceCustom;
import org.nmcpye.datarun.service.impl.ChvRegisterServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Primary
@Transactional
public class ChvRegisterServiceCustomImpl
    extends IdentifiableServiceImpl<ChvRegister>
    implements ChvRegisterServiceCustom {

    private final Logger log = LoggerFactory.getLogger(ChvRegisterServiceImpl.class);

    private final ChvRegisterRepositoryCustom repository;


    public ChvRegisterServiceCustomImpl(ChvRegisterRepositoryCustom repository) {
        super(repository);
        this.repository = repository;
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
