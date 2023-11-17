package ru.clevertec.check.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.clevertec.check.dto.BalancedDiscountCard;
import ru.clevertec.check.dto.Body;
import ru.clevertec.check.dto.Check;
import ru.clevertec.check.dto.GoodArgs;
import ru.clevertec.check.dto.GoodInfo;
import ru.clevertec.check.dto.GoodResponseDto;
import ru.clevertec.check.dto.Title;
import ru.clevertec.check.dto.Total;
import ru.clevertec.check.exception.ProductQuantityInStockIsNotAvailableException;
import ru.clevertec.check.service.CheckService;
import ru.clevertec.check.service.DiscountCardService;
import ru.clevertec.check.service.GoodService;
import ru.clevertec.check.service.PrintService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MarketServiceImpl implements CheckService {

    private final GoodService goodService;
    private final DiscountCardService discountCardService;
    private final PrintService printService;

    @Override
    public void generateCheck(GoodArgs args) {
        List<GoodInfo> goodInfos;
        BalancedDiscountCard discountCardDto;
        try {
            goodInfos = goodService.subtractCountAndGet(args.goodDtoList());
            discountCardDto = discountCardService.getWithBalance(args.cardDto());

        } catch (ProductQuantityInStockIsNotAvailableException e) {
            log.info(e.getMessage());
            printService.printToFile("/file.scv", e);
            return;
        }

        List<GoodResponseDto> responseDtos = goodInfos.stream()
                .map(goodInfo -> GoodResponseDto.builder()
                        .price(goodInfo.price())
                        .description(goodInfo.description())
                        .count(goodInfo.count())
                        .discount(goodInfo.isTradePrice() && goodInfo.count() > 5 ? 10 : discountCardDto.discountPercentage())
                        .build()
                ).toList();

        Title title = new Title();
        Body body = getBody(responseDtos);
        Check check = Check.builder()
                .title(title)
                .body(body)
                .total(getTotal(body))
                .build();

        printService.printToFile("/file.csv", check);
        printService.printToConsole(check);
    }

    private Total getTotal(Body body) {
        return Total.builder()
                .totalPrice(body.getTotalPrice())
                .totalDiscount(body.getTotalDiscount())
                .totalWithDiscount(body.getTotalPrice().subtract(body.getTotalDiscount()))
                .build();
    }

    private Body getBody(List<GoodResponseDto> responseDtos) {
        return Body.builder()
                .goodList(responseDtos)
                .build();
    }
}
