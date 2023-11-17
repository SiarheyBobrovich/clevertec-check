package ru.clevertec.check.exception;

import ru.clevertec.check.constant.CheckConstant;
import ru.clevertec.check.dto.response.Printable;

import java.io.IOException;
import java.io.Writer;

public class FileCreationException extends Exception implements Printable {

    @Override
    public void print(Writer writer) throws IOException {
        writer.append(CheckConstant.Exception.ERROR)
                .append('\n')
                .append(CheckConstant.Exception.INTERNAL_SERVER_ERROR)
                .append('\n')
                .flush();
    }
}
