package ${package}.lib;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Setter
@Getter
public class BaseType extends RevisionBaseType {
    private UUID id;
}
