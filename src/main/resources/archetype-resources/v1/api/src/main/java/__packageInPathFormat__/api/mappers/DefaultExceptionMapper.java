package ${package}.api.mappers;

import ${package}.lib.exceptions.RestException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class DefaultExceptionMapper extends ResponseEntityExceptionHandler {

    @ExceptionHandler({RestException.class})
    protected ResponseEntity<Object> handleConflict(RestException exception, WebRequest webRequest) {
        return handleExceptionInternal(exception, exception.getError(), new HttpHeaders(), exception.getStatus(), webRequest);
    }

}
