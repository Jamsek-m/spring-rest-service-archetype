package ${package}.lib.exceptions;

public class ValidationException extends RestException {
    public ValidationException(String entity, String field) {
        super(ErrorCode.VALIDATION_FAILED);
        setAdditionalData(ErrorParam.ENTITY, entity);
        setAdditionalData(ErrorParam.FIELD, field);
    }

    public ValidationException(Class<?> entityClass, String field) {
        this(entityClass.getSimpleName(), field);
    }

    public ValidationException(String entity, String field, String reason) {
        this(entity, field);
        setAdditionalData(ErrorParam.REASON, reason);
    }

    public ValidationException(Class<?> entityClass, String field, String reason) {
        this(entityClass.getSimpleName(), field, reason);
    }
}
