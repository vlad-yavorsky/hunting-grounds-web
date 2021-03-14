package ua.vlad.hg.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ua.vlad.hg.core.updater.dto.GroundCsvDto;
import ua.vlad.hg.core.updater.service.GroundCsvUpdaterService;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/administrator/batch")
@RequiredArgsConstructor
public class BatchController {

    public final GroundCsvUpdaterService groundCsvUpdaterService;

    @GetMapping("/upload")
    public String uploadGet() {
        return "administrator/batch/upload";
    }

    @PostMapping("/upload")
    public String uploadPost(@RequestParam("csvFile") final MultipartFile multipartFile,
                             final ModelMap model) throws IOException {
        if (multipartFile.isEmpty()) {
            model.addAttribute("error", "batch.file.empty");
        } else {
            List<GroundCsvDto> batch = groundCsvUpdaterService.batch(multipartFile);
            model.addAttribute("batch", batch);
            model.addAttribute("success", "batch.upload.success");
        }
        return "administrator/batch/upload";
    }

}
