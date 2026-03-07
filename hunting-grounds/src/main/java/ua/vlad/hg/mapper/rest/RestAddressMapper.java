package ua.vlad.hg.mapper.rest;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ua.vlad.hg.dto.LatLng;
import ua.vlad.hg.entity.Address;
import ua.vlad.hg.dto.rest.RestAddressDto;

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
        if (address.getLatitude() == null || address.getLongitude() == null) {
            return null;
        }
        return LatLng.builder()
                .lat(address.getLatitude())
                .lng(address.getLongitude())
                .build();
    }

}
