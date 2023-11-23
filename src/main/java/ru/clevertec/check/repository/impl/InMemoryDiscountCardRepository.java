package ru.clevertec.check.repository.impl;

import org.springframework.stereotype.Repository;
import ru.clevertec.check.entity.DiscountCard;
import ru.clevertec.check.repository.DiscountCardRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Repository
public class InMemoryDiscountCardRepository implements DiscountCardRepository {

    private final Map<Long, DiscountCard> discountCardMap = new HashMap<>();

    {
        discountCardMap.putAll(Map.of(
                1L, new DiscountCard(1L, 1111, (byte) 3, null),
                2L, new DiscountCard(2L, 2222, (byte) 3, null),
                3L, new DiscountCard(3L, 3334, (byte) 4, null),
                4L, new DiscountCard(4L, 4444, (byte) 5, null)
        ));
    }

    @Override
    public Optional<DiscountCard> findByNumber(Integer number) {
        return discountCardMap.values().stream()
                .filter(card -> Objects.equals(card.getNumber(), number))
                .findFirst();
    }
}
