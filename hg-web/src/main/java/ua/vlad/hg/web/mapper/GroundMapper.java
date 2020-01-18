package ua.vlad.hg.web.mapper;

import org.mapstruct.Mapper;
import ua.vlad.hg.core.entity.Ground;
import ua.vlad.hg.web.dto.GroundCreateDto;
import ua.vlad.hg.web.dto.GroundDto;
import ua.vlad.hg.web.dto.GroundUpdateDto;

@Mapper(uses = AddressMapper.class)
public interface GroundMapper {

    Ground toEntity(GroundCreateDto groundCreateDto);
    Ground toEntity(GroundUpdateDto groundUpdateDto);

    GroundUpdateDto toUpdateDto(Ground ground);
    GroundDto toDto(Ground ground);

}
