package ru.clevertec.check.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import ru.clevertec.check.dto.request.Bucket;

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
}
