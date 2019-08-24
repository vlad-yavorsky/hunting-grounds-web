package ua.vlad.hg.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.vlad.hg.dto.CountryDto;
import ua.vlad.hg.dto.GroundDto;
import ua.vlad.hg.dto.RegionDto;
import ua.vlad.hg.service.GroundService;
import ua.vlad.hg.service.RegionService;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RestApiController {

    private final GroundService groundService;
    private final RegionService regionService;

    @GetMapping("/ground/{id}")
    public GroundDto getGround(@PathVariable Long id) {
        return groundService.findById(id);
    }

    @GetMapping("/grounds")
    public List<GroundDto> getGrounds() {
        return groundService.findAll();
    }

    @GetMapping("/countries")
    public List<CountryDto> getCounties() {
        return regionService.findAllCountries();
    }

    @GetMapping("/region/{id}")
    public RegionDto getRegion(@PathVariable Long id) {
        return regionService.findById(id);
    }

    @GetMapping("/regions/{countryId}")
    public List<RegionDto> getRegionsByCountry(@PathVariable Long countryId) {
        return regionService.findAllByCountryIdAndParentRegionIsNull(countryId);
    }

    @GetMapping("/subregions/{parentId}")
    public List<RegionDto> getSubRegionsByParentId(@PathVariable Long parentId) {
        return regionService.findAllByParentRegionId(parentId);
    }

}
