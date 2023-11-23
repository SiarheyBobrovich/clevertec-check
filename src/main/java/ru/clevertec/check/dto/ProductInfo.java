package ru.clevertec.check.dto;

import lombok.Builder;
import lombok.experimental.FieldNameConstants;

import java.math.BigDecimal;

@Builder
@FieldNameConstants
public record ProductInfo(String description,
                          BigDecimal price,
                          Integer count,
                          Boolean isTradePrice) {
}