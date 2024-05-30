package org.nmcpye.datarun.service.impl;

import java.util.Optional;
import org.nmcpye.datarun.domain.ChvRegister;
import org.nmcpye.datarun.repository.ChvRegisterRepository;
import org.nmcpye.datarun.service.ChvRegisterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link org.nmcpye.datarun.domain.ChvRegister}.
 */
@Service
@Transactional
public class ChvRegisterServiceImpl implements ChvRegisterService {

    private final Logger log = LoggerFactory.getLogger(ChvRegisterServiceImpl.class);

    private final ChvRegisterRepository chvRegisterRepository;

    public ChvRegisterServiceImpl(ChvRegisterRepository chvRegisterRepository) {
        this.chvRegisterRepository = chvRegisterRepository;
    }

    @Override
    public ChvRegister save(ChvRegister chvRegister) {
        log.debug("Request to save ChvRegister : {}", chvRegister);
        return chvRegisterRepository.save(chvRegister);
    }

    @Override
    public ChvRegister update(ChvRegister chvRegister) {
        log.debug("Request to update ChvRegister : {}", chvRegister);
        chvRegister.setIsPersisted();
        return chvRegisterRepository.save(chvRegister);
    }

    @Override
    public Optional<ChvRegister> partialUpdate(ChvRegister chvRegister) {
        log.debug("Request to partially update ChvRegister : {}", chvRegister);

        return chvRegisterRepository
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
                if (chvRegister.getComment() != null) {
                    existingChvRegister.setComment(chvRegister.getComment());
                }
                if (chvRegister.getStartEntryTime() != null) {
                    existingChvRegister.setStartEntryTime(chvRegister.getStartEntryTime());
                }
                if (chvRegister.getDeleted() != null) {
                    existingChvRegister.setDeleted(chvRegister.getDeleted());
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
            .map(chvRegisterRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ChvRegister> findAll(Pageable pageable) {
        log.debug("Request to get all ChvRegisters");
        return chvRegisterRepository.findAll(pageable);
    }

    public Page<ChvRegister> findAllWithEagerRelationships(Pageable pageable) {
        return chvRegisterRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ChvRegister> findOne(Long id) {
        log.debug("Request to get ChvRegister : {}", id);
        return chvRegisterRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ChvRegister : {}", id);
        chvRegisterRepository.deleteById(id);
    }
}
