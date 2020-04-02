package io.swagger.api;

import io.swagger.io.ShopCartRequestBody;
import io.swagger.io.ShopCartResponseBody;
import io.swagger.service.ShopCartService;
import io.swagger.utils.AuthInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ShopCartApiController implements ShopCartApi {
    private ShopCartService shopCartService;

    @Autowired
    public ShopCartApiController(ShopCartService shopCartService) {
        this.shopCartService = shopCartService;
    }

    @Override
    public ResponseEntity<ShopCartResponseBody> updateShopCart(@Valid @RequestBody ShopCartRequestBody body) {
        try {
            ShopCartResponseBody res = shopCartService.updateShopCart(body, true);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (ShopCartService.ShopCartServiceException e) {
            return handleException(e);
        }
    }

    @Override
    public ResponseEntity<ShopCartResponseBody> addToShopCart(@Valid @RequestBody ShopCartRequestBody body) {
        try {
            ShopCartResponseBody res = shopCartService.updateShopCart(body, false);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (ShopCartService.ShopCartServiceException e) {
            return handleException(e);
        }
    }

    @Override
    public ResponseEntity<ShopCartResponseBody> getShopCart() {
        try {
            AuthInfo authInfo = AuthInfo.getAccountFromAuth();
            ShopCartResponseBody res = shopCartService.getShopCartByUsername(authInfo.getUsername());
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (ShopCartService.ShopCartServiceException e) {
            return handleException(e);
        }
    }

    @Override
    public ResponseEntity<ShopCartResponseBody> deleteShopCart() {
        try {
            AuthInfo authInfo = AuthInfo.getAccountFromAuth();
            ShopCartResponseBody res = shopCartService.deleteShopCart(authInfo.getUsername());
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (ShopCartService.ShopCartServiceException e) {
            return handleException(e);
        }
    }

    @Override
    public ResponseEntity<ShopCartResponseBody> getShopCartAdmin(String username) {
        try {
            ShopCartResponseBody res = shopCartService.getShopCartByUsername(username);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (ShopCartService.ShopCartServiceException e) {
            return handleException(e);
        }
    }

    @Override
    public ResponseEntity<ShopCartResponseBody> updateShopCartAdmin(@Valid @RequestBody ShopCartRequestBody body) {
        try {
            ShopCartResponseBody res = shopCartService.updateShopCart(body, true);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (ShopCartService.ShopCartServiceException e) {
            return handleException(e);
        }
    }

    @Override
    public ResponseEntity<ShopCartResponseBody> deleteShopCartAdmin(String username) {
        try {
            ShopCartResponseBody res = shopCartService.deleteShopCart(username);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (ShopCartService.ShopCartServiceException e) {
            return handleException(e);
        }
    }

    private ResponseEntity<ShopCartResponseBody> handleException(ShopCartService.ShopCartServiceException e) {
        if (e.getError() == ShopCartService.ShopCartServiceError.User_Mismatch) {
            return new ResponseEntity<>(ShopCartResponseBody.createError("Operation Unauthorized"), HttpStatus.UNAUTHORIZED);
        } else if (e.getError() == ShopCartService.ShopCartServiceError.Invalid_PizzaInfo) {
            return new ResponseEntity<>(ShopCartResponseBody.createError("Cannot find the pizza"), HttpStatus.BAD_REQUEST);
        } else if (e.getError() == ShopCartService.ShopCartServiceError.ShopCart_Not_Found) {
            return new ResponseEntity<>(ShopCartResponseBody.createError("Shop cart doesn't exit"), HttpStatus.NOT_FOUND);
        } else if (e.getError() == ShopCartService.ShopCartServiceError.Invalid_Username) {
            return new ResponseEntity<>(ShopCartResponseBody.createError("Unknown user"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ShopCartResponseBody.createError("Unknown server error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
