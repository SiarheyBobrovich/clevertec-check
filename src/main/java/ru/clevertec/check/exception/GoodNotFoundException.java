package ru.clevertec.check.exception;

import ru.clevertec.check.constant.CheckConstant;

public class GoodNotFoundException extends AbstractPrintableException {

    public GoodNotFoundException() {
        super(CheckConstant.Exception.BAD_REQUEST);
    }
}
