package ru.clevertec.check.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.clevertec.check.dto.request.DiscountCardDto;
import ru.clevertec.check.dto.response.BalancedDiscountCard;
import ru.clevertec.check.mapper.DiscountCardMapper;
import ru.clevertec.check.repository.DiscountCardRepository;
import ru.clevertec.check.service.DiscountCardService;

@Slf4j
@Service
@RequiredArgsConstructor
public class DiscountCardServiceImpl implements DiscountCardService {

    private final DiscountCardRepository discountCardRepository;
    private final DiscountCardMapper discountCardMapper;

    @Override
    public BalancedDiscountCard getWithBalance(DiscountCardDto discountCardDto) {
        Integer cardNumber = discountCardDto.getNumber();
        return cardNumber == null ?
                discountCardMapper.toDefaultDiscountCard(discountCardDto) :
                discountCardRepository.findByNumber(cardNumber)
                        .map(card -> discountCardMapper.toBalancedDiscountCard(card, discountCardDto.getBalance()))
                        .orElseGet(() -> discountCardMapper.toDefaultDiscountCard(discountCardDto));
    }
}
