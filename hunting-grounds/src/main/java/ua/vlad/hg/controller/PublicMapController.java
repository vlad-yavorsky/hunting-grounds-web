package ua.vlad.hg.controller;

import ua.vlad.hg.mapper.RegionMapper;
import ua.vlad.hg.config.ApplicationProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ua.vlad.hg.service.RegionService;

@Controller
@RequiredArgsConstructor
public class PublicMapController {

    private final RegionService regionService;
    private final ApplicationProperties appProperties;
    private final RegionMapper regionMapper;

    @GetMapping("/")
    public String publicMap(final ModelMap model) {
        model.addAttribute("regions", regionService.findAllByCountryIdAndParentRegionIsNull(1L)
                .stream()
                .map(regionMapper::toDto)
                .toList());
        model.addAttribute("google_map_api_key", appProperties.getGoogleMapsApiKey());
        return "site/publicMap";
    }

}
