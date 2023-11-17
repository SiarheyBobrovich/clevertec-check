package ru.clevertec.check.service;

import ru.clevertec.check.dto.request.Order;
import ru.clevertec.check.dto.GoodInfo;
import ru.clevertec.check.exception.ProductQuantityInStockIsNotAvailableException;

import java.util.List;

public interface GoodService {

    List<GoodInfo> subtractCountAndGet(List<Order> orderList) throws ProductQuantityInStockIsNotAvailableException;

}
