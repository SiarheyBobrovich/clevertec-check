package ru.clevertec.check.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.io.IOException;
import java.io.Writer;

@Getter
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Check implements Printable {

    Title title;
    Body body;
    Total total;

    @Override
    public void print(Writer writer) throws IOException {
        title.print(writer);
        writer.append('\n');
        body.print(writer);
        writer.append('\n');
        total.print(writer);
    }
}
