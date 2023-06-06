package ${package}.services.common.auth;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class CurrentUser {
    private UUID userId;
    private String username;
    private String email;
}
