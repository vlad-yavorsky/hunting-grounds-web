package ua.vlad.hg.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.vlad.hg.core.entity.Address;
import ua.vlad.hg.web.dto.AddressCreateDto;
import ua.vlad.hg.web.dto.AddressDto;
import ua.vlad.hg.web.dto.AddressUpdateDto;

@Mapper(uses = RegionMapper.class)
public interface AddressMapper {

    @Mapping(source = "countryId", target = "country.id")
    @Mapping(source = "regionId", target = "region.id")
    @Mapping(source = "subRegionId", target = "subRegion")
    Address toEntity(AddressCreateDto addressCreateDto);

    @Mapping(source = "countryId", target = "country.id")
    @Mapping(source = "regionId", target = "region.id")
    @Mapping(source = "subRegionId", target = "subRegion")
    Address toEntity(AddressUpdateDto addressUpdateDto);

    @Mapping(source = "country.id", target = "countryId")
    @Mapping(source = "region.id", target = "regionId")
    @Mapping(source = "subRegion.id", target = "subRegionId")
    AddressUpdateDto toUpdateDto(Address address);

    @Mapping(source = "country.id", target = "countryId")
    @Mapping(source = "region.id", target = "regionId")
    @Mapping(source = "subRegion.id", target = "subRegionId")
    AddressDto toDto(Address address);

}
