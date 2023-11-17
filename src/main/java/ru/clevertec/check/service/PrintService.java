package ru.clevertec.check.service;

import ru.clevertec.check.dto.Printable;

public interface PrintService {

    void printToFile(String filepath, Printable printable);

    void printToConsole(Printable printable);
}
