package ru.clevertec.check.exception;

import ru.clevertec.check.constant.CheckConstant;

public class GoodQuantityInStockIsNotAvailableException extends AbstractPrintableException {

    public GoodQuantityInStockIsNotAvailableException() {
        super(CheckConstant.Exception.BAD_REQUEST);
    }
}
