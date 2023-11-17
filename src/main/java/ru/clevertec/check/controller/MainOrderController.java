package ru.clevertec.check.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.clevertec.check.dto.request.Bucket;
import ru.clevertec.check.dto.response.Check;
import ru.clevertec.check.dto.response.Printable;
import ru.clevertec.check.exception.FileCreationException;
import ru.clevertec.check.mapper.ArgMapper;
import ru.clevertec.check.service.OrderService;
import ru.clevertec.check.service.PrintService;

import java.util.Arrays;

import static ru.clevertec.check.constant.CheckConstant.FILE_PATH;

@Slf4j
@Component
@RequiredArgsConstructor
public class MainOrderController {

    private final ArgMapper argMapper;
    private final OrderService orderService;
    private final PrintService printService;

    @SneakyThrows
    public void processOrder(String[] args) {
        log.info("Main args: {}", Arrays.asList(args));

        Bucket bucket = argMapper.parseArg(args);

        try {
            Check check = orderService.generateCheck(bucket);
            print(check);

        } catch (Exception e) {
            if (e instanceof Printable printable) {
                print(printable);
            } else {
                log.error("Unknown exception", e);
            }
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
