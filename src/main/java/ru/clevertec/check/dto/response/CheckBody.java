package ru.clevertec.check.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import ru.clevertec.check.constant.CheckConstant;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.List;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CheckBody implements Printable {

    List<OrderResponseDto> goodList;

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

        goodList.forEach(good -> print(good, writer));
    }

    public BigDecimal getTotalPrice() {
        return goodList.stream()
                .map(OrderResponseDto::getTotal)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    public BigDecimal getTotalDiscount() {
        return goodList.stream()
                .map(OrderResponseDto::getDiscount)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    @SneakyThrows
    private void print(Printable printable, Writer writer) {
        printable.print(writer);
    }
}
