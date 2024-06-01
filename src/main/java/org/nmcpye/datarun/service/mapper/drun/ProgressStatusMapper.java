package org.nmcpye.datarun.service.mapper.drun;

import org.mapstruct.Mapper;
import org.nmcpye.datarun.domain.*;
import org.nmcpye.datarun.service.dto.drun.*;

/**
 * Mapper for the entity {@link ProgressStatus} and its DTO {@link ProgressStatusDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProgressStatusMapper extends EntityMapper<ProgressStatusDTO, ProgressStatus> {}
