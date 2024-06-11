package org.nmcpye.datarun.service.impl;

import java.util.Optional;
import org.nmcpye.datarun.domain.WarehouseTransaction;
import org.nmcpye.datarun.repository.WarehouseTransactionRepository;
import org.nmcpye.datarun.service.WarehouseTransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link org.nmcpye.datarun.domain.WarehouseTransaction}.
 */
@Service
@Transactional
public class WarehouseTransactionServiceImpl implements WarehouseTransactionService {

    private final Logger log = LoggerFactory.getLogger(WarehouseTransactionServiceImpl.class);

    private final WarehouseTransactionRepository warehouseTransactionRepository;

    public WarehouseTransactionServiceImpl(WarehouseTransactionRepository warehouseTransactionRepository) {
        this.warehouseTransactionRepository = warehouseTransactionRepository;
    }

    @Override
    public WarehouseTransaction save(WarehouseTransaction warehouseTransaction) {
        log.debug("Request to save WarehouseTransaction : {}", warehouseTransaction);
        return warehouseTransactionRepository.save(warehouseTransaction);
    }

    @Override
    public WarehouseTransaction update(WarehouseTransaction warehouseTransaction) {
        log.debug("Request to update WarehouseTransaction : {}", warehouseTransaction);
        warehouseTransaction.setIsPersisted();
        return warehouseTransactionRepository.save(warehouseTransaction);
    }

    @Override
    public Optional<WarehouseTransaction> partialUpdate(WarehouseTransaction warehouseTransaction) {
        log.debug("Request to partially update WarehouseTransaction : {}", warehouseTransaction);

        return warehouseTransactionRepository
            .findById(warehouseTransaction.getId())
            .map(existingWarehouseTransaction -> {
                if (warehouseTransaction.getUid() != null) {
                    existingWarehouseTransaction.setUid(warehouseTransaction.getUid());
                }
                if (warehouseTransaction.getCode() != null) {
                    existingWarehouseTransaction.setCode(warehouseTransaction.getCode());
                }
                if (warehouseTransaction.getName() != null) {
                    existingWarehouseTransaction.setName(warehouseTransaction.getName());
                }
                if (warehouseTransaction.getImovUid() != null) {
                    existingWarehouseTransaction.setImovUid(warehouseTransaction.getImovUid());
                }
                if (warehouseTransaction.getTransactionDate() != null) {
                    existingWarehouseTransaction.setTransactionDate(warehouseTransaction.getTransactionDate());
                }
                if (warehouseTransaction.getPhaseNo() != null) {
                    existingWarehouseTransaction.setPhaseNo(warehouseTransaction.getPhaseNo());
                }
                if (warehouseTransaction.getEntryType() != null) {
                    existingWarehouseTransaction.setEntryType(warehouseTransaction.getEntryType());
                }
                if (warehouseTransaction.getQuantity() != null) {
                    existingWarehouseTransaction.setQuantity(warehouseTransaction.getQuantity());
                }
                if (warehouseTransaction.getNotes() != null) {
                    existingWarehouseTransaction.setNotes(warehouseTransaction.getNotes());
                }
                if (warehouseTransaction.getPersonName() != null) {
                    existingWarehouseTransaction.setPersonName(warehouseTransaction.getPersonName());
                }
                if (warehouseTransaction.getWorkDayId() != null) {
                    existingWarehouseTransaction.setWorkDayId(warehouseTransaction.getWorkDayId());
                }
                if (warehouseTransaction.getSubmissionTime() != null) {
                    existingWarehouseTransaction.setSubmissionTime(warehouseTransaction.getSubmissionTime());
                }
                if (warehouseTransaction.getSubmissionId() != null) {
                    existingWarehouseTransaction.setSubmissionId(warehouseTransaction.getSubmissionId());
                }
                if (warehouseTransaction.getDeleted() != null) {
                    existingWarehouseTransaction.setDeleted(warehouseTransaction.getDeleted());
                }
                if (warehouseTransaction.getSubmissionUuid() != null) {
                    existingWarehouseTransaction.setSubmissionUuid(warehouseTransaction.getSubmissionUuid());
                }
                if (warehouseTransaction.getStartEntryTime() != null) {
                    existingWarehouseTransaction.setStartEntryTime(warehouseTransaction.getStartEntryTime());
                }
                if (warehouseTransaction.getFinishedEntryTime() != null) {
                    existingWarehouseTransaction.setFinishedEntryTime(warehouseTransaction.getFinishedEntryTime());
                }
                if (warehouseTransaction.getStatus() != null) {
                    existingWarehouseTransaction.setStatus(warehouseTransaction.getStatus());
                }
                if (warehouseTransaction.getCreatedBy() != null) {
                    existingWarehouseTransaction.setCreatedBy(warehouseTransaction.getCreatedBy());
                }
                if (warehouseTransaction.getCreatedDate() != null) {
                    existingWarehouseTransaction.setCreatedDate(warehouseTransaction.getCreatedDate());
                }
                if (warehouseTransaction.getLastModifiedBy() != null) {
                    existingWarehouseTransaction.setLastModifiedBy(warehouseTransaction.getLastModifiedBy());
                }
                if (warehouseTransaction.getLastModifiedDate() != null) {
                    existingWarehouseTransaction.setLastModifiedDate(warehouseTransaction.getLastModifiedDate());
                }

                return existingWarehouseTransaction;
            })
            .map(warehouseTransactionRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<WarehouseTransaction> findAll(Pageable pageable) {
        log.debug("Request to get all WarehouseTransactions");
        return warehouseTransactionRepository.findAll(pageable);
    }

    public Page<WarehouseTransaction> findAllWithEagerRelationships(Pageable pageable) {
        return warehouseTransactionRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<WarehouseTransaction> findOne(Long id) {
        log.debug("Request to get WarehouseTransaction : {}", id);
        return warehouseTransactionRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete WarehouseTransaction : {}", id);
        warehouseTransactionRepository.deleteById(id);
    }
}
