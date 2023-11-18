package ru.clevertec.check.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import ru.clevertec.check.constant.CheckConstant;

import java.io.Writer;
import java.math.BigDecimal;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CheckTotal implements Printable {

    BigDecimal totalPrice;
    BigDecimal totalDiscount;
    BigDecimal totalWithDiscount;

    @Override
    @SneakyThrows
    public void print(Writer writer) {
        writer.append(CheckConstant.Total.TOTAL_PRICE)
                .append(CheckConstant.DELIMITER)
                .append(CheckConstant.Total.TOTAL_DISCOUNT)
                .append(CheckConstant.DELIMITER)
                .append(CheckConstant.Total.TOTAL_WITH_DISCOUNT)
                .append('\n')
                .append(totalPrice.toString())
                .append(CheckConstant.CURRENCY)
                .append(CheckConstant.DELIMITER)
                .append(totalDiscount.toString())
                .append(CheckConstant.CURRENCY)
                .append(CheckConstant.DELIMITER)
                .append(totalWithDiscount.toString())
                .append(CheckConstant.CURRENCY);
    }
}
