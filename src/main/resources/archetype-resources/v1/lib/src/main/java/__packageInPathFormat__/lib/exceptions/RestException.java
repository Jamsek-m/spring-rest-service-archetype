package ${package}.lib.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class RestException extends RuntimeException {

    protected final HttpStatus status;

    protected final ApiError error;

    public RestException() {
        this(ErrorCode.INTERNAL_SERVER_ERROR);
    }

    public RestException(Throwable cause) {
        this(ErrorCode.INTERNAL_SERVER_ERROR, cause);
    }

    public RestException(ErrorCode errorCode) {
        super(errorCode.message());
        this.status = errorCode.status();
        this.error = createApiError(errorCode);
    }

    public RestException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.message(), cause);
        this.status = errorCode.status();
        this.error = createApiError(errorCode);
    }

    protected RestException setAdditionalData(String key, String value) {
        if (this.error.additionalData == null) {
            this.error.additionalData = new HashMap<>();
        }
        this.error.additionalData.put(key, value);
        return this;
    }

    private ApiError createApiError(ErrorCode errorCode) {
        ApiError apiError = new ApiError();
        apiError.status = errorCode.status().value();
        apiError.code = errorCode.code();
        apiError.message = errorCode.message();
        return apiError;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public ApiError getError() {
        return error;
    }

    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ApiError implements Serializable {
        protected int status;
        protected String code;
        protected String message;
        protected Map<String, String> additionalData = null;
    }

}
