package ru.clevertec.check.dto.response;

import java.io.IOException;
import java.io.Writer;

public interface Printable {

    void print(Writer writer) throws IOException;
}
