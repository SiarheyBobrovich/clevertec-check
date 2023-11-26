package ru.clevertec.check.exception;

public class PrintableException extends AbstractPrintableException {

    public PrintableException() {
        super(INTERNAL_SERVER_ERROR);
    }
}
