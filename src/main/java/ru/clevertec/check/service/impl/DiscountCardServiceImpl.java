package ru.clevertec.check.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.clevertec.check.dto.request.DiscountCardDto;
import ru.clevertec.check.dto.BalancedDiscountCard;
import ru.clevertec.check.mapper.DiscountCardMapper;
import ru.clevertec.check.repository.DiscountCardRepository;
import ru.clevertec.check.service.DiscountCardService;

@Slf4j
@Service
@RequiredArgsConstructor
public class DiscountCardServiceImpl implements DiscountCardService {

    private final DiscountCardRepository discountCardRepository;
    private final DiscountCardMapper discountCardMapper;

    /**
     * Метод проверяет номер карты и:
     * <pre>
     *     - Если отсутствует -> возвращает карту с балансом и 0% скидкой
     *     - Если отсутствует в BD -> возвращает карту с балансом и 2% скидкой
     *     - Если есть в BD -> возвращает карту с балансом и скидкой из DB
     * </pre>
     *
     * @param discountCardDto DTO с номером карты и балансом
     * @return Карту с балансом и скидкой
     */
    @Override
    public BalancedDiscountCard getWithBalance(DiscountCardDto discountCardDto) {
        Integer cardNumber = discountCardDto.getNumber();
        return switch (cardNumber) {
            case null -> discountCardMapper.toNoneDiscountCard(discountCardDto);
            default -> discountCardRepository.findByNumber(cardNumber)
                    .map(card -> discountCardMapper.toBalancedDiscountCard(card, discountCardDto.getBalance()))
                    .orElseGet(() -> discountCardMapper.toDefaultDiscountCard(discountCardDto));
        };
    }
}
