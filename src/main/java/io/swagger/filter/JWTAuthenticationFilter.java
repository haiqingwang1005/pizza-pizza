package io.swagger.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.io.SignInRequestBody;
import io.swagger.io.UserDetailsResponse;
import io.swagger.model.Account;
import io.swagger.repository.AccountRepository;
import io.swagger.utils.JwtHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private static final Logger log = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

    private final AuthenticationManager authenticationManager;
    private final AccountRepository accountRepository;
    private final ObjectMapper objectMapper;
    private final JwtHelper jwtHelper;

    @Autowired
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager,
                                   ObjectMapper objectMapper,
                                   AccountRepository accountRepository,
                                   JwtHelper jwtHelper) {
        this.authenticationManager = authenticationManager;
        this.objectMapper = objectMapper;
        this.accountRepository = accountRepository;
        this.jwtHelper = jwtHelper;
        setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/signin", HttpMethod.POST.name()));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            SignInRequestBody body = objectMapper.readValue(req.getInputStream(), SignInRequestBody.class);
            log.info("Authenticate account " + body.toString());
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            body.getUsername(),
                            body.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            logger.error("Cannot get account info from request", e);
            throw new BadCredentialsException("Invalid request");
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) {
        String username = ((User) auth.getPrincipal()).getUsername();
        Account account = accountRepository.findByUsername(username);
        UserDetailsResponse userDetailsResponse = UserDetailsResponse.fromAccount(account);
        jwtHelper.injectJwtToResponseHeader(res, account);
        logger.info("User details:" + userDetailsResponse.toString());
        try {
            String json = objectMapper.writeValueAsString(userDetailsResponse);
            res.getWriter().write(json);
        } catch (IOException e) {
            logger.error("Cannot write account info to response", e);
        }
    }
}
