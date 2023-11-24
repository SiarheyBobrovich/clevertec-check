package ru.clevertec.check.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;

@Builder
public record ProductDto(

        @NotNull
        @PositiveOrZero
        Long id,

        @NotNull
        @PositiveOrZero
        Integer quantity) {
}
