package ru.clevertec.check.exception;

import ru.clevertec.check.constant.CheckConstant;

public class PrintableException extends AbstractPrintableException {

    public PrintableException() {
        super(CheckConstant.Exception.INTERNAL_SERVER_ERROR);
    }
}
