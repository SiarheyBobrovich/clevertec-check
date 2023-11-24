package ru.clevertec.check.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.clevertec.check.dto.ProductInfo;
import ru.clevertec.check.entity.Product;

@Mapper
public interface ProductMapper {

    @Mapping(target = "count", source = "count")
    @Mapping(target = "isTradePrice", source = "product.wholesaleProduct")
    ProductInfo toGoodInfo(Product product, Integer count);
}
