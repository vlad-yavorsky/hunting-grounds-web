package ua.vlad.hg.rest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.vlad.hg.core.entity.Region;
import ua.vlad.hg.rest.dto.RestRegionDto;

@Mapper
public interface RestRegionMapper {

    @Mapping(source = "country.name", target = "country")
    @Mapping(source = "parentRegion.name", target = "parentRegion")
    RestRegionDto toRestDto(Region region);

}
