package ua.vlad.hg.rest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.vlad.hg.core.entity.Address;
import ua.vlad.hg.rest.dto.RestAddressDto;

@Mapper
public interface RestAddressMapper {

    @Mapping(source = "country.name", target = "country")
    @Mapping(source = "region.id", target = "regionId")
    @Mapping(source = "region.name", target = "region")
    @Mapping(source = "subRegion.name", target = "subRegion")
    @Mapping(source = "address.latitude", target = "marker.lat")
    @Mapping(source = "address.longitude", target = "marker.lng")
    RestAddressDto toRestDto(Address address);

}
