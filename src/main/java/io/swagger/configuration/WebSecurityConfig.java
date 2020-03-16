package io.swagger.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.filter.JWTAuthenticationFilter;
import io.swagger.repository.AccountRepository;
import io.swagger.service.AccountService;
import io.swagger.filter.JWTAuthorizationFilter;
import io.swagger.utils.JwtHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.header.HeaderWriter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;
    private final ObjectMapper objectMapper;
    private final JwtHelper jwtHelper;

    @Autowired
    public WebSecurityConfig(AccountService accountService,
                             PasswordEncoder passwordEncoder,
                             AccountRepository accountRepository,
                             ObjectMapper objectMapper,
                             JwtHelper jwtHelper) {
        this.accountService = accountService;
        this.passwordEncoder = passwordEncoder;
        this.accountRepository = accountRepository;
        this.objectMapper = objectMapper;
        this.jwtHelper = jwtHelper;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().headers().addHeaderWriter(headerWriter()).and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/order", "/signin").authenticated()
                .antMatchers(HttpMethod.POST, "/toppings", "/pizza", "/promotion", "/pizzaSizes").access("hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.DELETE, "/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.GET, "/order", "/profile").authenticated()
                .antMatchers(HttpMethod.GET, "/admin/order").access("hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.GET).permitAll()
                .antMatchers(HttpMethod.POST, "/register").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .anyRequest().denyAll()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager(), objectMapper, accountRepository, jwtHelper))
                .addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtHelper))
                // this disables session creation on Spring Security
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

    @Bean
    protected HeaderWriter headerWriter() {
        return (httpServletRequest, httpServletResponse) -> {
            httpServletResponse.addHeader("Access-Control-Allow-Origin", "http://localhost:5000");
            httpServletResponse.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
            httpServletResponse.addHeader("Access-Control-Allow-Methods", "GET, POST, PATCH, PUT, DELETE, OPTIONS");
            httpServletResponse.addHeader("Access-Control-Allow-Credentials", "true");
        };
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(accountService).passwordEncoder(passwordEncoder);
    }
}
