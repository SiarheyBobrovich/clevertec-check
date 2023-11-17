package ru.clevertec.check.dto;

import lombok.Builder;
import ru.clevertec.check.constant.CheckConstant;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;

@Builder
public record BalancedDiscountCard(BigDecimal balance,
                                   Integer number,
                                   Byte discountPercentage) implements Printable {

    @Override
    public void print(Writer writer) throws IOException {
        writer.append(CheckConstant.DiscountCard.DISCOUNT_CARD)
                .append(CheckConstant.DELIMITER)
                .append(CheckConstant.DiscountCard.DISCOUNT_PERCENTAGE)
                .append('\n')
                .append(number.toString())
                .append(CheckConstant.DELIMITER)
                .append(discountPercentage.toString())
                .append(CheckConstant.PERCENTAGE);
    }
}
