package ua.vlad.hg.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ua.vlad.hg.domain.Address;
import ua.vlad.hg.dto.AddressDto;
import ua.vlad.hg.dto.LatLng;

@Mapper
public interface AddressMapper {

    @Mapping(source = "zipCode", target = "zipCode")
    @Mapping(source = "country.name", target = "country")
    @Mapping(source = "region.id", target = "regionId")
    @Mapping(source = "region.name", target = "regionName")
    @Mapping(source = "subRegion.name", target = "subRegion")
    @Mapping(source = "city", target = "city")
    @Mapping(source = "street", target = "street")
    @Mapping(source = "address", target = "marker", qualifiedByName = "toMarker")
    @Mapping(source = "info", target = "info")
    AddressDto toDto(Address address);

    @Named("toMarker")
    default LatLng toLatLng(Address address) {
        // hides marker coordinates from rest response if lat or lng are null, else will be empty object
        return address.getLatitude() == null || address.getLongitude() == null
                ? null
                : new LatLng(address.getLatitude(), address.getLongitude());
    }

}
