package ru.clevertec.check.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record BalancedDiscountCard(BigDecimal balance,
                                   Integer number,
                                   Byte discountPercentage) {

    public boolean isDefault() {
        return number == null;
    }
}
