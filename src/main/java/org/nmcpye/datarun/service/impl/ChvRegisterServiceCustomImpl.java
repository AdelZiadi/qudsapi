package org.nmcpye.datarun.service.impl;

import org.nmcpye.datarun.domain.ChvRegister;
import org.nmcpye.datarun.repository.ChvRegisterRepositoryCustom;
import org.nmcpye.datarun.service.ChvRegisterServiceCustom;
import org.nmcpye.datarun.utils.CodeGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Primary
@Transactional
public class ChvRegisterServiceCustomImpl
    extends ChvRegisterServiceImpl
    implements ChvRegisterServiceCustom {

    private final Logger log = LoggerFactory.getLogger(ChvRegisterServiceImpl.class);

    private final ChvRegisterRepositoryCustom chvRegisterRepository;


    public ChvRegisterServiceCustomImpl(ChvRegisterRepositoryCustom chvRegisterRepository) {
        super(chvRegisterRepository);
        this.chvRegisterRepository = chvRegisterRepository;
    }

    @Override
    public ChvRegister save(ChvRegister chvRegister) {
        if (chvRegister.getUid() == null || chvRegister.getUid().isEmpty()) {
            chvRegister.setUid(CodeGenerator.generateUid());
        }
        log.debug("Request to save ChvRegister : {}", chvRegister);
        return chvRegisterRepository.save(chvRegister);
    }
}
