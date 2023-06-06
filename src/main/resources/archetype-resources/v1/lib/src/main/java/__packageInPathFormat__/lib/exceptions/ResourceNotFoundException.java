package ${package}.lib.exceptions;

import java.util.UUID;

public class ResourceNotFoundException extends RestException {
    public ResourceNotFoundException(String entity, String identifier) {
        super(ErrorCode.NOT_FOUND);
        setAdditionalData(ErrorParam.ENTITY, entity);
        setAdditionalData(ErrorParam.IDENTIFIER, identifier);
    }

    public ResourceNotFoundException(Class<?> entityClass, String identifier) {
        this(entityClass.getSimpleName(), identifier);
    }

    public ResourceNotFoundException(Class<?> entityClass, UUID identifier) {
        this(entityClass.getSimpleName(), identifier.toString());
    }

    public ResourceNotFoundException(String entity, UUID identifier) {
        this(entity, identifier.toString());
    }
}
