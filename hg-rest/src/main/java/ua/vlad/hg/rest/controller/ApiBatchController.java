package ua.vlad.hg.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ua.vlad.hg.core.updater.service.GroundCsvUpdaterService;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiBatchController {

    @RequiredArgsConstructor
    private static class BatchResponse {
        enum BatchStatus {
            SUCCESS,
            FAILURE,
            WARNINGS
        }

        public final BatchStatus status;
        public final String exception;

        static BatchResponse success() {
            return new BatchResponse(BatchResponse.BatchStatus.SUCCESS, null);
        }

        static BatchResponse failure(String exception) {
            return new BatchResponse(BatchStatus.FAILURE, exception);
        }
    }

    private static final BatchResponse successBatchResponse = BatchResponse.success();

    private final GroundCsvUpdaterService groundCsvUpdaterService;

    @PostMapping("/batch")
    public BatchResponse batch(@RequestParam("file") List<MultipartFile> multipartFiles) {
        try {
            for (MultipartFile multipartFile : multipartFiles) {
                groundCsvUpdaterService.batch(multipartFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return BatchResponse.failure(e.getMessage());
        }
        return successBatchResponse;
    }

    @PostMapping("/batchJob")
    public BatchResponse batchJob() {
        try {
            groundCsvUpdaterService.batchJob();
        } catch (Exception e) {
            e.printStackTrace();
            return BatchResponse.failure(e.getMessage());
        }
        return successBatchResponse;
    }

}
