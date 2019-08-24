package ua.vlad.hg.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.util.StringUtils;
import ua.vlad.hg.domain.Ground;
import ua.vlad.hg.dto.GroundDto;
import ua.vlad.hg.dto.Polygon;
import ua.vlad.hg.util.KmlDocument;

import java.util.List;

@Mapper(uses = AddressMapper.class)
public interface GroundMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "alias", target = "alias")
    @Mapping(source = "created", target = "created")
    @Mapping(source = "area", target = "area")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "kml", target = "polygons", qualifiedByName = "kmlToPolygons")
    GroundDto toDto(Ground ground);

    @Named("kmlToPolygons")
    default List<Polygon> kmlToPolygons(String kml) {
        return !StringUtils.isEmpty(kml) ? new KmlDocument(kml).extractPolygons() : null;
    }

}
