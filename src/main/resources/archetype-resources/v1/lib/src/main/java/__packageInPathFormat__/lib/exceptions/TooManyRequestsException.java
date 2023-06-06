package ${package}.lib.exceptions;

public class TooManyRequestsException extends RestException {
    public TooManyRequestsException() {
        super(ErrorCode.TOO_MANY_REQUESTS);
    }
}
