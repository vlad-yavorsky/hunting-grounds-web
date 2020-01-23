package ua.vlad.hg.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.vlad.hg.core.service.RegionService;
import ua.vlad.hg.rest.dto.RestCountryDto;
import ua.vlad.hg.rest.dto.RestGroundDto;
import ua.vlad.hg.rest.dto.RestRegionDto;
import ua.vlad.hg.rest.mapper.RestCountryMapper;
import ua.vlad.hg.rest.mapper.RestGroundMapper;
import ua.vlad.hg.rest.mapper.RestRegionMapper;
import ua.vlad.hg.rest.service.RestGroundService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RestApiController {

    private final RestGroundService restGroundService;
    private final RegionService regionService;
    private final RestGroundMapper groundRestMapper;
    private final RestRegionMapper regionRestMapper;
    private final RestCountryMapper countryRestMapper;

    @GetMapping("/ground/{groundId}")
    public RestGroundDto getGround(@PathVariable Long groundId) {
        return groundRestMapper.toRestDto(restGroundService.find(groundId));
    }

    @GetMapping("/grounds")
    public List<RestGroundDto> getGrounds() {
        return restGroundService.findAll()
                .stream()
                .map(groundRestMapper::toRestDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/countries")
    public List<RestCountryDto> getCounties() {
        return regionService.findAllCountries()
                .stream()
                .map(countryRestMapper::toRestDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/region/{regionId}")
    public RestRegionDto getRegion(@PathVariable Long regionId) {
        return regionRestMapper.toRestDto(regionService.find(regionId));
    }

    @GetMapping("/regions/{countryId}")
    public List<RestRegionDto> getRegionsByCountry(@PathVariable Long countryId) {
        return regionService.findAllByCountryIdAndParentRegionIsNull(countryId)
                .stream()
                .map(regionRestMapper::toRestDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/subregions/{parentRegionId}")
    public List<RestRegionDto> getSubRegionsByParentId(@PathVariable Long parentRegionId) {
        return regionService.findAllByParentRegionId(parentRegionId)
                .stream()
                .map(regionRestMapper::toRestDto)
                .collect(Collectors.toList());
    }

}
