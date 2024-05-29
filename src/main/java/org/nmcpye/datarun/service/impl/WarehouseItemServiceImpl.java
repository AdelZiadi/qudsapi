package org.nmcpye.datarun.service.impl;

import org.nmcpye.datarun.domain.WarehouseItem;
import org.nmcpye.datarun.repository.WarehouseItemRepository;
import org.nmcpye.datarun.service.WarehouseItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link org.nmcpye.datarun.domain.WarehouseItem}.
 */
@Service
@Transactional
public class WarehouseItemServiceImpl implements WarehouseItemService {

    private final Logger log = LoggerFactory.getLogger(WarehouseItemServiceImpl.class);

    private final WarehouseItemRepository warehouseItemRepository;

    public WarehouseItemServiceImpl(WarehouseItemRepository warehouseItemRepository) {
        this.warehouseItemRepository = warehouseItemRepository;
    }

    @Override
    public WarehouseItem save(WarehouseItem warehouseItem) {
        log.debug("Request to save WarehouseItem : {}", warehouseItem);
        return warehouseItemRepository.save(warehouseItem);
    }

    @Override
    public WarehouseItem update(WarehouseItem warehouseItem) {
        log.debug("Request to update WarehouseItem : {}", warehouseItem);
        warehouseItem.setIsPersisted();
        return warehouseItemRepository.save(warehouseItem);
    }

    @Override
    public Optional<WarehouseItem> partialUpdate(WarehouseItem warehouseItem) {
        log.debug("Request to partially update WarehouseItem : {}", warehouseItem);

        return warehouseItemRepository
            .findById(warehouseItem.getId())
            .map(existingWarehouseItem -> {
                if (warehouseItem.getUid() != null) {
                    existingWarehouseItem.setUid(warehouseItem.getUid());
                }
                if (warehouseItem.getCode() != null) {
                    existingWarehouseItem.setCode(warehouseItem.getCode());
                }
                if (warehouseItem.getName() != null) {
                    existingWarehouseItem.setName(warehouseItem.getName());
                }
                if (warehouseItem.getDescription() != null) {
                    existingWarehouseItem.setDescription(warehouseItem.getDescription());
                }
                if (warehouseItem.getCreatedBy() != null) {
                    existingWarehouseItem.setCreatedBy(warehouseItem.getCreatedBy());
                }
                if (warehouseItem.getCreatedDate() != null) {
                    existingWarehouseItem.setCreatedDate(warehouseItem.getCreatedDate());
                }
                if (warehouseItem.getLastModifiedBy() != null) {
                    existingWarehouseItem.setLastModifiedBy(warehouseItem.getLastModifiedBy());
                }
                if (warehouseItem.getLastModifiedDate() != null) {
                    existingWarehouseItem.setLastModifiedDate(warehouseItem.getLastModifiedDate());
                }

                return existingWarehouseItem;
            })
            .map(warehouseItemRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WarehouseItem> findAll() {
        log.debug("Request to get all WarehouseItems");
        return warehouseItemRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<WarehouseItem> findOne(Long id) {
        log.debug("Request to get WarehouseItem : {}", id);
        return warehouseItemRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete WarehouseItem : {}", id);
        warehouseItemRepository.deleteById(id);
    }
}
