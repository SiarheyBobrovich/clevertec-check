package ru.clevertec.check.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.io.IOException;
import java.io.Writer;

@Getter
@SuperBuilder
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Check implements Printable {

    CheckTitle checkTitle;
    CheckBody checkBody;
    CheckTotal checkTotal;

    @Override
    public void print(Writer writer) throws IOException {
        checkTitle.print(writer);
        writer.append('\n');
        checkBody.print(writer);
        writer.append('\n');
        checkTotal.print(writer);
        writer.append('\n');
    }
}
