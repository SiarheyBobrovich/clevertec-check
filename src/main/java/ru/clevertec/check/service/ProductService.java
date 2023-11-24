package ru.clevertec.check.service;

import ru.clevertec.check.dto.ProductInfo;
import ru.clevertec.check.dto.request.ProductDto;

import java.util.List;

public interface ProductService {

    List<ProductInfo> subtractCountAndGet(List<ProductDto> productDtoList);

}
