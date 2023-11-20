package ru.clevertec.check.service;

import ru.clevertec.check.dto.request.Bucket;
import ru.clevertec.check.dto.response.Check;

public interface OrderService {

    Check generateCheck(Bucket args);
}
