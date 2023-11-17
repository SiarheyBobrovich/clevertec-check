package ru.clevertec.check.service;

import ru.clevertec.check.dto.BalancedDiscountCard;
import ru.clevertec.check.dto.DiscountCardDto;

public interface DiscountCardService {

    BalancedDiscountCard getWithBalance(DiscountCardDto discountCardDto);
}
