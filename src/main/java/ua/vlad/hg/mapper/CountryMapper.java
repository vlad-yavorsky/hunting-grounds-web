package ua.vlad.hg.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.vlad.hg.domain.Country;
import ua.vlad.hg.dto.CountryDto;

@Mapper
public interface CountryMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "isoNumber", target = "isoNumber")
    @Mapping(source = "isoAlpha3Code", target = "isoAlpha3Code")
    CountryDto toDto(Country country);

}
