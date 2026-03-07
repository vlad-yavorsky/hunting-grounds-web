package ua.vlad.hg.mapper.rest;

import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ua.vlad.hg.dto.Polygon;
import ua.vlad.hg.entity.Ground;
import ua.vlad.hg.service.KmlDocument;
import ua.vlad.hg.dto.rest.RestGroundDto;

import java.util.List;

@Mapper(uses = RestAddressMapper.class)
public interface RestGroundMapper {

    @Mapping(source = "kml", target = "polygons", qualifiedByName = "kmlToPolygons")
    RestGroundDto toRestDto(Ground ground);

    @Named("kmlToPolygons")
    default List<Polygon> kmlToPolygons(String kml) {
        return StringUtils.isBlank(kml) ? null : KmlDocument.of(kml).extractPolygons();
    }

}
