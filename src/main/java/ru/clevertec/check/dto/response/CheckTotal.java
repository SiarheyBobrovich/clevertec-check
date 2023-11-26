package ru.clevertec.check.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;

@Component
@Scope("prototype")
@RequiredArgsConstructor
public class CheckTotal extends AbstractTitlePrintable {

    @Value("${app.constant.base.currency}")
    private String currency;

    @Value("${app.constant.base.delimiter}")
    private String delimiter;

    @Getter
    @Value("${app.constant.total}")
    private String title;

    private final CheckBody checkBody;

    @Override
    protected void printBody(Writer writer) throws IOException {
        BigDecimal totalPrice = checkBody.getTotalPrice();
        BigDecimal totalDiscount = checkBody.getTotalDiscount();

        writer.append(totalPrice.toString())
                .append(currency)
                .append(delimiter)
                .append(totalDiscount.toString())
                .append(currency)
                .append(delimiter)
                .append(totalPrice.subtract(totalDiscount).toString())
                .append(currency);
    }
}
