package ${package}.lib.exceptions;

public class BadRequestException extends RestException {
    public BadRequestException(ErrorCode errorCode) {
        super(errorCode);
    }

    public BadRequestException(ErrorCode errorCode, String reason) {
        this(errorCode);
        setAdditionalData(ErrorParam.REASON, reason);
    }
}
