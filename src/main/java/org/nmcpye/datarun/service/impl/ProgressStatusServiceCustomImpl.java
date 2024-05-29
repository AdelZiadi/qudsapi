package org.nmcpye.datarun.service.impl;

import org.nmcpye.datarun.domain.ProgressStatus;
import org.nmcpye.datarun.repository.ProgressStatusRepositoryCustom;
import org.nmcpye.datarun.service.ProgressStatusServiceCustom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Primary
@Transactional
public class ProgressStatusServiceCustomImpl
    extends ProgressStatusServiceImpl
    implements ProgressStatusServiceCustom {

    private final Logger log = LoggerFactory.getLogger(TeamServiceCustomImpl.class);

    ProgressStatusRepositoryCustom progressStatusRepository;

    public ProgressStatusServiceCustomImpl(ProgressStatusRepositoryCustom progressStatusRepository) {
        super(progressStatusRepository);
        this.progressStatusRepository = progressStatusRepository;
    }

    @Override
    public Page<ProgressStatus> findAll(Pageable pageable) {
        log.debug("Request to get all ProgressStatuses");
        return progressStatusRepository.findAll(pageable);
    }
}
