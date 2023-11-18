package ru.clevertec.check.dto.response;

import lombok.Builder;
import lombok.SneakyThrows;
import ru.clevertec.check.constant.CheckConstant;

import java.io.Writer;
import java.math.BigDecimal;

@Builder
public record BalancedDiscountCard(BigDecimal balance,
                                   Integer number,
                                   Byte discountPercentage) implements Printable {

    @Override
    @SneakyThrows
    public void print(Writer writer) {
        writer.append(CheckConstant.DiscountCard.DISCOUNT_CARD)
                .append(CheckConstant.DELIMITER)
                .append(CheckConstant.DiscountCard.DISCOUNT_PERCENTAGE)
                .append('\n')
                .append(number.toString())
                .append(CheckConstant.DELIMITER)
                .append(discountPercentage.toString())
                .append(CheckConstant.PERCENTAGE)
                .append('\n');
    }

    public boolean isDefault() {
        return number == null;
    }
}
