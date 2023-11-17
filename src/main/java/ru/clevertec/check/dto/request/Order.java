package ru.clevertec.check.dto.request;

import lombok.Builder;

@Builder
public record Order(Long id,
                    Integer quantity) {
}
