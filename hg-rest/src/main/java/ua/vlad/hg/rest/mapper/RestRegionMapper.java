package ua.vlad.hg.rest.mapper;

import org.mapstruct.Mapper;
import ua.vlad.hg.core.entity.Region;
import ua.vlad.hg.rest.dto.RestRegionDto;

@Mapper
public interface RestRegionMapper {

    RestRegionDto toRestDto(Region region);

}
