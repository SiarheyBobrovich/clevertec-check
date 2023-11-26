package ru.clevertec.check.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Writer;
import java.math.BigDecimal;
import java.util.List;

@Component
@Scope("prototype")
@RequiredArgsConstructor
public class CheckBody extends AbstractTitlePrintable {

    @Getter
    @Value("${app.constant.body}")
    private String title;

    private final List<OrderResponseDto> goodList;

    @Override
    protected void printBody(Writer writer) {
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
