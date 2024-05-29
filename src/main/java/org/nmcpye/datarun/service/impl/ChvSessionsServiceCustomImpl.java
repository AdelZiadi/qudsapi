package org.nmcpye.datarun.service.impl;

import org.nmcpye.datarun.domain.ChvSessions;
import org.nmcpye.datarun.repository.ChvSessionsRepositoryCustom;
import org.nmcpye.datarun.service.ChvSessionsServiceCustom;
import org.nmcpye.datarun.utils.CodeGenerator;
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
public class ChvSessionsServiceCustomImpl
    extends ChvSessionsServiceImpl
    implements ChvSessionsServiceCustom {

    private final Logger log = LoggerFactory.getLogger(ChvSessionsServiceCustomImpl.class);

    private final ChvSessionsRepositoryCustom chvSessionsRepository;


    public ChvSessionsServiceCustomImpl(ChvSessionsRepositoryCustom chvSessionsRepository) {
        super(chvSessionsRepository);
        this.chvSessionsRepository = chvSessionsRepository;
    }

    @Override
    public ChvSessions save(ChvSessions chvSessions) {
        if (chvSessions.getUid() == null || chvSessions.getUid().isEmpty()) {
            chvSessions.setUid(CodeGenerator.generateUid());
        }
        return chvSessionsRepository.save(chvSessions);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ChvSessions> findAll(Pageable pageable) {
        log.debug("Request to get all ChvSessions");
        return chvSessionsRepository.findAllByUser(pageable);
    }

    public Page<ChvSessions> findAllWithEagerRelationships(Pageable pageable) {
        return chvSessionsRepository.findAllWithEagerRelationshipsByUser(pageable);
    }
}
