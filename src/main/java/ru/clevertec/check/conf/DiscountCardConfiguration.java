package ru.clevertec.check.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.clevertec.check.entity.DiscountCard;

import java.util.Map;

@Configuration
public class DiscountCardConfiguration {

    @Bean
    public Map<Long, DiscountCard> discountCardMap() {
        return Map.of(
                1L, new DiscountCard(1L, 1111, (byte) 3, null),
                2L, new DiscountCard(2L, 2222, (byte) 3, null),
                3L, new DiscountCard(3L, 3334, (byte) 4, null),
                4L, new DiscountCard(4L, 4444, (byte) 5, null)
        );
    }
}
