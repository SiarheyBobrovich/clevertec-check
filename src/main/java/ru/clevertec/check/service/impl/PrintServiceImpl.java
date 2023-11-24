package ru.clevertec.check.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.clevertec.check.dto.response.Printable;
import ru.clevertec.check.exception.FileCreationException;
import ru.clevertec.check.exception.PrintableException;
import ru.clevertec.check.service.PrintService;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Path;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrintServiceImpl implements PrintService {

    /**
     * Метод создаёт {@link Writer} и делегирует {@link Printable} печать
     *
     * @param filepath  Путь к файлу для печати
     * @param printable Объект класса {@link Printable}, в котором реализована логика печати в {@link Writer}
     */
    @Override
    public void printToFile(Path filepath, Printable printable) {
        log.info("Try to create file: {}", filepath.toAbsolutePath());
        File file = creteFile(filepath);

        try (Writer writer = new BufferedWriter(new FileWriter(file))) {
            printable.print(writer);

        } catch (IOException exception) {
            log.info("File not found: {}", filepath);
        }
    }

    /**
     * Метод создаёт {@link Writer} для печати в консоль и делегирует объекту {@link Printable} печать
     *
     * @param printable Объект класса {@link Printable}, в котором реализована логика печати в {@link Writer}
     */
    @Override
    public void printToConsole(Printable printable) {
        try {
            Writer writer = new OutputStreamWriter(System.out);
            printable.print(writer);
            writer.flush();

        } catch (IOException exception) {
            log.info("Can't print to console: {}", printable);
            throw new PrintableException();
        }
    }

    private File creteFile(Path path) {
        File file = path.toFile();

        try {
            if (!file.exists() && !file.createNewFile()) {
                log.info("Can't create file: {}", file);
                throw new FileCreationException();
            }

        } catch (IOException e) {
            throw new FileCreationException();

        }

        return file;
    }

}
