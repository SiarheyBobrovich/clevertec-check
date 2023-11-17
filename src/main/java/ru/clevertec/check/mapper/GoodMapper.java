package ru.clevertec.check.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.clevertec.check.dto.GoodInfo;
import ru.clevertec.check.entity.Good;

@Mapper
public interface GoodMapper {

    @Mapping(target = "count", source = "count")
    @Mapping(target = "isTradePrice", source = "good.wholesaleGoods")
    GoodInfo toGoodInfo(Good good, Integer count);
}
