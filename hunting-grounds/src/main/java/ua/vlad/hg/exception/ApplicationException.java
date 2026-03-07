package ua.vlad.hg.exception;

import lombok.Getter;

public class ApplicationException extends RuntimeException {

    @Getter
    private final ExceptionCode exceptionCode;

    public ApplicationException(ExceptionCode exceptionCode, Object... messageParams) {
        super(String.format(exceptionCode.getMessage(), messageParams));
        this.exceptionCode = exceptionCode;
    }

}
