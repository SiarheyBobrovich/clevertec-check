package ru.clevertec.check.exception;

import ru.clevertec.check.constant.CheckConstant;
import ru.clevertec.check.dto.response.Printable;

import java.io.IOException;
import java.io.Writer;

public abstract class AbstractPrintableException extends RuntimeException implements Printable {

    public AbstractPrintableException(String message) {
        super(message);
    }

    @Override
    public void print(Writer writer) {
        try {
            writer.append(CheckConstant.Exception.ERROR)
                    .append('\n')
                    .append(this.getMessage())
                    .append('\n')
                    .flush();

        } catch (IOException e) {
            throw new PrintableException();
        }
    }
}
