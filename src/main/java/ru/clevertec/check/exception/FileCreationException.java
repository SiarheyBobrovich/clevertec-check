package ru.clevertec.check.exception;

import ru.clevertec.check.constant.CheckConstant;

public class FileCreationException extends AbstractPrintableException {

    public FileCreationException() {
        super(CheckConstant.Exception.INTERNAL_SERVER_ERROR);
    }
}
