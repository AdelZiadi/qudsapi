package org.nmcpye.datarun.service.impl;

import java.util.List;
import java.util.Optional;
import org.nmcpye.datarun.domain.ProgressStatus;
import org.nmcpye.datarun.repository.ProgressStatusRepository;
import org.nmcpye.datarun.service.ProgressStatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link org.nmcpye.datarun.domain.ProgressStatus}.
 */
@Service
@Transactional
public class ProgressStatusServiceImpl implements ProgressStatusService {

    private final Logger log = LoggerFactory.getLogger(ProgressStatusServiceImpl.class);

    private final ProgressStatusRepository progressStatusRepository;

    public ProgressStatusServiceImpl(ProgressStatusRepository progressStatusRepository) {
        this.progressStatusRepository = progressStatusRepository;
    }

    @Override
    public ProgressStatus save(ProgressStatus progressStatus) {
        log.debug("Request to save ProgressStatus : {}", progressStatus);
        return progressStatusRepository.save(progressStatus);
    }

    @Override
    public ProgressStatus update(ProgressStatus progressStatus) {
        log.debug("Request to update ProgressStatus : {}", progressStatus);
        progressStatus.setIsPersisted();
        return progressStatusRepository.save(progressStatus);
    }

    @Override
    public Optional<ProgressStatus> partialUpdate(ProgressStatus progressStatus) {
        log.debug("Request to partially update ProgressStatus : {}", progressStatus);

        return progressStatusRepository
            .findById(progressStatus.getId())
            .map(existingProgressStatus -> {
                if (progressStatus.getUid() != null) {
                    existingProgressStatus.setUid(progressStatus.getUid());
                }
                if (progressStatus.getCode() != null) {
                    existingProgressStatus.setCode(progressStatus.getCode());
                }
                if (progressStatus.getName() != null) {
                    existingProgressStatus.setName(progressStatus.getName());
                }
                if (progressStatus.getCreatedBy() != null) {
                    existingProgressStatus.setCreatedBy(progressStatus.getCreatedBy());
                }
                if (progressStatus.getCreatedDate() != null) {
                    existingProgressStatus.setCreatedDate(progressStatus.getCreatedDate());
                }
                if (progressStatus.getLastModifiedBy() != null) {
                    existingProgressStatus.setLastModifiedBy(progressStatus.getLastModifiedBy());
                }
                if (progressStatus.getLastModifiedDate() != null) {
                    existingProgressStatus.setLastModifiedDate(progressStatus.getLastModifiedDate());
                }

                return existingProgressStatus;
            })
            .map(progressStatusRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProgressStatus> findAll() {
        log.debug("Request to get all ProgressStatuses");
        return progressStatusRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProgressStatus> findOne(Long id) {
        log.debug("Request to get ProgressStatus : {}", id);
        return progressStatusRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProgressStatus : {}", id);
        progressStatusRepository.deleteById(id);
    }
}
