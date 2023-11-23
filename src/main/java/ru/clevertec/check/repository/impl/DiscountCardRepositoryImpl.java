package ru.clevertec.check.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.clevertec.check.entity.DiscountCard;
import ru.clevertec.check.repository.DiscountCardRepository;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DiscountCardRepositoryImpl implements DiscountCardRepository {

    private final Map<Long, DiscountCard> discountCardMap;

    @Override
    public Optional<DiscountCard> findByNumber(Integer number) {
        return discountCardMap.values().stream()
                .filter(card -> Objects.equals(card.getNumber(), number))
                .findFirst();
    }
}
