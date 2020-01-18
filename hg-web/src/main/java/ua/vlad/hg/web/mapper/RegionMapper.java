package ua.vlad.hg.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.vlad.hg.core.entity.Region;
import ua.vlad.hg.web.dto.RegionDto;

@Mapper
public interface RegionMapper {

    @Mapping(source = "id", target = "id")
    Region toEntity(Long id);

    RegionDto toDto(Region region);

}
