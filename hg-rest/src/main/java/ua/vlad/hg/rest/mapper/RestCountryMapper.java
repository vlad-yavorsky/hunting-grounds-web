package ua.vlad.hg.rest.mapper;

import org.mapstruct.Mapper;
import ua.vlad.hg.core.entity.Country;
import ua.vlad.hg.rest.dto.RestCountryDto;

@Mapper
public interface RestCountryMapper {

    RestCountryDto toRestDto(Country country);

}
