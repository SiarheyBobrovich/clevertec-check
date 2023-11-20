package ru.clevertec.check.factory;

import ru.clevertec.check.dto.GoodInfo;
import ru.clevertec.check.dto.response.BalancedDiscountCard;
import ru.clevertec.check.dto.response.Check;

import java.util.List;

public interface CheckFactory {

    Check createCheck(List<GoodInfo> goodInfoList, BalancedDiscountCard discountCard);
}
