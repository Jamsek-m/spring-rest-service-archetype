package ${package}.lib.exceptions;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorParam {
    public static final String ENTITY = "entity";
    public static final String FIELD = "field";
    public static final String REASON = "reason";
    public static final String IDENTIFIER = "identifier";
    public static final String ROLE = "role";
    public static final String MORE_INFO = "moreInfo";
    public static final String KEY = "key";
    public static final String REQUIRED_TYPE = "requiredType";
}
