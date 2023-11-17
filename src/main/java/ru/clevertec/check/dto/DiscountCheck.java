package ru.clevertec.check.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.io.IOException;
import java.io.Writer;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DiscountCheck extends Check {

    BalancedDiscountCard balancedDiscountCard;

    public DiscountCheck(Title title,
                         Body body,
                         Total total,
                         BalancedDiscountCard balancedDiscountCard) {
        super(title, body, total);
        this.balancedDiscountCard = balancedDiscountCard;
    }

    @Override
    public void print(Writer writer) throws IOException {
        getTitle().print(writer);
        getBody().print(writer);

        balancedDiscountCard.print(writer);

        getTotal().print(writer);
    }
}
