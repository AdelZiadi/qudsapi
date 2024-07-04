package org.nmcpye.datarun.web.rest;

import org.nmcpye.datarun.domain.ChvRegister;
import org.nmcpye.datarun.drun.repository.ChvRegisterRepositoryCustom;
import org.nmcpye.datarun.drun.service.ChvRegisterServiceCustom;
import org.nmcpye.datarun.web.rest.common.AbstractResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing {@link ChvRegister}.
 */
@RestController
@RequestMapping("/api/custom/chvRegisters")
public class ChvRegisterResourceCustom
    extends AbstractResource<ChvRegister> {

    private final Logger log = LoggerFactory.getLogger(ChvRegisterResourceCustom.class);

    private final ChvRegisterServiceCustom chvRegisterService;

    private final ChvRegisterRepositoryCustom chvRegisterRepository;

    public ChvRegisterResourceCustom(ChvRegisterServiceCustom chvRegisterService,
                                     ChvRegisterRepositoryCustom chvRegisterRepository) {
        super(chvRegisterService, chvRegisterRepository);
        this.chvRegisterService = chvRegisterService;
        this.chvRegisterRepository = chvRegisterRepository;
    }

    @Override
    protected Page<ChvRegister> getList(Pageable pageable, boolean eagerload) {
        if (eagerload) {
            return identifiableService.findAllWithEagerRelationships(pageable);
        } else {
            return identifiableService.findAll(pageable);
        }
    }

    @Override
    protected String getName() {
        return "chvRegisters";
    }
}
