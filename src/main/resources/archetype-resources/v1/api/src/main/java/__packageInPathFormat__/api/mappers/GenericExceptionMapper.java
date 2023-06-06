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
@Order(Ordered.HIGHEST_PRECEDENCE + 100)
public class GenericExceptionMapper extends ResponseEntityExceptionHandler {

    @ExceptionHandler({Exception.class})
    protected ResponseEntity<Object> handleConflict(Exception exception, WebRequest webRequest) {
        exception.printStackTrace();
        RestException restException = new RestException(exception);
        return handleExceptionInternal(restException, restException.getError(), new HttpHeaders(), restException.getStatus(), webRequest);
    }

}
