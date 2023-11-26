package ru.clevertec.check.exception;

public class FileCreationException extends AbstractPrintableException {

    public FileCreationException() {
        super(INTERNAL_SERVER_ERROR);
    }
}
