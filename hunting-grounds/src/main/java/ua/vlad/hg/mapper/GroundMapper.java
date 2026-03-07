package ua.vlad.hg.mapper;

import org.mapstruct.Mapper;
import ua.vlad.hg.entity.Ground;
import ua.vlad.hg.dto.GroundCreateDto;
import ua.vlad.hg.dto.GroundDto;
import ua.vlad.hg.dto.GroundUpdateDto;

@Mapper(uses = AddressMapper.class)
public interface GroundMapper {

    Ground toEntity(GroundCreateDto groundCreateDto);
    Ground toEntity(GroundUpdateDto groundUpdateDto);

    GroundUpdateDto toUpdateDto(Ground ground);
    GroundDto toDto(Ground ground);

}
