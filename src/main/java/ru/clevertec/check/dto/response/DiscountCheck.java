package ru.clevertec.check.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import ru.clevertec.check.exception.PrintableException;

import java.io.IOException;
import java.io.Writer;

@Getter
@Setter
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DiscountCheck extends Check {

    PrintableDiscountCard printableDiscountCard;

    @Override
    public void print(Writer writer) {
        try {
            getCheckTitle().print(writer);
            writer.write('\n');
            getCheckBody().print(writer);
            writer.write('\n');
            printableDiscountCard.print(writer);
            writer.write('\n');
            getCheckTotal().print(writer);
            writer.write('\n');

        } catch (IOException e) {
            throw new PrintableException();
        }
    }
}
