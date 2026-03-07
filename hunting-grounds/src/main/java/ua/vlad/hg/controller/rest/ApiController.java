package ua.vlad.hg.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.vlad.hg.service.RegionService;
import ua.vlad.hg.dto.rest.RestCountryDto;
import ua.vlad.hg.dto.rest.RestGroundDto;
import ua.vlad.hg.dto.rest.RestRegionDto;
import ua.vlad.hg.mapper.rest.RestCountryMapper;
import ua.vlad.hg.mapper.rest.RestGroundMapper;
import ua.vlad.hg.mapper.rest.RestRegionMapper;
import ua.vlad.hg.service.RestGroundService;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {

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
                .toList();
    }

    @GetMapping("/countries")
    public List<RestCountryDto> getCounties() {
        return regionService.findAllCountries()
                .stream()
                .map(countryRestMapper::toRestDto)
                .toList();
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
                .toList();
    }

    @GetMapping("/subregions/{parentRegionId}")
    public List<RestRegionDto> getSubRegionsByParentId(@PathVariable Long parentRegionId) {
        return regionService.findAllByParentRegionId(parentRegionId)
                .stream()
                .map(regionRestMapper::toRestDto)
                .toList();
    }

}
