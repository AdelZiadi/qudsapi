package org.nmcpye.datarun.service.mapper.drun;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.nmcpye.datarun.domain.*;
import org.nmcpye.datarun.service.dto.drun.*;

/**
 * Mapper for the entity {@link ItnsVillageHousesDetail} and its DTO {@link ItnsVillageHousesDetailDTO}.
 */
@Mapper(componentModel = "spring")
public interface ItnsVillageHousesDetailMapper extends EntityMapper<ItnsVillageHousesDetailDTO, ItnsVillageHousesDetail> {
    @Mapping(target = "villageData", source = "villageData", qualifiedByName = "itnsVillageId")
    ItnsVillageHousesDetailDTO toDto(ItnsVillageHousesDetail s);

    @Named("itnsVillageId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "uid", source = "uid")
    ItnsVillageDTO toDtoItnsVillageId(ItnsVillage itnsVillage);
}
