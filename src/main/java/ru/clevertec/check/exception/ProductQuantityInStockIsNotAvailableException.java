package ru.clevertec.check.exception;

import ru.clevertec.check.constant.CheckConstant;

public class ProductQuantityInStockIsNotAvailableException extends AbstractPrintableException {

    public ProductQuantityInStockIsNotAvailableException() {
        super(CheckConstant.Exception.BAD_REQUEST);
    }
}
