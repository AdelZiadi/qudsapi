package org.nmcpye.datarun.service.mapper.drun;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.nmcpye.datarun.domain.*;
import org.nmcpye.datarun.service.dto.drun.*;

/**
 * Mapper for the entity {@link ChvSession} and its DTO {@link ChvSessionDTO}.
 */
@Mapper(componentModel = "spring")
public interface ChvSessionMapper extends EntityMapper<ChvSessionDTO, ChvSession> {
    @Mapping(target = "team", source = "team", qualifiedByName = "teamCode")
    @Mapping(target = "activity", source = "activity", qualifiedByName = "activityCode")
    ChvSessionDTO toDto(ChvSession s);

    @Named("teamCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "uid", source = "uid")
    @Mapping(target = "code", source = "code")
    TeamDTO toDtoTeamCode(Team team);

    @Named("activityCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "uid", source = "uid")
    @Mapping(target = "code", source = "code")
    ActivityDTO toDtoActivityCode(Activity activity);
}
