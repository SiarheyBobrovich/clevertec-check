package ru.clevertec.check.processor.impl;

import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

import java.util.List;

import static ru.clevertec.check.constant.CheckConstant.FILE_PATH;

@Slf4j
@Component
@RequiredArgsConstructor
public class MainOrderProcessorImpl implements MainOrderProcessor {

    private final ArgMapper argMapper;
    private final PrintService printService;
    private final MainOrderController orderController;

    @Override
    public void processOrder(String[] args) {
        log.info("Main args: {}", List.of(args));

        Bucket bucket = argMapper.parseArg(args);

        try {
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
        try {
            printService.printToFile(FILE_PATH, printable);
            printService.printToConsole(printable);

        } catch (FileCreationException fileCreationException) {
            printService.printExternalErrorToFile(FILE_PATH, fileCreationException);
            printService.printToConsole(printable);
        }
    }
}
