package ru.clevertec.check.service;

import ru.clevertec.check.dto.response.Printable;
import ru.clevertec.check.exception.FileCreationException;

import java.nio.file.Path;

public interface PrintService {

    void printToFile(Path filepath, Printable printable) throws FileCreationException;

    void printToConsole(Printable printable);

    void printExternalErrorToFile(Path filePath, Printable printable);
}
