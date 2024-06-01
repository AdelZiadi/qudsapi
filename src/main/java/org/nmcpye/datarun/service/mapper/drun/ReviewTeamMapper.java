package org.nmcpye.datarun.service.mapper.drun;

import org.mapstruct.Mapper;
import org.nmcpye.datarun.domain.*;
import org.nmcpye.datarun.service.dto.drun.*;

/**
 * Mapper for the entity {@link ReviewTeam} and its DTO {@link ReviewTeamDTO}.
 */
@Mapper(componentModel = "spring")
public interface ReviewTeamMapper extends EntityMapper<ReviewTeamDTO, ReviewTeam> {}
