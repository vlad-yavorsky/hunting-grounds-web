package ua.vlad.hg.rest.exception.hander;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RestApiError {

    private final int code;
    private final String message;

}
