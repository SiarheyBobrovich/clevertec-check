package ru.clevertec.check.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ru.clevertec.check.entity.Product;
import ru.clevertec.check.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final Map<Long, Product> goodMap;

    @NonNull
    @Override
    public Product saveAndFlush(@NonNull Product good) {
        if (good.getId() == null) {
            Long id = goodMap.keySet().stream()
                    .max(Long::compareTo)
                    .orElse(1L);
            good.setId(id);
        }

        goodMap.put(good.getId(), good);

        return new Product(good);
    }

    @NonNull
    @Override
    public List<Product> findAllById(@NonNull Iterable<Long> longs) {
        List<Product> result = new ArrayList<>();

        longs.forEach(id ->
                result.add(goodMap.get(id))
        );

        return result.stream()
                .filter(Objects::nonNull)
                .map(Product::new)
                .toList();
    }
}
