package ru.clevertec.check.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.experimental.FieldDefaults;
import ru.clevertec.check.constant.CheckConstant;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.List;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Body implements Printable {

    List<GoodResponseDto> goodList;

    @Override
    public void print(Writer writer) throws IOException {
        writer.append(CheckConstant.Body.QTY)
                .append(CheckConstant.DELIMITER)
                .append(CheckConstant.Body.DESCRIPTION)
                .append(CheckConstant.DELIMITER)
                .append(CheckConstant.Body.PRICE)
                .append(CheckConstant.DELIMITER)
                .append(CheckConstant.Body.DISCOUNT)
                .append(CheckConstant.DELIMITER)
                .append(CheckConstant.Body.TOTAL)
                .append('\n');

        for (GoodResponseDto good : goodList) {
            good.print(writer);
            writer.write('\n');
        }
    }

    public BigDecimal getTotalPrice() {
        return goodList.stream()
                .map(GoodResponseDto::getTotal)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    public BigDecimal getTotalDiscount() {
        return goodList.stream()
                .map(GoodResponseDto::getDiscount)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }
}
