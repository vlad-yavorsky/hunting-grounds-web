package ua.vlad.hg.core.updater.mapper;

import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ua.vlad.hg.core.entity.Ground;
import ua.vlad.hg.core.entity.Region;
import ua.vlad.hg.core.updater.dto.GroundCsvDto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Mapper
public interface GroundCsvDtoMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "alias", target = "alias")
    @Mapping(source = "area", target = "area")
    @Mapping(source = "kmlPath", target = "kml", qualifiedByName = "kmlPathToKml")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "city", target = "address.city")
    @Mapping(source = "street", target = "address.street")
    @Mapping(source = "zipCode", target = "address.zipCode")
    @Mapping(source = "latitude", target = "address.latitude")
    @Mapping(source = "longitude", target = "address.longitude")
    @Mapping(source = "info", target = "address.info")
    @Mapping(source = "countryId", target = "address.country.id")
    @Mapping(source = "regionId", target = "address.region.id")
    @Mapping(source = "subRegionId", target = "address.subRegion", qualifiedByName = "subRegion")
    @Mapping(source = "type", target = "address.type")
    Ground toEntity(GroundCsvDto groundCsvDto);

    List<Ground> toEntity(List<GroundCsvDto> groundCsvDto);

    @Named("kmlPathToKml")
    default String kmlPathToKml(String kmlPath) throws IOException {
        return (StringUtils.isBlank(kmlPath)) ? "" : Files.readString(Path.of(kmlPath));
    }

    @Named("subRegion")
    default Region subRegion(String subRegionId) {
        if (StringUtils.isBlank(subRegionId)) {
            return null;
        }
        Region subRegion = new Region();
        subRegion.setId(Long.valueOf(subRegionId));
        return subRegion;
    }

}
