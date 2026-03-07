package ua.vlad.hg.dto;

public record BatchResponse(BatchStatus status, String exception) {

    enum BatchStatus {
        SUCCESS,
        FAILURE,
        WARNINGS
    }

    private static final BatchResponse SUCCESS = new BatchResponse(BatchStatus.SUCCESS, null);

    public static BatchResponse success() {
        return SUCCESS;
    }

    public static BatchResponse failure(String exception) {
        return new BatchResponse(BatchStatus.FAILURE, exception);
    }
}
