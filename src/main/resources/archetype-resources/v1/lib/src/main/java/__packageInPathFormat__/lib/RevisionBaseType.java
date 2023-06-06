package ${package}.lib;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class RevisionBaseType {
    private Instant createdAt;
    private Instant updatedAt;
}
