package io.swagger;

import io.swagger.model.*;
import io.swagger.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final AccountRepository accountRepository;

    @Autowired
    public Swagger2SpringBoot(
            ToppingsRepository toppingsRepository, CreditCardsRepository creditCardsRepository,
            PizzaSizesRepository pizzaSizesRepository, CaloriesRepository caloriesRepository,
            PriceRuleRepository priceRuleRepository, PizzaRepository pizzaRepository,
            OrderRepository orderRepository, PromotionRepository promotionRepository,
            StoreLocationRepository storeLocationRepository, AccountRepository accountRepository) {
        this.toppingsRepository = toppingsRepository;
        this.creditCardsRepository = creditCardsRepository;
        this.pizzaSizesRepository = pizzaSizesRepository;
        this.caloriesRepository = caloriesRepository;
        this.priceRuleRepository = priceRuleRepository;
        this.pizzaRepository = pizzaRepository;
        this.orderRepository = orderRepository;
        this.promotionRepository = promotionRepository;
        this.storeLocationRepository = storeLocationRepository;
        this.accountRepository = accountRepository;
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
        Account.initialize(accountRepository, passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new MessageDigestPasswordEncoder("MD5");
    }

    public static void main(String[] args) throws Exception {
        new SpringApplication(Swagger2SpringBoot.class).run(args);
    }

    static class ExitException extends RuntimeException implements ExitCodeGenerator {
        private static final long serialVersionUID = 1L;

        @Override
        public int getExitCode() {
            return 10;
        }

    }
}
