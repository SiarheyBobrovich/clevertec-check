package ru.clevertec.check.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.clevertec.check.dto.BalancedDiscountCard;

import java.io.IOException;
import java.io.Writer;

@Component
@Scope("prototype")
@RequiredArgsConstructor
public class PrintableDiscountCard extends AbstractTitlePrintable {

    private final BalancedDiscountCard balancedDiscountCard;

    @Value("${app.constant.base.percentage}")
    private String percentage;

    @Value("${app.constant.base.delimiter}")
    private String delimiter;

    @Getter
    @Value("${app.constant.discount-card}")
    private String title;

    @Override
    protected void printBody(Writer writer) throws IOException {
        writer.append(balancedDiscountCard.number().toString())
                .append(delimiter)
                .append(balancedDiscountCard.discountPercentage().toString())
                .append(percentage)
                .append('\n');
    }
}
