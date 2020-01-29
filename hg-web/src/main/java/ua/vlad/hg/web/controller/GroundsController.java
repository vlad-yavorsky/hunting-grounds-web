package ua.vlad.hg.web.controller;

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
import ua.vlad.hg.core.entity.Ground;
import ua.vlad.hg.core.service.GroundService;
import ua.vlad.hg.core.service.RegionService;
import ua.vlad.hg.web.dto.GroundCreateDto;
import ua.vlad.hg.web.dto.GroundRemoveDto;
import ua.vlad.hg.web.dto.GroundUpdateDto;
import ua.vlad.hg.web.mapper.GroundMapper;
import ua.vlad.hg.web.mapper.RegionMapper;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/administrator/grounds")
@RequiredArgsConstructor
public class GroundsController {

    private final GroundService groundService;
    private final RegionService regionService;
    private final GroundMapper groundMapper;
    private final RegionMapper regionMapper;

    @GetMapping
    public String grounds(ModelMap model,
                          @RequestParam(name = "page", defaultValue = "0") final int page,
                          @RequestParam(name = "size", defaultValue = "50") final int size,
                          @RequestParam(name = "direction", defaultValue = "DESC")final String sortDirection,
                          @RequestParam(name = "column", defaultValue = "id") final String sortColumn) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.valueOf(sortDirection), sortColumn);
        Page<Ground> grounds = groundService.findAll(pageable);
        model.addAttribute("grounds", grounds.getContent());
        model.addAttribute("page", grounds.getPageable().getPageNumber());
        model.addAttribute("totalPages", grounds.getTotalPages());
        model.addAttribute("size", size);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("sortColumn", sortColumn);
        return "administrator/grounds/list";
    }

    @GetMapping("/item/{groundId}")
    public String item(@PathVariable final Long groundId, final ModelMap model) {
        model.addAttribute("ground", groundMapper.toDto(groundService.find(groundId)));
        return "administrator/grounds/item";
    }

    @GetMapping("/add")
    public String add(final ModelMap model) {
        model.addAttribute("ground", new GroundCreateDto());
        model.addAttribute("countries", regionService.findAllCountries());
        return "administrator/grounds/add";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("ground") final GroundCreateDto groundCreateDto, @RequestParam("kmlFile") final MultipartFile kmlFile,
                      final BindingResult result, final ModelMap model, final RedirectAttributes redirectAttributes) throws IOException {
        model.addAttribute("countries", regionService.findAllCountries());
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getFieldErrors());
        } else {
            Ground ground = groundService.create(groundMapper.toEntity(groundCreateDto), kmlFile);
            model.addAttribute("ground", ground);
            redirectAttributes.addFlashAttribute("success", "ground.add.success");
            return "redirect:/administrator/grounds/edit/" + ground.getId();
        }
        return "administrator/grounds/add";
    }

    @GetMapping("/edit/{groundId}")
    public String edit(@PathVariable final Long groundId, final ModelMap model) {
        GroundUpdateDto groundUpdateDto = groundMapper.toUpdateDto(groundService.find(groundId));
        model.addAttribute("ground", groundUpdateDto);
        model.addAttribute("countries", regionService.findAllCountries());
        model.addAttribute("regions", regionService.findAllByCountryIdAndParentRegionIsNull(groundUpdateDto.getAddress().getCountryId())
                .stream()
                .map(regionMapper::toDto)
                .collect(Collectors.toList()));
        model.addAttribute("subRegions", regionService.findAllByParentRegionId(groundUpdateDto.getAddress().getRegionId()));
        return "administrator/grounds/edit";
    }

    @PostMapping("/edit")
    public String edit(@Valid @ModelAttribute("ground") final GroundUpdateDto groundUpdateDto, @RequestParam("kmlFile") final MultipartFile kmlFile,
                       final BindingResult result, final RedirectAttributes redirectAttributes) throws IOException {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", result.getFieldErrors());
        } else {
            groundService.update(groundMapper.toEntity(groundUpdateDto), kmlFile, groundUpdateDto.isRemoveKml(), groundUpdateDto.isReverseInnerBounds());
            redirectAttributes.addFlashAttribute("success", "ground.edit.success");
        }
        return "redirect:/administrator/grounds/edit/" + groundUpdateDto.getId();
    }

    @GetMapping("/reverseinnerbounds/{groundId}")
    public String reverseInnerBounds(final HttpServletRequest request, @PathVariable final Long groundId, final RedirectAttributes redirectAttributes) {
        groundService.reverseInnerBounds(groundId);
        redirectAttributes.addFlashAttribute("success", "ground.edit.success");
        return "redirect:" + request.getHeader("Referer");
    }

    @PostMapping("/remove")
    public String remove(@ModelAttribute("ground") final GroundRemoveDto groundRemoveDto, final RedirectAttributes redirectAttributes) {
        groundService.delete(groundRemoveDto.getId());
        redirectAttributes.addFlashAttribute("success", "ground.remove.success");
        return "redirect:/administrator/grounds";
    }

}
