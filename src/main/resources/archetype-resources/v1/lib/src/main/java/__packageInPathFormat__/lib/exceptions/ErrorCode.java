package ${package}.lib.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Optional;

public enum ErrorCode {
    INTERNAL_SERVER_ERROR("error.server", "Internal server error!", HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST("error.bad-request", "Bad request!", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED("error.unauthorized", "Unauthorized!", HttpStatus.UNAUTHORIZED),
    FORBIDDEN("error.forbidden", "Forbidden!", HttpStatus.FORBIDDEN),
    NOT_FOUND("error.not-found", "Not found!", HttpStatus.NOT_FOUND),
    CONFLICT("error.conflict", "Conflict!", HttpStatus.CONFLICT),
    VALIDATION_FAILED("error.validation", "Validation failed!", HttpStatus.UNPROCESSABLE_ENTITY),
    TOO_MANY_REQUESTS("error.too-many-requests", "Too many requests!", HttpStatus.TOO_MANY_REQUESTS);

    private final String code;

    private final String message;

    private final HttpStatus status;

    ErrorCode(String code, String message, HttpStatus status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

    public String code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

    public HttpStatus status() {
        return this.status;
    }

    public static Optional<ErrorCode> fromCode(String code) {
        for (ErrorCode errorCode : values()) {
            if (errorCode.code.equals(code)) {
                return Optional.of(errorCode);
            }
        }
        return Optional.empty();
    }
}
