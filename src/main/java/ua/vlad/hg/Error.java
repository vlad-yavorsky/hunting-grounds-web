package ua.vlad.hg;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Error {

    INVALID_USERNAME_OR_PASSWORD("Invalid username or password"),
    USER_HAVE_NO_ACCESS_TO_ADMIN_SECTION("You do not have access to the Administrator section of this site");

    private final String message;
}
