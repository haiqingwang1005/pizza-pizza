package io.swagger;

import io.swagger.model.Calories;
import io.swagger.model.CreditCard;
import io.swagger.model.Order;
import io.swagger.model.Pizza;
import io.swagger.model.PizzaSize;
import io.swagger.model.PriceRule;
import io.swagger.model.Promotion;
import io.swagger.model.StoreLocation;
import io.swagger.model.Toppings;
import io.swagger.repository.CaloriesRepository;
import io.swagger.repository.CreditCardsRepository;
import io.swagger.repository.OrderRepository;
import io.swagger.repository.PizzaRepository;
import io.swagger.repository.PizzaSizesRepository;
import io.swagger.repository.PriceRuleRepository;
import io.swagger.repository.PromotionRepository;
import io.swagger.repository.StoreLocationRepository;
import io.swagger.repository.ToppingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = { "io.swagger", "io.swagger.api" , "io.swagger.configuration"})
public class Swagger2SpringBoot implements CommandLineRunner {
    
    private final ToppingsRepository toppingsRepository;
    private final CreditCardsRepository creditCardsRepository;
    private final PizzaSizesRepository pizzaSizesRepository;
    private final CaloriesRepository caloriesRepository;
    private final PriceRuleRepository priceRuleRepository;
    private final PizzaRepository pizzaRepository;
    private final OrderRepository orderRepository;
    private final PromotionRepository promotionRepository;
    private final StoreLocationRepository storeLocationRepository;

    @Autowired
    public Swagger2SpringBoot(
        ToppingsRepository toppingsRepository, CreditCardsRepository creditCardsRepository,
        PizzaSizesRepository pizzaSizesRepository, CaloriesRepository caloriesRepository,
        PriceRuleRepository priceRuleRepository, PizzaRepository pizzaRepository,
        OrderRepository orderRepository, PromotionRepository promotionRepository,
        StoreLocationRepository storeLocationRepository) {
        this.toppingsRepository = toppingsRepository;
        this.creditCardsRepository = creditCardsRepository;
        this.pizzaSizesRepository = pizzaSizesRepository;
        this.caloriesRepository = caloriesRepository;
        this.priceRuleRepository = priceRuleRepository;
        this.pizzaRepository = pizzaRepository;
        this.orderRepository = orderRepository;
        this.promotionRepository = promotionRepository;
        this.storeLocationRepository = storeLocationRepository;
    }

    @Override
    public void run(String... arg0) throws Exception {
        if (arg0.length > 0 && arg0[0].equals("exitcode")) {
            throw new ExitException();
        }
        Toppings.initialize(toppingsRepository);
        CreditCard.initialize(creditCardsRepository);
        PizzaSize.initialize(pizzaSizesRepository);
        Calories.initialize(caloriesRepository);
        PriceRule.initialize(priceRuleRepository);
        Promotion.initialize(promotionRepository);
        StoreLocation.initialize(storeLocationRepository);
        Pizza.initialize(pizzaRepository, toppingsRepository, pizzaSizesRepository);
        Order.initialize(orderRepository);
    }

    public static void main(String[] args) throws Exception {
        new SpringApplication(Swagger2SpringBoot.class).run(args);
    }

    class ExitException extends RuntimeException implements ExitCodeGenerator {
        private static final long serialVersionUID = 1L;

        @Override
        public int getExitCode() {
            return 10;
        }

    }
}
