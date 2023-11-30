package ru.clevertec.check.conf;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import ru.clevertec.check.entity.Product;
import ru.clevertec.check.util.FileUtil;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class ProductConfiguration {

    @Bean
    @SneakyThrows
    public Map<Long, Product> productFileMap(Environment environment) {
        try (InputStream resource = FileUtil.getInputStream(environment.getProperty("app.product.data.load.file"))) {
            String fileAsString = new String(resource.readAllBytes());

            return Arrays.stream(fileAsString.split("\n"))
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
}
