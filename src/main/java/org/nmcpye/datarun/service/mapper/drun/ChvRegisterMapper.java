package org.nmcpye.datarun.service.mapper.drun;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.nmcpye.datarun.domain.*;
import org.nmcpye.datarun.service.dto.drun.*;

/**
 * Mapper for the entity {@link ChvRegister} and its DTO {@link ChvRegisterDTO}.
 */
@Mapper(componentModel = "spring")
public interface ChvRegisterMapper extends EntityMapper<ChvRegisterDTO, ChvRegister> {
    @Mapping(target = "location", source = "location", qualifiedByName = "assignmentCode")
    @Mapping(target = "team", source = "team", qualifiedByName = "teamCode")
    @Mapping(target = "activity", source = "activity", qualifiedByName = "activityCode")
    ChvRegisterDTO toDto(ChvRegister s);

    @Named("assignmentCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "uid", source = "uid")
    @Mapping(target = "code", source = "code")
    AssignmentDTO toDtoAssignmentCode(Assignment assignment);

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
