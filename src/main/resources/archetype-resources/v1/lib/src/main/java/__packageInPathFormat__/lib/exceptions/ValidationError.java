package ${package}.lib.exceptions;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidationError {
    public static final String REQUIRED = "REQUIRED";
    public static final String PATTERN_MISMATCH = "PATTERN_MISMATCH";
}
