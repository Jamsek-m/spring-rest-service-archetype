package ${package}.lib.exceptions;

public class UnauthorizedException extends RestException {
    public UnauthorizedException() {
        super(ErrorCode.UNAUTHORIZED);
    }
}
