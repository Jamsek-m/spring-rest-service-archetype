package ${package}.services.common.utils;

import ${package}.services.common.types.VoidFunc;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.function.Consumer;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SetterUtils {

    public static <T> void setIfNotNull(T value, Consumer<T> setter, boolean allowBlank) {
        if (value != null) {
            if (value instanceof String stringValue && !allowBlank) {
                if (!stringValue.trim().isBlank()) {
                    setter.accept(value);
                }
            } else {
                setter.accept(value);
            }
        }
    }

    public static <T> void setIfNotNull(T value, Consumer<T> setter) {
        setIfNotNull(value, setter, false);
    }

    public static <C> void setIfNull(C controlValue, VoidFunc setter) {
        setIfNull(controlValue, setter, true);
    }

    public static <C> void setIfNull(C controlValue, VoidFunc setter, boolean blankAsNull) {
        if (controlValue == null) {
            setter.call();
        } else {
            if (controlValue instanceof String stringValue && blankAsNull && (stringValue.trim().isBlank())) {
                setter.call();
            }
        }
    }
}
