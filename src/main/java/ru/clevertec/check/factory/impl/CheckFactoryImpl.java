package ru.clevertec.check.factory.impl;

import org.springframework.stereotype.Component;
import ru.clevertec.check.dto.ProductInfo;
import ru.clevertec.check.dto.response.BalancedDiscountCard;
import ru.clevertec.check.dto.response.Check;
import ru.clevertec.check.dto.response.CheckBody;
import ru.clevertec.check.dto.response.CheckTitle;
import ru.clevertec.check.dto.response.CheckTotal;
import ru.clevertec.check.dto.response.DiscountCheck;
import ru.clevertec.check.dto.response.OrderResponseDto;
import ru.clevertec.check.exception.BalanceNotAvailableException;
import ru.clevertec.check.factory.CheckFactory;

import java.math.BigDecimal;
import java.util.List;

@Component
public class CheckFactoryImpl implements CheckFactory {

    @Override
    public Check createCheck(List<ProductInfo> productInfoList,
                             BalancedDiscountCard discountCard) {
        List<OrderResponseDto> orderResponseList = buildOrderResponseList(productInfoList, discountCard);

        checkBalance(orderResponseList, discountCard);

        return getCheck(orderResponseList, discountCard);
    }

    private void checkBalance(List<OrderResponseDto> orderResponseList,
                              BalancedDiscountCard discountCard) throws BalanceNotAvailableException {
        orderResponseList.stream()
                .map(OrderResponseDto::getTotal)
                .reduce(BigDecimal::add)
                .filter(total -> discountCard.balance().compareTo(total) >= 0)
                .orElseThrow(BalanceNotAvailableException::new);
    }

    private List<OrderResponseDto> buildOrderResponseList(List<ProductInfo> productInfoList,
                                                          BalancedDiscountCard discountCard) {
        return productInfoList.stream()
                .map(goodInfo -> OrderResponseDto.builder()
                        .price(goodInfo.price())
                        .description(goodInfo.description())
                        .count(goodInfo.count())
                        .discount(goodInfo.isTradePrice() && goodInfo.count() >= 5 ? 10 : discountCard.discountPercentage())
                        .build()
                ).toList();
    }

    private Check getCheck(List<OrderResponseDto> orderResponseList,
                           BalancedDiscountCard balancedDiscountCard) {
        CheckTitle checkTitle = new CheckTitle();
        CheckBody checkBody = getBody(orderResponseList);
        CheckTotal checkTotal = getTotal(checkBody);

        return buildCheck(checkTitle, checkBody, checkTotal, balancedDiscountCard);
    }

    private CheckTotal getTotal(CheckBody checkBody) {
        return CheckTotal.builder()
                .totalPrice(checkBody.getTotalPrice())
                .totalDiscount(checkBody.getTotalDiscount())
                .totalWithDiscount(checkBody.getTotalPrice().subtract(checkBody.getTotalDiscount()))
                .build();
    }

    private CheckBody getBody(List<OrderResponseDto> responseDtos) {
        return CheckBody.builder()
                .goodList(responseDtos)
                .build();
    }

    private Check buildCheck(CheckTitle checkTitle,
                             CheckBody checkBody,
                             CheckTotal checkTotal,
                             BalancedDiscountCard balancedDiscountCard) {
        return balancedDiscountCard.isDefault() ?
                buildDefaultCheck(checkTitle, checkBody, checkTotal) : buildDiscountCheck(checkTitle, checkBody, balancedDiscountCard, checkTotal);
    }

    private Check buildDiscountCheck(CheckTitle checkTitle,
                                     CheckBody checkBody,
                                     BalancedDiscountCard balancedDiscountCard,
                                     CheckTotal checkTotal) {
        return DiscountCheck.builder()
                .checkTitle(checkTitle)
                .checkBody(checkBody)
                .balancedDiscountCard(balancedDiscountCard)
                .checkTotal(checkTotal)
                .build();
    }

    private Check buildDefaultCheck(CheckTitle checkTitle,
                                    CheckBody checkBody,
                                    CheckTotal checkTotal) {
        return Check.builder()
                .checkTitle(checkTitle)
                .checkBody(checkBody)
                .checkTotal(checkTotal)
                .build();
    }
}
