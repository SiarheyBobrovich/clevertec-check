package ru.clevertec.check.exception;

import ru.clevertec.check.dto.response.Printable;

import java.io.IOException;
import java.io.Writer;

public abstract class AbstractPrintableException extends RuntimeException implements Printable {

    protected static final String ERROR = "ERROR";
    protected static final String BAD_REQUEST = "BAD REQUEST";
    protected static final String INTERNAL_SERVER_ERROR = "INTERNAL SERVER ERROR";
    protected static final String BALANCE_NOT_AVAILABLE = "NOT ENOUGH MONEY";

    public AbstractPrintableException(String message) {
        super(message);
    }

    @Override
    public void print(Writer writer) {
        try {
            writer.append(ERROR)
                    .append('\n')
                    .append(this.getMessage())
                    .append('\n')
                    .flush();

        } catch (IOException e) {
            throw new PrintableException();
        }
    }
}
