package ru.clevertec.check.conf;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import ru.clevertec.check.entity.Product;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Configuration
public class ProductConfiguration {

    @Bean
    @SneakyThrows
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
}
