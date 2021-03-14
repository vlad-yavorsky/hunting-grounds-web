package ua.vlad.hg.core.updater;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;

@Getter
@Setter
@RequiredArgsConstructor
public class BatchItemStatus {

    public enum BatchStatus {
        NOT_PROCESSED,
        SUCCESS,
        ERROR
    }

    private static final BatchItemStatus NOT_PROCESSED = new BatchItemStatus(BatchStatus.NOT_PROCESSED, Strings.EMPTY);
    private static final BatchItemStatus SUCCESS = new BatchItemStatus(BatchStatus.SUCCESS, Strings.EMPTY);

    private final BatchStatus status;
    private final String message;

    public static BatchItemStatus error(String message) {
        return new BatchItemStatus(BatchStatus.ERROR, message);
    }

    public static BatchItemStatus success() {
        return SUCCESS;
    }

    public static BatchItemStatus notProcessed() {
        return NOT_PROCESSED;
    }

}
