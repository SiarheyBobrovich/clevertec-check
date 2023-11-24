package ru.clevertec.check.service;

import ru.clevertec.check.dto.response.Printable;

import java.nio.file.Path;

public interface PrintService {

    void printToFile(Path filepath, Printable printable);

    void printToConsole(Printable printable);

}
