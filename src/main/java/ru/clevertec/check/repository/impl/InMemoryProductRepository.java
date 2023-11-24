package ru.clevertec.check.repository.impl;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ru.clevertec.check.entity.Product;
import ru.clevertec.check.repository.ProductRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class InMemoryProductRepository implements ProductRepository {

    private final Map<Long, Product> goodMap = new HashMap<>();

    {
        goodMap.put(1L, new Product(1L, "Milk", BigDecimal.valueOf(1.07), 10, true));
        goodMap.put(2L, new Product(2L, "Cream 400g", BigDecimal.valueOf(2.71), 20, true));
        goodMap.put(3L, new Product(3L, "Yogurt 400g", BigDecimal.valueOf(2.10), 7, true));
        goodMap.put(4L, new Product(4L, "Packed potatoes 1kg", BigDecimal.valueOf(1.47), 30, false));
        goodMap.put(5L, new Product(5L, "Packed cabbage 1kg", BigDecimal.valueOf(1.19), 15, false));
        goodMap.put(6L, new Product(6L, "Packed tomatoes 350g", BigDecimal.valueOf(1.60), 50, false));
        goodMap.put(7L, new Product(7L, "Packed apples 1kg", BigDecimal.valueOf(2.78), 18, false));
        goodMap.put(8L, new Product(8L, "Packed oranges 1kg", BigDecimal.valueOf(3.20), 12, false));
        goodMap.put(9L, new Product(9L, "Packed bananas 1kg", BigDecimal.valueOf(1.10), 25, true));
        goodMap.put(10L, new Product(10L, "Packed beef fillet 1kg", BigDecimal.valueOf(12.80), 7, false));
        goodMap.put(11L, new Product(11L, "Packed pork fillet 1kg", BigDecimal.valueOf(8.52), 14, false));
        goodMap.put(12L, new Product(12L, "Packed chicken breasts 1kgSour ", BigDecimal.valueOf(10.75), 18, false));
        goodMap.put(13L, new Product(13L, "Baguette 360g", BigDecimal.valueOf(1.30), 10, true));
        goodMap.put(14L, new Product(14L, "Drinking water 1,5l", BigDecimal.valueOf(0.80), 100, false));
        goodMap.put(15L, new Product(15L, "Olive oil 500ml", BigDecimal.valueOf(5.30), 16, false));
        goodMap.put(16L, new Product(16L, "Sunflower oil 1l", BigDecimal.valueOf(1.20), 12, false));
        goodMap.put(17L, new Product(17L, "Chocolate Ritter sport 100g", BigDecimal.valueOf(1.10), 50, true));
        goodMap.put(18L, new Product(18L, "Paulaner 0,5l", BigDecimal.valueOf(1.10), 100, false));
        goodMap.put(19L, new Product(19L, "Whiskey Jim Beam 1l", BigDecimal.valueOf(13.99), 30, false));
        goodMap.put(20L, new Product(20L, "Whiskey Jack Daniels 1l", BigDecimal.valueOf(17.19), 20, false));

        goodMap.values()
                .forEach(product -> product.setPrice(product.getPrice().setScale(2, RoundingMode.HALF_UP)));
    }

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
