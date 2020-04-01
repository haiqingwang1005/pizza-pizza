package io.swagger.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import io.swagger.io.UserDetailsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Api(value = "profile")
public interface UserProfileApi {
  @ApiOperation(value = "Get the account profile.",
      nickname = "getUserProfile",
      notes = "Get the user profile.",
      response = UserDetailsResponse.class,
      tags={ "User Profiles", })
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully get the account details", response = UserDetailsResponse.class),
  })
  @RequestMapping(value = "/profile",
      produces = { "application/json" },
      method = RequestMethod.GET)
  ResponseEntity<UserDetailsResponse> getUserProfile();

}
