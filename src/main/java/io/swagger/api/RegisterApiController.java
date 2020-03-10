package io.swagger.api;

import io.swagger.io.RegisterRequestBody;
import io.swagger.io.UserDetailsResponse;
import io.swagger.model.Account;
import io.swagger.service.AccountService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
public class RegisterApiController implements RegisterApi{
    private final AccountService accountService;
    private final HttpServletRequest request;
    private final HttpServletResponse response;

    @Autowired
    public RegisterApiController(AccountService accountService,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
        this.accountService = accountService;
        this.request = request;
        this.response = response;
    }

    @Override
    public ResponseEntity<UserDetailsResponse> registerAccount(@ApiParam(value = "Account to be registered")  @Valid @RequestBody RegisterRequestBody body) {
        try {

            Account newAccount = accountService.register(response, toAccount(body));

            return new ResponseEntity<UserDetailsResponse>(UserDetailsResponse.fromAccount(newAccount), HttpStatus.OK);
        } catch (AccountService.AuthenticationException e) {
            AccountService.AuthenticationError error = e.getAuthenticationError();
            switch (error) {
                case InvalidInput:
                case AccountTypeNotAllowed:
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                case AccountAlreadyExists:
                    return new ResponseEntity<>(HttpStatus.CONFLICT);
                default:
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    private Account toAccount(RegisterRequestBody body) {
        return Account.builder()
                .username(body.getUsername())
                .password(body.getPassword())
                .firstname(body.getFirstname())
                .lastname(body.getLastname())
                .email(body.getEmail())
                .accountRole(body.getAccountRole())
                .build();
    }


}
