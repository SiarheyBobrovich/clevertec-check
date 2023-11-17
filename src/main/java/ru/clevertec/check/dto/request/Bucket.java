package ru.clevertec.check.dto.request;

import lombok.Builder;

import java.util.List;

@Builder
public record Bucket(List<Order> orderList,
                     DiscountCardDto cardDto) {
}
