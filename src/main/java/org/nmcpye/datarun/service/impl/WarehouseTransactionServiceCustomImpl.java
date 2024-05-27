package org.nmcpye.datarun.service.impl;

import org.nmcpye.datarun.domain.WarehouseTransaction;
import org.nmcpye.datarun.repository.WarehouseTransactionRepositoryCustom;
import org.nmcpye.datarun.security.SecurityUtils;
import org.nmcpye.datarun.service.WarehouseTransactionServiceCustom;
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
public class WarehouseTransactionServiceCustomImpl
    extends WarehouseTransactionServiceImpl
    implements WarehouseTransactionServiceCustom {

    private final Logger log = LoggerFactory.getLogger(WarehouseTransactionServiceCustomImpl.class);

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

    public Page<WarehouseTransaction> findAllWithEagerRelationships(Pageable pageable) {
        return warehouseTransactionRepository.findAllWithEagerRelationshipsByUser(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<WarehouseTransaction> findOne(Long id) {
        log.debug("Request to get WarehouseTransaction : {}", id);
        return warehouseTransactionRepository.findOneWithEagerRelationshipsByUser(id);
    }
}
