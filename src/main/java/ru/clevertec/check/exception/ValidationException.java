package ru.clevertec.check.exception;

import ru.clevertec.check.constant.CheckConstant;

public class ValidationException extends AbstractPrintableException {

    public ValidationException() {
        super(CheckConstant.Exception.BAD_REQUEST);
    }
}
