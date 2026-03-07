package ua.vlad.hg.dto;

import org.apache.logging.log4j.util.Strings;

public record BatchItemStatus(BatchStatus status, String message) {

    public enum BatchStatus {
        NOT_PROCESSED,
        SUCCESS,
        ERROR
    }

    private static final BatchItemStatus NOT_PROCESSED = new BatchItemStatus(BatchStatus.NOT_PROCESSED, Strings.EMPTY);
    private static final BatchItemStatus SUCCESS = new BatchItemStatus(BatchStatus.SUCCESS, Strings.EMPTY);

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
