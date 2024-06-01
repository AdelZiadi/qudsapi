package org.nmcpye.datarun.service.mapper.drun;

import org.mapstruct.Mapper;
import org.nmcpye.datarun.domain.*;
import org.nmcpye.datarun.service.dto.drun.*;

/**
 * Mapper for the entity {@link VillageLocation} and its DTO {@link VillageLocationDTO}.
 */
@Mapper(componentModel = "spring")
public interface VillageLocationMapper extends EntityMapper<VillageLocationDTO, VillageLocation> {}
