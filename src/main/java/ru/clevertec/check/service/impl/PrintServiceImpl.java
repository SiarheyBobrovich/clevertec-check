package ru.clevertec.check.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.clevertec.check.dto.response.Printable;
import ru.clevertec.check.exception.FileCreationException;
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

    @Override
    public void printToFile(Path filepath, Printable printable) {
        log.info("Try to create file: {}", filepath.toAbsolutePath());
        File file = creteFile(filepath);

        try (Writer writer = new BufferedWriter(new FileWriter(file))) {
            print(writer, printable);

        } catch (IOException exception) {
            log.info("File not found: {}", filepath);
        }
    }

    @SneakyThrows
    private File creteFile(Path path) {
        File file = path.toFile();

        if (!file.exists() && !file.createNewFile()) {
            log.info("Can't create file: {}", file);
            throw new FileCreationException();
        }

        return file;
    }

    @Override
    public void printToConsole(Printable printable) {
        try {
            Writer writer = new OutputStreamWriter(System.out);
            print(writer, printable);
            writer.flush();

        } catch (IOException exception) {
            log.info("Can't print to console: {}", printable);
        }
    }

    @Override
    public void printExternalErrorToFile(Path filePath, Printable printable) {
        printToFile(filePath, printable);
    }

    private void print(Writer writer, Printable printable) throws IOException {
        printable.print(writer);
    }
}
