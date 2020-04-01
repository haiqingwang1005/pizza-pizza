package io.swagger.service;

import io.swagger.io.ShopCartRequestBody;
import io.swagger.io.ShopCartRequestPizza;
import io.swagger.io.ShopCartResponseBody;
import io.swagger.io.ShopCartResponsePizza;
import io.swagger.model.*;
import io.swagger.repository.*;
import io.swagger.utils.AuthInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShopCartService {
    private final ShopCartRepository shopCartRepository;
    private final ToppingsRepository toppingsRepository;
    private final CrustsRepository crustsRepository;
    private final PizzaSizesRepository pizzaSizesRepository;
    private final PizzaRepository pizzaRepository;

    public enum ShopCartServiceError {
        Invalid_PizzaInfo,
        Invalid_Username,
        ShopCart_Not_Found,
        User_Mismatch;
    }

    public static class ShopCartServiceException extends Exception {
        private final ShopCartServiceError error;

        public ShopCartServiceException(ShopCartServiceError error) {
            this.error = error;
        }

        public ShopCartServiceError getError() {
            return this.error;
        }
    }

    @Autowired
    public ShopCartService(ShopCartRepository shopCartRepository,
                           ToppingsRepository toppingsRepository,
                           CrustsRepository crustsRepository,
                           PizzaSizesRepository pizzaSizesRepository,
                           PizzaRepository pizzaRepository) {
        this.shopCartRepository = shopCartRepository;
        this.toppingsRepository = toppingsRepository;
        this.pizzaSizesRepository = pizzaSizesRepository;
        this.crustsRepository = crustsRepository;
        this.pizzaRepository = pizzaRepository;
    }

    public ShopCartResponseBody getShopCartByUsername(String username) throws ShopCartServiceException {
        validateUsername(username);

        AuthInfo auth = AuthInfo.getAccountFromAuth();
        if (auth.getAccountRole() != AccountRole.ADMIN && !StringUtils.equals(username, auth.getUsername())) {
            throw new ShopCartServiceException(ShopCartServiceError.User_Mismatch);
        }

        ShopCart shopCart = shopCartRepository.findByUsername(username);
        if (shopCart == null) {
            throw new ShopCartServiceException(ShopCartServiceError.ShopCart_Not_Found);
        }
        return shopCartToResponseBody(shopCart);
    }

    public ShopCartResponseBody updateShopCart(ShopCartRequestBody body) throws ShopCartServiceException {
        AuthInfo auth = AuthInfo.getAccountFromAuth();
        if (auth.getAccountRole() != AccountRole.ADMIN && !StringUtils.equals(body.getUsername(), auth.getUsername())) {
            throw new ShopCartServiceException(ShopCartServiceError.User_Mismatch);
        }

        ShopCart shopCart = shopCartRepository.findByUsername(body.getUsername());
        if (shopCart == null) {
            shopCart = ShopCart.builder().username(body.getUsername()).build();
        }

        List<ShopCartRequestPizza> requestPizzas = body.getPizzaList();
        updateShopCartPizza(shopCart, requestPizzas);
        shopCartRepository.save(shopCart);

        return shopCartToResponseBody(shopCart);
    }

    public ShopCartResponseBody deleteShopCart(String username) throws ShopCartServiceException {
        validateUsername(username);

        AuthInfo auth = AuthInfo.getAccountFromAuth();
        if (auth.getAccountRole() != AccountRole.ADMIN && !StringUtils.equals(username, auth.getUsername())) {
            throw new ShopCartServiceException(ShopCartServiceError.User_Mismatch);
        }

        ShopCart shopCart = shopCartRepository.findByUsername(username);
        if (shopCart == null) {
            throw new ShopCartServiceException(ShopCartServiceError.ShopCart_Not_Found);
        }
        shopCartRepository.delete(shopCart);
        return shopCartToResponseBody(shopCart);
    }

    private Pizza getPizzaFromRequest(ShopCartRequestPizza pizzaInRequest) throws ShopCartServiceException {
        Pizza pizza;
        if (!StringUtils.isEmpty(pizzaInRequest.getPizzaId())) {
            pizza = pizzaRepository.findById(pizzaInRequest.getPizzaId());
        } else {
            pizza = pizzaRepository.findByCrustNameAndSizeNameAndToppingName(
                    pizzaInRequest.getCrustName(),
                    pizzaInRequest.getSizeName(),
                    pizzaInRequest.getToppingName());
        }
        if (pizza == null) {
            throw new ShopCartServiceException(ShopCartServiceError.Invalid_PizzaInfo);
        }
        return pizza;
    }

    private void updateShopCartPizza(ShopCart shopCart, List<ShopCartRequestPizza> requestPizzas) throws ShopCartServiceException {
        Map<String, ShopCartPizza> pizzasMap = new HashMap<>();
        for (ShopCartRequestPizza pizzaInRequest : requestPizzas) {
            Pizza pizza = getPizzaFromRequest(pizzaInRequest);
            ShopCartPizza pizzaInCart = ShopCartPizza.builder()
                    .pizzaNote(pizzaInRequest.getPizzaNote())
                    .number(pizzaInRequest.getNumber() < 0 ? 0 : pizzaInRequest.getNumber())
                    .build();

            ShopCartPizza pizzaInCartInMap = pizzasMap.get(pizza.getId());
            if (pizzaInCartInMap == null) {
                pizzasMap.put(pizza.getId(), pizzaInCart);
            } else {
                pizzasMap.put(pizza.getId(), pizzaInCart.merge(pizzaInCartInMap));
            }
        }
        shopCart.setPizzas(pizzasMap);
    }

    private void fulfillPizza(ShopCartResponseBody body,
                              Map<String, ShopCartPizza> pizzas) {
        double cartPrice = body.getCartPrice();

        List<ShopCartResponsePizza> shopCartResponsePizzaList = new ArrayList<>();
        for (Map.Entry<String, ShopCartPizza> entry : pizzas.entrySet()) {
            String pizzaId = entry.getKey();
            ShopCartPizza shopCartPizza = entry.getValue();
            Pizza pizza = pizzaRepository.findById(pizzaId);
            ShopCartResponsePizza shopCartResponsePizza = ShopCartResponsePizza.builder()
                    .pizza(pizza)
                    .number(shopCartPizza.getNumber())
                    .pizzaNote(shopCartPizza.getPizzaNote())
                    .build();
            shopCartResponsePizzaList.add(shopCartResponsePizza);
            cartPrice += pizza.getPrice() * shopCartPizza.getNumber();
        }
        body.setPizzas(shopCartResponsePizzaList);
        body.setCartPrice(cartPrice);
    }

    private ShopCartResponseBody shopCartToResponseBody(ShopCart shopCart) {
        ShopCartResponseBody body = ShopCartResponseBody.builder()
                .username(shopCart.getUsername())
                .cartPrice(0.0)
                .build();
        Map<String, ShopCartPizza> pizzas = shopCart.getPizzas();
        fulfillPizza(body, pizzas);
        return body;
    }

    private void validateUsername(String username) throws ShopCartServiceException {
        if (StringUtils.isEmpty(username)) {
            throw new ShopCartServiceException(ShopCartServiceError.Invalid_Username);
        }
    }
}
