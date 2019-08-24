package ua.vlad.hg.service;

import ua.vlad.hg.domain.Region;
import ua.vlad.hg.dto.CountryDto;
import ua.vlad.hg.dto.RegionDto;

import java.util.List;

public interface RegionService {

    Region save(Region project);

    void delete(Region ground);

    void deleteById(Long id);

    RegionDto findById(Long id);

    List<CountryDto> findAllCountries();

    List<RegionDto> findAllByCountryIdAndParentRegionIsNull(Long countryId);

    List<RegionDto> findAllByParentRegionId(Long parentRegionId);
}
