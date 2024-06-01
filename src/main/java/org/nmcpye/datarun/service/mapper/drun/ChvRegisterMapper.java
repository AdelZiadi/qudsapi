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
    @Mapping(target = "patient", source = "patient", qualifiedByName = "patientInfoName")
    @Mapping(target = "team", source = "team", qualifiedByName = "teamCode")
    ChvRegisterDTO toDto(ChvRegister s);

    @Named("patientInfoName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "uid", source = "uid")
    @Mapping(target = "name", source = "name")
    PatientInfoDTO toDtoPatientInfoName(PatientInfo patientInfo);

    @Named("teamCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "uid", source = "uid")
    @Mapping(target = "code", source = "code")
    TeamDTO toDtoTeamCode(Team team);
}
