package ru.clevertec.check.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.clevertec.check.dto.ProductInfo;
import ru.clevertec.check.dto.request.ProductDto;
import ru.clevertec.check.entity.Product;
import ru.clevertec.check.exception.GoodNotFoundException;
import ru.clevertec.check.exception.GoodQuantityInStockIsNotAvailableException;
import ru.clevertec.check.mapper.ProductMapper;
import ru.clevertec.check.repository.ProductRepository;
import ru.clevertec.check.service.ProductService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    /**
     * Метод уменьшает количество продуктов в DB и возвращает обработанный продукт
     *
     * @param productDtoList Список идентификаторов продуктов с количеством
     * @return Список с Информацией по продуктам
     */
    @Override
    public List<ProductInfo> subtractCountAndGet(List<ProductDto> productDtoList) {
        Map<Long, Integer> idCountMap = productDtoList.stream()
                .collect(Collectors.toMap(ProductDto::id, ProductDto::quantity));

        List<Product> allById = productRepository.findAllById(idCountMap.keySet());

        if (allById.size() != idCountMap.size()) {
            throw new GoodNotFoundException();
        }

        return allById.stream()
                .map(good -> updateQuantityInStock(good, idCountMap.get(good.getId())))
                .map(productRepository::saveAndFlush)
                .map(good -> productMapper.toGoodInfo(good, idCountMap.get(good.getId())))
                .toList();
    }

    private Product updateQuantityInStock(Product product, Integer orderCount) {
        int remain = product.getQuantityInStock() - orderCount;

        if (remain < 0) {
            throw new GoodQuantityInStockIsNotAvailableException();
        }

        product.setQuantityInStock(remain);

        return product;
    }
}
