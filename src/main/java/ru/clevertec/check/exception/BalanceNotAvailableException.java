package ru.clevertec.check.exception;

import ru.clevertec.check.constant.CheckConstant;

public class BalanceNotAvailableException extends AbstractPrintableException {

    public BalanceNotAvailableException() {
        super(CheckConstant.Exception.BALANCE_NOT_AVAILABLE);
    }
}
