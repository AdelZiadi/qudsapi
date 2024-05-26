package org.nmcpye.datarun.service.impl;

import org.nmcpye.datarun.domain.ItnsVillage;
import org.nmcpye.datarun.repository.ItnsVillageRepositoryCustom;
import org.nmcpye.datarun.security.SecurityUtils;
import org.nmcpye.datarun.service.ItnsVillageServiceCustom;
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
public class ItnsVillageServiceCustomImpl extends ItnsVillageServiceImpl implements ItnsVillageServiceCustom {

    private final Logger log = LoggerFactory.getLogger(ItnsVillageServiceCustomImpl.class);

    final private ItnsVillageRepositoryCustom itnsVillageRepository;

    public ItnsVillageServiceCustomImpl(ItnsVillageRepositoryCustom itnsVillageRepository) {
        super(itnsVillageRepository);
        this.itnsVillageRepository = itnsVillageRepository;
    }

    @Override
    public ItnsVillage save(ItnsVillage itnsVillage) {
        if (itnsVillage.getUid() == null || itnsVillage.getUid().isEmpty()) {
            itnsVillage.setUid(CodeGenerator.generateUid());
        }
        return itnsVillageRepository.save(itnsVillage);
    }

    @Override
    public Page<ItnsVillage> findAllWithEagerRelationships(Pageable pageable) {
        final String userLogin = SecurityUtils.getCurrentUserLogin()
            .orElseThrow(() -> new IllegalStateException("User not logged in"));
        return itnsVillageRepository.findAllWithEagerRelationshipsByUser(pageable, userLogin);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ItnsVillage> findOne(Long id) {
        final String userLogin = SecurityUtils.getCurrentUserLogin()
            .orElseThrow(() -> new IllegalStateException("User not logged in"));
        log.debug("Request to get ItnsVillage : {}", id);
        return itnsVillageRepository.findOneWithEagerRelationshipsByUser(id, userLogin);
    }
}
