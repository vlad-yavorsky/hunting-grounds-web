package ua.vlad.hg.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RestApiError {

    private final int code;
    private final String message;

}
