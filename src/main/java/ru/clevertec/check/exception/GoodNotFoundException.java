package ru.clevertec.check.exception;

public class GoodNotFoundException extends AbstractPrintableException {

    public GoodNotFoundException() {
        super(BAD_REQUEST);
    }
}
