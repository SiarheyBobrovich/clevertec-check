package ru.clevertec.check.repository;

import ru.clevertec.check.entity.Product;

import java.util.List;

public interface ProductRepository {

    Product saveAndFlush(Product entity);

    List<Product> findAllById(Iterable<Long> longs);
}
