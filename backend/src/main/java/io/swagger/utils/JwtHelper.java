package io.swagger.utils;

import static io.swagger.utils.SecurityConstants.EXPIRATION_TIME;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.swagger.model.Account;
import io.swagger.model.AccountRole;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtHelper {
    private static final Logger log = LoggerFactory.getLogger(JwtHelper.class);

    private static final String KEY_ROLE = "role";

    public Account parseAccountFromJwtToken(String token) {
        if (!StringUtils.isEmpty(token) && token.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            // parse the token.
            try {
                DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(
                        SecurityConstants.SECRET.getBytes())).build().verify(token.replace(
                        SecurityConstants.TOKEN_PREFIX, ""));
                Date expireAt = decodedJWT.getExpiresAt();
                if (new Date(System.currentTimeMillis()).after(expireAt)) {
                    log.error("Token has expired");
                    return null;
                }
                String username = decodedJWT.getSubject();
                String role = decodedJWT.getClaim(KEY_ROLE).asString();
                if (!StringUtils.isEmpty(username)) {
                    return Account.builder().username(username).accountRole(AccountRole.fromValue(role)).build();
                }
                log.error("Token miss necessary info");
            } catch (Exception e) {
                log.error("Cannot verify the token", e);
            }
        }
        log.error("Cannot parse account from empty token");
        return null;
    }

    public String issueJwtToken(Account account) {
        return JWT.create()
                .withSubject(account.getUsername())
                .withClaim(KEY_ROLE, account.getAccountRole().getRole())
                .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(EXPIRATION_TIME)))
                .sign(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()));
    }


}
