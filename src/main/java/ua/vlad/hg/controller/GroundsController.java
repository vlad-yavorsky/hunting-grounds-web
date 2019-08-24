package ua.vlad.hg.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.vlad.hg.domain.Ground;
import ua.vlad.hg.service.GroundService;
import ua.vlad.hg.service.RegionService;
import ua.vlad.hg.util.KmlDocument;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/administrator/grounds")
@RequiredArgsConstructor
public class GroundsController {

    private final GroundService groundService;
    private final RegionService regionService;

    @GetMapping("")
    public String grounds(ModelMap model,
                          @RequestParam(name = "page", defaultValue = "0") int page,
                          @RequestParam(name = "size", defaultValue = "50") int size,
                          @RequestParam(name = "direction", defaultValue = "DESC") String sortDirection,
                          @RequestParam(name = "column", defaultValue = "id") String sortColumn) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.valueOf(sortDirection), sortColumn);
        Page<Ground> groundsPage = groundService.findAll(pageable);
        List<Ground> groundsList = groundsPage.getContent();

        model.addAttribute("grounds", groundsList);
        model.addAttribute("page", groundsPage.getPageable().getPageNumber());
        model.addAttribute("totalPages", groundsPage.getTotalPages());
        model.addAttribute("size", size);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("sortColumn", sortColumn);

        return "administrator/grounds/list";
    }

    @GetMapping("/item/{ground}")
    public String item(@PathVariable Ground ground, ModelMap model) {
        if (ground != null) {
            model.addAttribute("ground", ground);
        } else {
            model.addAttribute("error", "ground.notFound");
        }

        return "administrator/grounds/item";
    }

    @GetMapping("/add")
    public String add(ModelMap model) {
        model.addAttribute("ground", new Ground());
        model.addAttribute("countries", regionService.findAllCountries());
//        model.addAttribute("regions", regionService.findAll());
//        model.addAttribute("subRegions", regionService.findAllSubRegions());

        return "administrator/grounds/add";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("Ground") Ground ground, @RequestParam("kmlFile") MultipartFile kml, BindingResult result, ModelMap model, RedirectAttributes redirectAttributes) {
        model.addAttribute("ground", ground);
        model.addAttribute("countries", regionService.findAllCountries());
//        model.addAttribute("regions", regionService.findAll());
//        model.addAttribute("subRegions", regionService.findAllSubRegions());

        if (result.hasErrors()) {
            model.addAttribute("errors", result.getFieldErrors());
        } else {
            try {
                if (!kml.isEmpty()) {
                    ground.setKml(new String(kml.getBytes()));
                }
                ground = groundService.save(ground);
                redirectAttributes.addFlashAttribute("success", "ground.add.success");
                return "redirect:edit/" + ground.getId();
            } catch (Exception e) {
                model.addAttribute("exception", e);
            }
        }

        return "administrator/grounds/add";
    }

    @GetMapping("/edit/{ground}")
    public String edit(@PathVariable Ground ground, ModelMap model) throws IOException {
        if (ground != null) {
            model.addAttribute("ground", ground);
            model.addAttribute("countries", regionService.findAllCountries());
            model.addAttribute("regions", regionService.findAllByCountryIdAndParentRegionIsNull(ground.getAddress().getCountry().getId()));
            model.addAttribute("subRegions", regionService.findAllByParentRegionId(ground.getAddress().getRegion().getId()));
        } else {
            model.addAttribute("error", "ground.notFound");
        }

        return "administrator/grounds/edit";
    }

    @PostMapping("/edit")
    public String edit(@Valid @ModelAttribute("Ground") Ground ground, @RequestParam("kmlFile") MultipartFile kml, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", result.getFieldErrors());
        } else {
            try {
                if (!kml.isEmpty()) {
                    ground.setKml(new String(kml.getBytes()));
                }
                groundService.save(ground);
                redirectAttributes.addFlashAttribute("success", "ground.edit.success");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("exception", e);
            }
        }

        return "redirect:edit/" + ground.getId();
    }

    @PostMapping("/remove")
    public String remove(@ModelAttribute("Ground") Ground ground, RedirectAttributes redirectAttributes) {
        try {
            groundService.deleteById(ground.getId());
            redirectAttributes.addFlashAttribute("success", "ground.remove.success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "ground.notFound");
        }

        return "redirect:/administrator/grounds";
    }

    @GetMapping("/reverseinnerbounds/{ground}")
    public String reverseInnerBounds(HttpServletRequest request, @PathVariable Ground ground, RedirectAttributes redirectAttributes) {
        if (ground != null) {
            try {
                if (!ground.getKml().isEmpty()) {
                    ground.setKml(new KmlDocument(ground.getKml()).reverseInnerBounds());
                    groundService.save(ground);
                }
                redirectAttributes.addFlashAttribute("success", "ground.edit.success");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("exception", e);
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "ground.notFound");
        }

        return "redirect:" + request.getHeader("Referer");
    }
}
