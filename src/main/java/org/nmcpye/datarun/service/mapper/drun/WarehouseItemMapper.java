package org.nmcpye.datarun.service.mapper.drun;

import org.mapstruct.Mapper;
import org.nmcpye.datarun.domain.*;
import org.nmcpye.datarun.service.dto.drun.*;

/**
 * Mapper for the entity {@link WarehouseItem} and its DTO {@link WarehouseItemDTO}.
 */
@Mapper(componentModel = "spring")
public interface WarehouseItemMapper extends EntityMapper<WarehouseItemDTO, WarehouseItem> {}
