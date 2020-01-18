package ua.vlad.hg.core.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum ExceptionCode {

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User %s not found"),
    REGION_NOT_FOUND(HttpStatus.NOT_FOUND, "Region %s not found"),
    GROUND_NOT_FOUND(HttpStatus.NOT_FOUND, "Ground %s not found"),
    USERNAME_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "Username %s already exists"),
    INSUFFICIENT_PRIVILEGES(HttpStatus.FORBIDDEN, "Insufficient privileges");

    @Getter
    private final HttpStatus status;

    @Getter
    private final String message;

}
