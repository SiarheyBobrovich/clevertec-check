package ru.clevertec.check.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.clevertec.check.dto.GoodInfo;
import ru.clevertec.check.dto.request.Bucket;
import ru.clevertec.check.dto.request.GoodDto;
import ru.clevertec.check.dto.response.BalancedDiscountCard;
import ru.clevertec.check.dto.response.Check;
import ru.clevertec.check.factory.CheckFactory;
import ru.clevertec.check.service.DiscountCardService;
import ru.clevertec.check.service.GoodService;
import ru.clevertec.check.service.OrderService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final GoodService goodService;
    private final DiscountCardService discountCardService;
    private final CheckFactory checkFactory;

    @Override
    public Check generateCheck(Bucket bucket) {
        List<GoodDto> listGoods = bucket.getGoods().stream()
                .collect(Collectors.collectingAndThen(Collectors.groupingBy(
                                GoodDto::id, Collectors.reducing(0, GoodDto::quantity, Integer::sum)),
                        idQty -> idQty.entrySet().stream()
                                .map(x -> new GoodDto(x.getKey(), x.getValue())).toList()));

        List<GoodInfo> goodInfos = goodService.subtractCountAndGet(listGoods);
        BalancedDiscountCard discountCardDto = discountCardService.getWithBalance(bucket.getDiscountCard());

        return checkFactory.createCheck(goodInfos, discountCardDto);
    }
}
