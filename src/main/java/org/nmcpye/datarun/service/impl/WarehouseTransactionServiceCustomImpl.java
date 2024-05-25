package org.nmcpye.datarun.service.impl;

import org.nmcpye.datarun.domain.WarehouseTransaction;
import org.nmcpye.datarun.repository.WarehouseTransactionRepositoryCustom;
import org.nmcpye.datarun.service.WarehouseTransactionServiceCustom;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Primary
@Transactional
public class WarehouseTransactionServiceCustomImpl
    extends WarehouseTransactionServiceImpl implements WarehouseTransactionServiceCustom {
    final private WarehouseTransactionRepositoryCustom warehouseTransactionRepository;

    public WarehouseTransactionServiceCustomImpl(WarehouseTransactionRepositoryCustom warehouseTransactionRepository) {
        super(warehouseTransactionRepository);
        this.warehouseTransactionRepository = warehouseTransactionRepository;
    }

    @Override
    public WarehouseTransaction save(WarehouseTransaction warehouseTransaction) {
//        if (warehouseTransaction.getUid() == null || warehouseTransaction.getUid().isEmpty()) {
//            warehouseTransaction.setUid(CodeGenerator.generateUid());
//        }
        return warehouseTransactionRepository.save(warehouseTransaction);
    }
}
