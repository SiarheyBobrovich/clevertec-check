package ru.clevertec.check.dto.response;

import lombok.Builder;
import ru.clevertec.check.constant.CheckConstant;
import ru.clevertec.check.entity.Good;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_UP;

/**
 * DTO for {@link Good}
 */
@Builder
public class OrderResponseDto implements Printable {

    private final String description;
    private final BigDecimal price;
    private final Integer count;
    private final Byte discount;

    private BigDecimal total;
    private BigDecimal totalDiscount;

    @Override
    public void print(Writer writer) throws IOException {
        writer.append(count.toString())
                .append(CheckConstant.DELIMITER)
                .append(description)
                .append(CheckConstant.DELIMITER)
                .append(price.toString())
                .append(CheckConstant.CURRENCY)
                .append(CheckConstant.DELIMITER)
                .append(getDiscount().toString())
                .append(CheckConstant.CURRENCY)
                .append(CheckConstant.DELIMITER)
                .append(price.multiply(BigDecimal.valueOf(count)).setScale(2, HALF_UP).toString())
                .append(CheckConstant.CURRENCY)
                .append('\n');
    }

    public BigDecimal getDiscount() {
        if (totalDiscount == null) {
            totalDiscount = getTotal().multiply(BigDecimal.valueOf(discount))
                    .multiply(BigDecimal.valueOf(0.01))
                    .setScale(2, HALF_UP);
        }

        return totalDiscount;
    }

    public BigDecimal getTotal() {
        if (total == null) {
            total = price.multiply(BigDecimal.valueOf(count))
                    .setScale(2, HALF_UP);
        }

        return total;
    }
}
