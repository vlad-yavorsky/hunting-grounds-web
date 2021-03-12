package ua.vlad.hg.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ua.vlad.hg.core.updater.GroundCsvUpdaterService;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BatchController {

    @RequiredArgsConstructor
    private static class BatchResponse {

        enum BatchStatus {
            SUCCESS,
            FAILURE
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

    @PostMapping("/batch/create")
    public BatchResponse create(@RequestParam("file") List<MultipartFile> multipartFiles) {
        try {
            for (MultipartFile multipartFile : multipartFiles) {
                groundCsvUpdaterService.create(multipartFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return BatchResponse.failure(e.getMessage());
        }
        return successBatchResponse;
    }

    @PostMapping("/batch/update")
    public BatchResponse update(@RequestParam("file") List<MultipartFile> multipartFiles) {
        try {
            for (MultipartFile multipartFile : multipartFiles) {
                groundCsvUpdaterService.update(multipartFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return BatchResponse.failure(e.getMessage());
        }
        return successBatchResponse;
    }

    @PostMapping("/batch/createJob")
    public BatchResponse runCreateJob() {
        try {
            groundCsvUpdaterService.runCreateJob();
        } catch (Exception e) {
            e.printStackTrace();
            return BatchResponse.failure(e.getMessage());
        }
        return successBatchResponse;
    }

    @PostMapping("/batch/updateJob")
    public BatchResponse runUpdateJob() {
        try {
            groundCsvUpdaterService.runUpdateJob();
        } catch (Exception e) {
            e.printStackTrace();
            return BatchResponse.failure(e.getMessage());
        }
        return successBatchResponse;
    }

}
