package ru.clevertec.check.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.clevertec.check.dto.Printable;
import ru.clevertec.check.service.PrintService;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrintServiceImpl implements PrintService {

    @Override
    @SneakyThrows
    public void printToFile(String filepath, Printable printable) {
        Path path = Paths.get(filepath);
        if (!Files.exists(path)) {
            Files.createFile(path);
        }

        try (Writer writer = new BufferedWriter(new FileWriter(path.toFile()))) {
            print(writer, printable);

        } catch (IOException exception) {
            log.info("File not found: {}", filepath);
        }
    }

    @Override
    public void printToConsole(Printable printable) {
        try (Writer writer = new OutputStreamWriter(System.out)) {
            print(writer, printable);

        } catch (IOException exception) {
            log.info("Can't print to console: {}", printable);
        }
    }

    private void print(Writer writer, Printable printable) throws IOException {
        printable.print(writer);
    }
}
