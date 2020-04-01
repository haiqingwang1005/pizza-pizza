package io.swagger.api;

import io.swagger.io.UserDetailsResponse;
import io.swagger.model.Account;
import io.swagger.service.AccountService;
import io.swagger.service.AccountService.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserProfileApiController implements UserProfileApi {
  private final AccountService accountService;

  @Autowired
  public UserProfileApiController(AccountService accountService) {
    this.accountService = accountService;
  }

  @Override
  public ResponseEntity<UserDetailsResponse> getUserProfile() {
    String username = getUsernameFromAuth();
    Account account;
    try {
      account = accountService.findAccountByUsername(username);
    } catch (AuthenticationException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(UserDetailsResponse.fromAccount(account), HttpStatus.OK);
  }

  private String getUsernameFromAuth() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return authentication.getPrincipal().toString();
  }
}
