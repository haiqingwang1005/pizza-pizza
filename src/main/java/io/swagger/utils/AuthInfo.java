package io.swagger.utils;

import io.swagger.model.AccountRole;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Data
@Builder
public class AuthInfo {
    private String username;
    private AccountRole accountRole;

    public static AuthInfo getAccountFromAuth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getPrincipal().toString();
        String role = authentication.getAuthorities().iterator().next().getAuthority();

        return AuthInfo.builder()
                .accountRole(AccountRole.fromValue(role))
                .username(username).build();
    }
}
