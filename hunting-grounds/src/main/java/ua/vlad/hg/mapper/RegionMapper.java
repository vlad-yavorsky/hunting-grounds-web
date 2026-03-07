package ua.vlad.hg.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.vlad.hg.entity.Region;
import ua.vlad.hg.dto.RegionDto;

@Mapper
public interface RegionMapper {

    @Mapping(source = "id", target = "id")
    Region toEntity(Long id);

    RegionDto toDto(Region region);

}
