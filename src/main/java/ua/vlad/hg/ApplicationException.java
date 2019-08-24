package ua.vlad.hg;

import lombok.Getter;
import lombok.Setter;
import ua.vlad.hg.util.Strings;

@Getter
@Setter
public class ApplicationException extends RuntimeException {

    private Error error;

    private Object[] messageParams;

    private String message;

    public ApplicationException(Error error, Object... messageParams) {
        this.error = error;
        this.messageParams = messageParams;
        this.message = Strings.replace(error.getMessage(), messageParams);
    }

    @Override
    public String toString() {
        return getClass().getName() + ": [" + error + "]" + (message != null ? " " + message : "") ;
    }
}
