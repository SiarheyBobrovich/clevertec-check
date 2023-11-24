package ru.clevertec.check.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.clevertec.check.dto.ProductInfo;
import ru.clevertec.check.dto.request.Bucket;
import ru.clevertec.check.dto.request.ProductDto;
import ru.clevertec.check.dto.response.BalancedDiscountCard;
import ru.clevertec.check.dto.response.Check;
import ru.clevertec.check.factory.CheckFactory;
import ru.clevertec.check.service.DiscountCardService;
import ru.clevertec.check.service.ProductService;
import ru.clevertec.check.service.OrderService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final ProductService productService;
    private final DiscountCardService discountCardService;
    private final CheckFactory checkFactory;

    @Override
    public Check generateCheck(Bucket bucket) {
        List<ProductDto> listGoods = bucket.getProducts().stream()
                .collect(Collectors.collectingAndThen(Collectors.groupingBy(
                                ProductDto::id, Collectors.reducing(0, ProductDto::quantity, Integer::sum)),
                        idQty -> idQty.entrySet().stream()
                                .map(x -> new ProductDto(x.getKey(), x.getValue())).toList()));

        List<ProductInfo> productInfos = productService.subtractCountAndGet(listGoods);
        BalancedDiscountCard discountCardDto = discountCardService.getWithBalance(bucket.getDiscountCard());

        return checkFactory.createCheck(productInfos, discountCardDto);
    }
}
