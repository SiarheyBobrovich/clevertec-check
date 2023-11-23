package ru.clevertec.check.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.clevertec.check.dto.request.DiscountCardDto;
import ru.clevertec.check.dto.response.BalancedDiscountCard;
import ru.clevertec.check.entity.DiscountCard;

import java.math.BigDecimal;

@Mapper
public interface DiscountCardMapper {

    @Mapping(target = "balance", source = "balance")
    @Mapping(target = "discountPercentage", source = "discountCard.amount")
    BalancedDiscountCard toBalancedDiscountCard(DiscountCard discountCard, BigDecimal balance);

    @Mapping(target = "discountPercentage", expression = "java((byte)2)")
    BalancedDiscountCard toDefaultDiscountCard(DiscountCardDto discountCardDto);

    @Mapping(target = "discountPercentage", expression = "java((byte)0)")
    BalancedDiscountCard toNoneDiscountCard(DiscountCardDto discountCardDto);
}
