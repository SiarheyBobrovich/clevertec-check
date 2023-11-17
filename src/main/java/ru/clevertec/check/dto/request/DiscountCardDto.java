package ru.clevertec.check.dto.request;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record DiscountCardDto(Integer number,
                              BigDecimal balance) {

}
