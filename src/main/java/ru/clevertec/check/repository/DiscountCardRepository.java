package ru.clevertec.check.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.clevertec.check.entity.DiscountCard;

import java.util.Optional;

public interface DiscountCardRepository extends JpaRepository<DiscountCard, Long> {

    Optional<DiscountCard> findByNumber(Integer number);
}
