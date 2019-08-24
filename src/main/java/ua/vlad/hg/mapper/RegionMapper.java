package ua.vlad.hg.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.vlad.hg.domain.Region;
import ua.vlad.hg.dto.RegionDto;

@Mapper
public interface RegionMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "country.name", target = "country")
    @Mapping(source = "parentRegion.name", target = "parentRegion")
    RegionDto toDto(Region region);

}
