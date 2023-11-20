package ru.clevertec.check.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.check.dto.GoodInfo;
import ru.clevertec.check.dto.request.GoodDto;
import ru.clevertec.check.entity.Good;
import ru.clevertec.check.exception.GoodNotFoundException;
import ru.clevertec.check.exception.GoodQuantityInStockIsNotAvailableException;
import ru.clevertec.check.mapper.GoodMapper;
import ru.clevertec.check.repository.GoodRepository;
import ru.clevertec.check.service.GoodService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GoodServiceImpl implements GoodService {

    private final GoodRepository goodRepository;
    private final GoodMapper goodMapper;

    @Override
    @Transactional
    public List<GoodInfo> subtractCountAndGet(List<GoodDto> goodDtoList) {
        Map<Long, Integer> idCountMap = goodDtoList.stream()
                .collect(Collectors.toMap(GoodDto::id, GoodDto::quantity));

        List<Good> allById = goodRepository.findAllById(idCountMap.keySet());

        if (allById.size() != idCountMap.size()) {
            throw new GoodNotFoundException();
        }

        return allById.stream()
                .map(good -> updateQuantityInStock(good, idCountMap.get(good.getId())))
                .map(goodRepository::saveAndFlush)
                .map(good -> goodMapper.toGoodInfo(good, idCountMap.get(good.getId())))
                .toList();
    }

    private Good updateQuantityInStock(Good good, Integer orderCount) {
        int remain = good.getQuantityInStock() - orderCount;

        if (remain < 0) {
            throw new GoodQuantityInStockIsNotAvailableException();
        }

        good.setQuantityInStock(remain);

        return good;
    }
}
