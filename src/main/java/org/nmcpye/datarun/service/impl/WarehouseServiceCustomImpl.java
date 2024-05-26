package org.nmcpye.datarun.service.impl;

import org.nmcpye.datarun.domain.Warehouse;
import org.nmcpye.datarun.repository.WarehouseRepositoryCustom;
import org.nmcpye.datarun.security.SecurityUtils;
import org.nmcpye.datarun.service.WarehouseServiceCustom;
import org.nmcpye.datarun.utils.CodeGenerator;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Primary
@Transactional
public class WarehouseServiceCustomImpl extends WarehouseServiceImpl implements WarehouseServiceCustom {

    final private WarehouseRepositoryCustom warehouseRepository;

    public WarehouseServiceCustomImpl(WarehouseRepositoryCustom warehouseRepository) {
        super(warehouseRepository);
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public Warehouse save(Warehouse warehouse) {
        if (warehouse.getUid() == null || warehouse.getUid().isEmpty()) {
            warehouse.setUid(CodeGenerator.generateUid());
        }
        return warehouseRepository.save(warehouse);
    }

    @Override
    public Page<Warehouse> findByCurrentUser(Pageable pageable) {
        final String userLogin = SecurityUtils.getCurrentUserLogin()
            .orElseThrow(() -> new IllegalStateException("User not logged in"));
        return warehouseRepository.findByCurrentUser(userLogin, pageable);
    }
}
