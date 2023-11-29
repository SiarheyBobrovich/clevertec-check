package ru.clevertec.check.exception;

public class BalanceNotAvailableException extends AbstractPrintableException {

    public BalanceNotAvailableException() {
        super(BALANCE_NOT_AVAILABLE);
    }
}
