package ru.clevertec.check.factory.impl;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;
import ru.clevertec.check.dto.BalancedDiscountCard;
import ru.clevertec.check.dto.ProductInfo;
import ru.clevertec.check.dto.response.Check;
import ru.clevertec.check.dto.response.CheckBody;
import ru.clevertec.check.dto.response.CheckTitle;
import ru.clevertec.check.dto.response.CheckTotal;
import ru.clevertec.check.dto.response.DiscountCheck;
import ru.clevertec.check.dto.response.OrderResponseDto;
import ru.clevertec.check.dto.response.PrintableDiscountCard;
import ru.clevertec.check.exception.BalanceNotAvailableException;
import ru.clevertec.check.factory.CheckFactory;

import java.math.BigDecimal;
import java.util.List;

@Component
public abstract class CheckFactoryImpl implements CheckFactory {

    @Override
    public Check createCheck(List<ProductInfo> productInfoList,
                             BalancedDiscountCard discountCard) {
        List<OrderResponseDto> orderResponseList = buildOrderResponseList(productInfoList, discountCard);

        return getCheck(orderResponseList, discountCard);
    }

    private void checkBalanceMoreThenCheckTotal(CheckBody checkBody, BigDecimal balance) {
        BigDecimal totalPrice = checkBody.getTotalPrice().subtract(checkBody.getTotalDiscount());
        boolean isMore = balance.compareTo(totalPrice) >= 0;

        if (!isMore) {
            throw new BalanceNotAvailableException();
        }
    }

    private List<OrderResponseDto> buildOrderResponseList(List<ProductInfo> productInfoList,
                                                          BalancedDiscountCard discountCard) {
        return productInfoList.stream()
                .map(goodInfo -> getOrderResponseDto(
                        goodInfo.description(),
                        goodInfo.price(),
                        goodInfo.count(),
                        goodInfo.isTradePrice() && goodInfo.count() >= 5 ? 10 : discountCard.discountPercentage())
                ).toList();
    }

    private Check getCheck(List<OrderResponseDto> orderResponseList,
                           BalancedDiscountCard balancedDiscountCard) {
        CheckBody checkBody = getBody(orderResponseList);

        checkBalanceMoreThenCheckTotal(checkBody, balancedDiscountCard.balance());

        CheckTotal checkTotal = getTotal(checkBody);

        return buildCheck(getTitle(), checkBody, checkTotal, balancedDiscountCard);
    }

    @Lookup
    abstract CheckTitle getTitle();

    @Lookup
    abstract CheckBody getBody(List<OrderResponseDto> responseDtos);

    @Lookup
    abstract OrderResponseDto getOrderResponseDto(String description,
                                                  BigDecimal price,
                                                  Integer count,
                                                  Byte discount);

    @Lookup
    abstract PrintableDiscountCard getPrintableDiscountCard(BalancedDiscountCard balancedDiscountCard);

    @Lookup
    abstract CheckTotal getTotal(CheckBody checkBody);

    private Check buildCheck(CheckTitle checkTitle,
                             CheckBody checkBody,
                             CheckTotal checkTotal,
                             BalancedDiscountCard balancedDiscountCard) {
        Check check;

        if (balancedDiscountCard.isDefault()) {
            check = buildDefaultCheck(checkTitle, checkBody, checkTotal);

        } else {
            PrintableDiscountCard printableDiscountCard = getPrintableDiscountCard(balancedDiscountCard);
            check = buildDiscountCheck(checkTitle, checkBody, printableDiscountCard, checkTotal);
        }

        return check;
    }

    private Check buildDiscountCheck(CheckTitle checkTitle,
                                     CheckBody checkBody,
                                     PrintableDiscountCard printableDiscountCard,
                                     CheckTotal checkTotal) {
        return DiscountCheck.builder()
                .checkTitle(checkTitle)
                .checkBody(checkBody)
                .printableDiscountCard(printableDiscountCard)
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
