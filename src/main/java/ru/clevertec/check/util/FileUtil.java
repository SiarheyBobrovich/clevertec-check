package ru.clevertec.check.util;

import lombok.SneakyThrows;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

public class FileUtil {

    public static InputStream getInputStream(String filepath) {
        return Optional.ofNullable(getInputStreamFromResources(filepath))
                .orElseGet(() -> getInputStreamFromFileSystem(filepath));
    }

    private static InputStream getInputStreamFromResources(String filepath) {
        return FileUtil.class.getResourceAsStream(filepath);
    }

    @SneakyThrows
    private static InputStream getInputStreamFromFileSystem(String filepath) {
        return Files.newInputStream(Paths.get(filepath));
    }
}
