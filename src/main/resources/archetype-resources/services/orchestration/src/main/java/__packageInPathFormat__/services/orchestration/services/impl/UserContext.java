package ${package}.services.orchestration.services.impl;

import ${package}.lib.exceptions.UnauthorizedException;
import ${package}.services.common.auth.CurrentUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.UUID;

public abstract class UserContext {

    protected CurrentUser getCurrentUser() {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof Jwt jwt) {
            return CurrentUser.builder()
                .userId(UUID.fromString(jwt.getSubject()))
                .username(jwt.getClaimAsString("preferred_username"))
                .email(jwt.getClaimAsString("email"))
                .build();
        }
        throw new UnauthorizedException();
    }

    protected UUID getCurrentUserId() {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof Jwt jwt) {
            return UUID.fromString(jwt.getSubject());
        }
        throw new UnauthorizedException();
    }

}
