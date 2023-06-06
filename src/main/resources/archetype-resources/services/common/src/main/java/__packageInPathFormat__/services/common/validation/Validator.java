package ${package}.services.common.validation;

import ${package}.lib.exceptions.ValidationError;
import ${package}.lib.exceptions.ValidationException;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class Validator {

    public static final String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    public void assertNotNull(Object value, String entity, String field) throws ValidationException {
        if (value == null) {
            throw new ValidationException(entity, field, ValidationError.REQUIRED);
        }
    }

    public void assertNotNull(Object value, Class<?> entityClass, String field) throws ValidationException {
        assertNotNull(value, entityClass.getSimpleName(), field);
    }

    public void assertNotEmpty(Collection<?> collection, String entity, String field) throws ValidationException {
        assertNotNull(collection, entity, field);
        if (collection.isEmpty()) {
            throw new ValidationException(entity, field, ValidationError.REQUIRED);
        }
    }

    public void assertNotEmpty(Collection<?> collection, Class<?> entityClass, String field) throws ValidationException {
        assertNotEmpty(collection, entityClass.getSimpleName(), field);
    }

    public void assertNotBlank(String value, String entity, String field) throws ValidationException {
        if (value == null || value.trim().isEmpty()) {
            throw new ValidationException(entity, field, ValidationError.REQUIRED);
        }
    }

    public void assertNotBlank(String value, Class<?> entityClass, String field) throws ValidationException {
        assertNotBlank(value, entityClass.getSimpleName(), field);
    }

    public void assertRegex(String value, String regex, String entity, String field) throws ValidationException {
        if (!value.matches(regex)) {
            throw new ValidationException(entity, field, ValidationError.PATTERN_MISMATCH);
        }
    }

    public void assertRegex(String value, String regex, Class<?> entityClass, String field) throws ValidationException {
        assertRegex(value, regex, entityClass.getSimpleName(), field);
    }

    public void assertEmail(String value, String entity, String field) throws ValidationException {
        assertNotBlank(value, entity, field);
        assertRegex(value, EMAIL_REGEX, entity, field);
    }

    public void assertEmail(String value, Class<?> entityClass, String field) throws ValidationException {
        assertEmail(value, entityClass.getSimpleName(), field);
    }
}
