package ru.clevertec.check.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record GoodArgs(List<GoodDto> goodDtoList,
                       DiscountCardDto cardDto) {
}
