package ua.vlad.hg.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ua.vlad.hg.ApplicationProperties;
import ua.vlad.hg.service.RegionService;

@Controller
@RequiredArgsConstructor
public class PublicMapController {

    private final RegionService regionService;
    private final ApplicationProperties appProperties;

    @GetMapping("/")
    public String publicMap(ModelMap model) {
        model.addAttribute("regions", regionService.findAllByCountryIdAndParentRegionIsNull(1L));
        model.addAttribute("google_map_api_key", appProperties.getGoogleMapsApiKey());
        return "site/publicMap";
    }
}
