package ru.clevertec.check.conf;

import lombok.SneakyThrows;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import ru.clevertec.check.entity.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Configuration
public class ProductConfiguration {

    @SneakyThrows
    @Bean("productFileMap")
    @ConditionalOnProperty(name = "spring.product.data.file", havingValue = "true")
    public Map<Long, Product> productFileMap(Environment environment) {
        Path path = Paths.get(Objects.requireNonNull(environment.getProperty("spring.product.data.path")));

        return Files.readAllLines(path).stream()
                .skip(1)
                .map(line -> line.split(";"))
                .map(args -> Product.builder()
                        .id(Long.parseLong(args[0]))
                        .description(args[1])
                        .price(new BigDecimal(args[2]))
                        .quantityInStock(Integer.parseInt(args[3]))
                        .wholesaleProduct(Boolean.valueOf(args[4]))
                        .build())
                .collect(Collectors.toMap(Product::getId, product -> product));
    }

    @Bean
    @ConditionalOnMissingBean(name = "productFileMap")
    public Map<Long, Product> productMap() {
        Map<Long, Product> productHashMap = new HashMap<>(20);
        productHashMap.putAll(Map.of(
                1L, new Product(1L, "Milk", BigDecimal.valueOf(1.07), 10, true),
                2L, new Product(2L, "Cream 400g", BigDecimal.valueOf(2.71), 20, true),
                3L, new Product(3L, "Yogurt 400g", BigDecimal.valueOf(2.10), 7, true),
                4L, new Product(4L, "Packed potatoes 1kg", BigDecimal.valueOf(1.47), 30, false),
                5L, new Product(5L, "Packed cabbage 1kg", BigDecimal.valueOf(1.19), 15, false),
                6L, new Product(6L, "Packed tomatoes 350g", BigDecimal.valueOf(1.60), 50, false),
                7L, new Product(7L, "Packed apples 1kg", BigDecimal.valueOf(2.78), 18, false),
                8L, new Product(8L, "Packed oranges 1kg", BigDecimal.valueOf(3.20), 12, false),
                9L, new Product(9L, "Packed bananas 1kg", BigDecimal.valueOf(1.10), 25, true),
                10L, new Product(10L, "Packed beef fillet 1kg", BigDecimal.valueOf(12.80), 7, false)));

        productHashMap.putAll(Map.of(
                11L, new Product(11L, "Packed pork fillet 1kg", BigDecimal.valueOf(8.52), 14, false),
                12L, new Product(12L, "Packed chicken breasts 1kgSour ", BigDecimal.valueOf(10.75), 18, false),
                13L, new Product(13L, "Baguette 360g", BigDecimal.valueOf(1.30), 10, true),
                14L, new Product(14L, "Drinking water 1,5l", BigDecimal.valueOf(0.80), 100, false),
                15L, new Product(15L, "Olive oil 500ml", BigDecimal.valueOf(5.30), 16, false),
                16L, new Product(16L, "Sunflower oil 1l", BigDecimal.valueOf(1.20), 12, false),
                17L, new Product(17L, "Chocolate Ritter sport 100g", BigDecimal.valueOf(1.10), 50, true),
                18L, new Product(18L, "Paulaner 0,5l", BigDecimal.valueOf(1.10), 100, false),
                19L, new Product(19L, "Whiskey Jim Beam 1l", BigDecimal.valueOf(13.99), 30, false),
                20L, new Product(20L, "Whiskey Jack Daniels 1l", BigDecimal.valueOf(17.19), 20, false)));

        productHashMap.values()
                .forEach(product -> product.setPrice(product.getPrice().setScale(2, RoundingMode.HALF_UP)));

        return productHashMap;
    }
}
