package io.swagger.api;

import io.swagger.io.RegisterRequestBody;
import io.swagger.io.UserDetailsResponse;
import io.swagger.model.Account;
import io.swagger.service.AccountService;
import io.swagger.annotations.ApiParam;
import io.swagger.utils.Sanitizer;
import io.swagger.utils.TokenHelper;
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
    private final Sanitizer sanitizer;
    private final TokenHelper tokenHelper;

    @Autowired
    public RegisterApiController(AccountService accountService,
                                 HttpServletRequest request,
                                 HttpServletResponse response,
                                 Sanitizer sanitizer,
                                 TokenHelper tokenHelper) {
        this.accountService = accountService;
        this.request = request;
        this.response = response;
        this.sanitizer = sanitizer;
        this.tokenHelper = tokenHelper;
    }

    @Override
    public ResponseEntity<UserDetailsResponse> registerAccount(@ApiParam(value = "Account to be registered")  @Valid @RequestBody RegisterRequestBody body) {
        try {

            Account newAccount = accountService.register( RegisterRequestBody.toAccount(body, sanitizer));
            UserDetailsResponse result = UserDetailsResponse.fromAccount(newAccount);
            result.setToken(tokenHelper.injectTokenToResponseHeader(response, newAccount));
            return new ResponseEntity<UserDetailsResponse>(result, HttpStatus.OK);
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


}
