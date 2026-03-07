package ua.vlad.hg.controller.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ua.vlad.hg.dto.BatchResponse;
import ua.vlad.hg.service.GroundCsvUpdaterService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiBatchController {

    private final GroundCsvUpdaterService groundCsvUpdaterService;

    @PostMapping("/batch")
    public BatchResponse batch(@RequestParam("file") List<MultipartFile> multipartFiles) {
        try {
            for (MultipartFile multipartFile : multipartFiles) {
                groundCsvUpdaterService.batch(multipartFile);
            }
        } catch (Exception e) {
            log.error("Exception", e);
            return BatchResponse.failure(e.getMessage());
        }
        return BatchResponse.success();
    }

    @PostMapping("/batchJob")
    public BatchResponse batchJob() {
        try {
            groundCsvUpdaterService.batchJob();
        } catch (Exception e) {
            log.error("Exception", e);
            return BatchResponse.failure(e.getMessage());
        }
        return BatchResponse.success();
    }

}
