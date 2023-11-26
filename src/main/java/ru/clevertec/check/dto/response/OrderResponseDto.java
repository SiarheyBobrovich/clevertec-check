package ru.clevertec.check.dto.response;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.clevertec.check.entity.Product;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_UP;

/**
 * DTO for {@link Product}
 */
@Component
@Scope("prototype")
@RequiredArgsConstructor
public class OrderResponseDto implements Printable {

    private final String description;
    private final BigDecimal price;
    private final Integer count;
    private final Byte discount;

    private BigDecimal total;
    private BigDecimal totalDiscount;

    @Value("${app.constant.base.delimiter}")
    private String delimiter;

    @Value("${app.constant.base.currency}")
    private String currency;

    @Override
    public void print(Writer writer) throws IOException {
        writer.append(count.toString())
                .append(delimiter)
                .append(description)
                .append(delimiter)
                .append(price.toString())
                .append(currency)
                .append(delimiter)
                .append(getDiscount().toString())
                .append(currency)
                .append(delimiter)
                .append(price.multiply(BigDecimal.valueOf(count)).setScale(2, HALF_UP).toString())
                .append(currency)
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
