package io.swagger.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.Account;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static io.swagger.utils.SecurityConstants.EXPIRATION_TIME;

@Component
public class TokenHelper {
    private static final Logger log = LoggerFactory.getLogger(TokenHelper.class);

    private final JwtHelper jwtHelper;
    private final EncryptHelper encryptHelper;
    private ObjectMapper objectMapper;
    @Autowired
    public TokenHelper(JwtHelper jwtHelper, EncryptHelper encryptHelper, ObjectMapper objectMapper) {
        this.jwtHelper = jwtHelper;
        this.encryptHelper = encryptHelper;
        this.objectMapper = objectMapper;
    }

    public void injectTokenToResponseHeader(HttpServletResponse res,
                                            Account account) {
        try {
            String jsonString = objectMapper.writeValueAsString(account);
            String token = encryptHelper.encrypt(jsonString, SecurityConstants.SECRET);
            log.info("token: " + token);
            Cookie cookie = new Cookie("token", token);
            cookie.setPath("/");
            cookie.setDomain(".herokuapp.com");
            cookie.setMaxAge(EXPIRATION_TIME);
            res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
            res.addCookie(cookie);
        } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException e) {
            log.error("Cannot generate the token", e);
        } catch (JsonProcessingException e) {
            log.error("Cannot serialize account to token", e);
        }
    }

    public Account parseAccountFromRequestHeader(HttpServletRequest req) {
        String token = req.getHeader(SecurityConstants.HEADER_STRING);
        return parseAccountFromToken(token);
    }

    private Account parseAccountFromToken(String token) {
        if (!StringUtils.isEmpty(token) && token.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            try {
                String json = encryptHelper.decrypt(token.replace(SecurityConstants.TOKEN_PREFIX, ""), SecurityConstants.SECRET);
                return objectMapper.readValue(json, Account.class);
            } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException e) {
                log.error("Cannot parse the token", e);
            } catch (Exception e) {
                log.error("Cannot deserialize account from token", e);
            }
        }

        return null;
    }
}
