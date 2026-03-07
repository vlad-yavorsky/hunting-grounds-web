package ua.vlad.hg.mapper.rest;

import org.mapstruct.Mapper;
import ua.vlad.hg.entity.Region;
import ua.vlad.hg.dto.rest.RestRegionDto;

@Mapper
public interface RestRegionMapper {

    RestRegionDto toRestDto(Region region);

}
