package org.nmcpye.datarun.drun.service;

import org.nmcpye.datarun.domain.ChvRegister;
import org.nmcpye.datarun.service.ChvRegisterService;
import org.nmcpye.datarun.service.dto.drun.SaveSummaryDTO;

/**
 * Service Interface for managing {@link ChvRegister}.
 */
public interface ChvRegisterServiceCustom
    extends IdentifiableService<ChvRegister>, ChvRegisterService {

    SaveSummaryDTO saveWithReferences(ChvRegister chvRegister);
}
