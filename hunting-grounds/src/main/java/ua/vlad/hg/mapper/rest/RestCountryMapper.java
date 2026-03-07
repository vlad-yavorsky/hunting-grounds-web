package ua.vlad.hg.mapper.rest;

import org.mapstruct.Mapper;
import ua.vlad.hg.entity.Country;
import ua.vlad.hg.dto.rest.RestCountryDto;

@Mapper
public interface RestCountryMapper {

    RestCountryDto toRestDto(Country country);

}
