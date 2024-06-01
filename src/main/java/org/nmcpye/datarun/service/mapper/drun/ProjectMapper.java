package org.nmcpye.datarun.service.mapper.drun;

import org.mapstruct.Mapper;
import org.nmcpye.datarun.domain.*;
import org.nmcpye.datarun.service.dto.drun.*;

/**
 * Mapper for the entity {@link Project} and its DTO {@link ProjectDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProjectMapper extends EntityMapper<ProjectDTO, Project> {}
