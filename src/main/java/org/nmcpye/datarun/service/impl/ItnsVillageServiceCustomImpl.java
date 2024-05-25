package org.nmcpye.datarun.service.impl;

import org.nmcpye.datarun.domain.ItnsVillage;
import org.nmcpye.datarun.repository.ItnsVillageRepository;
import org.nmcpye.datarun.repository.ItnsVillageRepositoryCustom;
import org.nmcpye.datarun.service.ItnsVillageServiceCustom;
import org.nmcpye.datarun.utils.CodeGenerator;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Primary
@Transactional
public class ItnsVillageServiceCustomImpl extends ItnsVillageServiceImpl implements ItnsVillageServiceCustom {

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
}
