package ru.clevertec.check.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record DiscountCardDto(Integer number,
                              BigDecimal balance) {

}
