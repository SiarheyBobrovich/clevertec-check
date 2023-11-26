package ru.clevertec.check.service;

import ru.clevertec.check.dto.request.DiscountCardDto;
import ru.clevertec.check.dto.BalancedDiscountCard;

public interface DiscountCardService {

    BalancedDiscountCard getWithBalance(DiscountCardDto discountCardDto);
}
