package ru.clevertec.check.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.experimental.FieldDefaults;
import ru.clevertec.check.constant.CheckConstant;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Total implements Printable {

    BigDecimal totalPrice;
    BigDecimal totalDiscount;
    BigDecimal totalWithDiscount;

    @Override
    public void print(Writer writer) throws IOException {
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
