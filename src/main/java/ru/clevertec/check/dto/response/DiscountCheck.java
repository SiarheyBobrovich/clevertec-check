package ru.clevertec.check.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.io.IOException;
import java.io.Writer;

@Getter
@Setter
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DiscountCheck extends Check {

    BalancedDiscountCard balancedDiscountCard;

    @Override
    public void print(Writer writer) throws IOException {
        getCheckTitle().print(writer);
        writer.write('\n');
        getCheckBody().print(writer);
        writer.write('\n');
        balancedDiscountCard.print(writer);
        writer.write('\n');
        getCheckTotal().print(writer);
        writer.write('\n');
    }
}
