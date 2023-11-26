package ru.clevertec.check.dto.response;

import ru.clevertec.check.exception.PrintableException;

import java.io.IOException;
import java.io.Writer;

public abstract class AbstractTitlePrintable implements Printable {

    @Override
    public void print(Writer writer) {
        try {
            writer.write(getTitle());
            printBody(writer);
            writer.flush();

        } catch (IOException e) {
            throw new PrintableException();
        }
    }

    protected abstract String getTitle();

    protected abstract void printBody(Writer writer) throws IOException;
}
