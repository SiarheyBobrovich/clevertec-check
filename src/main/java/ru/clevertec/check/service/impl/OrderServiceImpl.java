package ru.clevertec.check.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.clevertec.check.dto.GoodInfo;
import ru.clevertec.check.dto.request.Bucket;
import ru.clevertec.check.dto.response.BalancedDiscountCard;
import ru.clevertec.check.dto.response.Check;
import ru.clevertec.check.factory.CheckFactory;
import ru.clevertec.check.service.DiscountCardService;
import ru.clevertec.check.service.GoodService;
import ru.clevertec.check.service.OrderService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final GoodService goodService;
    private final DiscountCardService discountCardService;
    private final CheckFactory checkFactory;

    @Override
    public Check generateCheck(Bucket args) throws Exception {
        List<GoodInfo> goodInfos = goodService.subtractCountAndGet(args.orderList());
        BalancedDiscountCard discountCardDto = discountCardService.getWithBalance(args.cardDto());

        return checkFactory.createCheck(goodInfos, discountCardDto);
    }
}
