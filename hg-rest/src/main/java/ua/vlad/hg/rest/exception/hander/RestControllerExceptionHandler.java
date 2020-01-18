package ua.vlad.hg.rest.exception.hander;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ua.vlad.hg.core.exception.ApplicationException;

@Slf4j
@RestControllerAdvice("ua.vlad.hg.rest.controller")
public class RestControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestApiError> applicationExceptionHandler(final Exception e) {
        log.error("Exception", e);
        int code = 0;
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if (e instanceof ApplicationException) {
            code = ((ApplicationException) e).getExceptionCode().ordinal();
            status = ((ApplicationException) e).getExceptionCode().getStatus();
        }
        return ResponseEntity
                .status(status)
                .body(RestApiError.builder()
                        .code(code)
                        .message(e.getMessage())
                        .build());
    }

}
