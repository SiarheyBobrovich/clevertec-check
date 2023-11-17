package ru.clevertec.check.dto;

import lombok.Builder;

@Builder
public record GoodDto(Long id,
                      Integer quantity) {
}
