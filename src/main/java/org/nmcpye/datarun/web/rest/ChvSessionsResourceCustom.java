package org.nmcpye.datarun.web.rest;

import org.nmcpye.datarun.domain.ChvSessions;
import org.nmcpye.datarun.repository.ChvSessionsRepositoryCustom;
import org.nmcpye.datarun.service.ChvSessionsServiceCustom;
import org.nmcpye.datarun.web.rest.common.AbstractResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * REST Custom controller for managing {@link ChvSessions}.
 */
@RestController
@RequestMapping("/api/custom/chvSessions")
public class ChvSessionsResourceCustom extends AbstractResource<ChvSessions> {

    private final Logger log = LoggerFactory.getLogger(ChvSessionsResourceCustom.class);

    private final ChvSessionsServiceCustom chvSessionsService;

    private final ChvSessionsRepositoryCustom chvSessionsRepository;

    public ChvSessionsResourceCustom(ChvSessionsServiceCustom chvSessionsService,
                                     ChvSessionsRepositoryCustom chvSessionsRepository) {
        this.chvSessionsService = chvSessionsService;
        this.chvSessionsRepository = chvSessionsRepository;
    }

    @Override
    protected Page<ChvSessions> getList(Pageable pageable, boolean eagerload) {
        if (eagerload) {
            return chvSessionsService.findAllWithEagerRelationships(pageable);
        } else {
            return chvSessionsService.findAll(pageable);
        }
    }

    @Override
    protected String getName() {
        return "chvSessions";
    }
}
