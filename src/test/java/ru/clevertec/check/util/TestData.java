package ru.clevertec.check.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import ru.clevertec.check.dto.request.Bucket;
import ru.clevertec.check.entity.Product;

import java.math.BigDecimal;
import java.util.List;

@UtilityClass
public class TestData {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @SneakyThrows
    public static List<Bucket> getInvalidBuckets() {
        return MAPPER.readValue(
                TestData.class.getResourceAsStream("/json/invalid_buckets.json"), new TypeReference<>() {
                });
    }

    @SneakyThrows
    public static List<Bucket> getValidBuckets() {
        return MAPPER.readValue(
                TestData.class.getResourceAsStream("/json/valid_buckets.json"), new TypeReference<>() {
                });
    }

    public static List<Product> buildListProducts() {
        return List.of(
                Product.builder()
                        .id(1L)
                        .description("Milk")
                        .price(BigDecimal.valueOf(1.07))
                        .quantityInStock(10)
                        .wholesaleProduct(true)
                        .build(),
                Product.builder()
                        .id(2L)
                        .description("Cream 400g")
                        .price(BigDecimal.valueOf(2.71))
                        .quantityInStock(20)
                        .wholesaleProduct(true)
                        .build());

    }
}
