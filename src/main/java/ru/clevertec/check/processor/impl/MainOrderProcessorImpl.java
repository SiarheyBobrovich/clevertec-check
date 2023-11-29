package ru.clevertec.check.processor.impl;

import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.clevertec.check.controller.MainOrderController;
import ru.clevertec.check.dto.request.Bucket;
import ru.clevertec.check.dto.response.Check;
import ru.clevertec.check.dto.response.Printable;
import ru.clevertec.check.exception.AbstractPrintableException;
import ru.clevertec.check.exception.FileCreationException;
import ru.clevertec.check.exception.ValidationException;
import ru.clevertec.check.mapper.ArgMapper;
import ru.clevertec.check.processor.MainOrderProcessor;
import ru.clevertec.check.service.PrintService;
import ru.clevertec.check.validation.Validator;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class MainOrderProcessorImpl implements MainOrderProcessor {

    private final ArgMapper argMapper;
    private final PrintService printService;
    private final MainOrderController orderController;
    private final Validator<String[]> validator;

    /**
     * Path to the saved file
     */
    @Value("${app.product.data.save.file}")
    private String filePath;

    /**
     * Запускает процесс обработки заказа из аргументов
     *
     * @param args Аргументы заказа
     */
    @Override
    public void processOrder(String[] args) {
        log.info("Main args: {}", List.of(args));

        try {
            validator.validate(args);
            Bucket bucket = argMapper.parseArg(args);
            Check check = orderController.createCheck(bucket);
            print(check);

        } catch (AbstractPrintableException printable) {
            print(printable);

        } catch (ConstraintViolationException e) {
            print(new ValidationException());

        } catch (Exception e) {
            log.error("Unknown exception", e);
        }
    }

    private void print(Printable printable) {
        Path path = Paths.get(filePath);
        try {
            printService.printToFile(path, printable);
            printService.printToConsole(printable);

        } catch (FileCreationException fileCreationException) {
            printService.printToFile(path, fileCreationException);
            printService.printToConsole(printable);
        }
    }
}
