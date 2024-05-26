package org.nmcpye.datarun.service;

import org.nmcpye.datarun.domain.Warehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Custom Service Interface for managing {@link Warehouse}.
 */
public interface WarehouseServiceCustom extends WarehouseService {
    Page<Warehouse> findByCurrentUser(Pageable pageable);
}
