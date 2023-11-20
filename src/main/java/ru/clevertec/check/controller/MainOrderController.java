package ru.clevertec.check.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import ru.clevertec.check.dto.request.Bucket;
import ru.clevertec.check.dto.response.Check;
import ru.clevertec.check.service.OrderService;

@Slf4j
@Component
@Validated
@RequiredArgsConstructor
public class MainOrderController {

    private final OrderService orderService;

    public Check createCheck(@Valid Bucket bucket) {
        log.info("Bucket: {}", bucket);

        return orderService.generateCheck(bucket);
    }
}
