package ua.vlad.hg.rest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.util.StringUtils;
import ua.vlad.hg.core.dto.LatLng;
import ua.vlad.hg.core.entity.Address;
import ua.vlad.hg.rest.dto.RestAddressDto;

@Mapper
public interface RestAddressMapper {

    @Mapping(source = "country.name", target = "country")
    @Mapping(source = "region.id", target = "regionId")
    @Mapping(source = "region.name", target = "region")
    @Mapping(source = "subRegion.name", target = "subRegion")
    @Mapping(source = "address", target = "marker", qualifiedByName = "toLatLng")
    RestAddressDto toRestDto(Address address);

    @Named("toLatLng")
    default LatLng toLatLng(Address address) {
        if (StringUtils.isEmpty(address.getLatitude()) || StringUtils.isEmpty(address.getLongitude())) {
            return null;
        }
        return LatLng.builder()
                .lat(Double.parseDouble(address.getLatitude()))
                .lng(Double.parseDouble(address.getLongitude()))
                .build();
    }

}
