package io.swagger.api;

import io.swagger.annotations.*;
import io.swagger.io.ShopCartRequestBody;
import io.swagger.io.ShopCartResponseBody;
import io.swagger.model.ShopCart;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Api(value = "cart")
public interface ShopCartApi {
    @ApiOperation(value = "Update pizza or sides or drinks in the shop cart. " +
            "The username in request body must match that in the token header. " +
            "If the items don't exit in the cart, it will add the items. If the items exist in the cart, it updates the number",
            nickname = "updateShopCart", notes = "Update items to the shop cart",
            response = ShopCartResponseBody.class,
            tags = {"Shop Cart Operation",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "item updated",
                    response = ShopCart.class),
            @ApiResponse(code = 400, message = "invalid input, object invalid"),
            @ApiResponse(code = 401, message = "Not authorized")})
    @RequestMapping(value = "/cart",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.PUT)
    ResponseEntity<ShopCartResponseBody> updateShopCart(@ApiParam(value = "items to be updated") @Valid @RequestBody ShopCartRequestBody body);

    @ApiOperation(value = "Update pizza or sides or drinks in the shop cart. " +
            "The username in request body must match that in the token header. " +
            "If the items don't exit in the cart, it will add the items. If the items exist in the cart, it updates the number",
            nickname = "updateShopCart", notes = "Update items to the shop cart",
            response = ShopCartResponseBody.class,
            tags = {"Shop Cart Operation",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "item created",
                    response = ShopCart.class),
            @ApiResponse(code = 400, message = "invalid input, object invalid"),
            @ApiResponse(code = 401, message = "Not authorized")})
    @RequestMapping(value = "/cart",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<ShopCartResponseBody> addToShopCart(@ApiParam(value = "items to be updated") @Valid @RequestBody ShopCartRequestBody body);


    @ApiOperation(value = "Get shop cart items of the username in the token.",
            nickname = "getShopCart",
            notes = "Get shop cart items by username ",
            response = ShopCartResponseBody.class,
            tags = {"Shop Cart Operation",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "search results matching criteria",
                    response = ShopCart.class),
            @ApiResponse(code = 404, message = "not found.")})
    @RequestMapping(value = "/cart",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<ShopCartResponseBody> getShopCart();

    @ApiOperation(value = "Delete the shop cart item by the username in the token.",
            nickname = "deleteShopCart", notes = "Delete shop cart item by username ",
            response = ShopCartResponseBody.class,
            tags = {"Shop Cart Operation",})
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "successfully delete the item. return all the item in the shop cart.",
                    response = ShopCartResponseBody.class),
            @ApiResponse(code = 400,
                    message = "bad input parameter")})
    @RequestMapping(value = "/cart",
            produces = {"application/json"},
            method = RequestMethod.DELETE)
    ResponseEntity<ShopCartResponseBody> deleteShopCart();

    @ApiOperation(value = "Get shop cart by username, which is required.",
            nickname = "getShopChartAdmin",
            notes = "Get shop cart items by username",
            response = ShopCartResponseBody.class,
            tags = {"Admin Shop Cart Operation",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "search results matching criteria",
                    response = ShopCartResponseBody.class),
            @ApiResponse(code = 400, message = "bad input parameter")})
    @RequestMapping(value = "/admin/cart",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<ShopCartResponseBody> getShopCartAdmin(@ApiParam(value = "username", required = true) @RequestParam(value = "username") String username);

    @ApiOperation(value = "Update item to shop cart.",
            nickname = "updateShopCartAdmin",
            notes = "update shop cart with new items. If the pizza exits in the carts, it update the pizzas. Otherwise it added pizzas to the carts",
            response = ShopCart.class,
            tags = {"Admin Shop Cart Operation",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "item updated",
                    response = ShopCartResponseBody.class),
            @ApiResponse(code = 400, message = "invalid input, object invalid")})
    @RequestMapping(value = "/admin/cart",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<ShopCartResponseBody> updateShopCartAdmin(@ApiParam(value = "items to be added") @Valid @RequestBody ShopCartRequestBody body);

    @ApiOperation(value = "Delete shop cart items by username",
            nickname = "deleteShopCartAdmin",
            notes = "Delete shop cart item by username ",
            response = ShopCartResponseBody.class,
            tags = {"Admin Shop Cart Operation",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successfully delete the item, return the deleted shop cart.",
                    response = ShopCartResponseBody.class),
            @ApiResponse(code = 400, message = "bad input parameter")})
    @RequestMapping(value = "/admin/cart",
            produces = {"application/json"},
            method = RequestMethod.DELETE)
    ResponseEntity<ShopCartResponseBody> deleteShopCartAdmin(@ApiParam(value = "username", required = true) @RequestParam(value = "username") String username);

}
