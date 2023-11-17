package ru.clevertec.check.util;

import ru.clevertec.check.dto.DiscountCardDto;
import ru.clevertec.check.dto.GoodArgs;
import ru.clevertec.check.dto.GoodDto;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ArgUtil {

    public static GoodArgs parseArg(String[] args) {
        return GoodArgs.builder()
                .goodDtoList(parseArgsToGoodDtoList(args))
                .cardDto(parseArgsToCardDto(args))
                .build();
    }

    private static DiscountCardDto parseArgsToCardDto(String[] args) {
        Map<String, String> nameNumber = Arrays.stream(args)
                .filter(arg -> arg.startsWith("discountCard") || arg.startsWith("balanceDebitCard"))
                .map(arg -> arg.split("="))
                .filter(arg -> arg.length == 2)
                .filter(arg -> arg[1].matches("([0-9]+)|([0-9]+\\.?[0-9]*)"))
                .collect(Collectors.toMap(x -> x[0], x -> x[1]));

        return DiscountCardDto.builder()
                .number(Integer.parseInt(nameNumber.get("discountCard")))
                .balance(new BigDecimal(nameNumber.get("balanceDebitCard")))
                .build();
    }

    private static List<GoodDto> parseArgsToGoodDtoList(String[] args) {
        return Arrays.stream(args)
                .filter(arg -> arg.startsWith("id"))
                .map(arg -> arg.split("-"))
                .filter(arg -> arg.length == 2)
                .filter(arg -> arg[0].matches("[0-9]+"))
                .filter(arg -> arg[1].matches("[0-9]+"))
                .map(arg -> GoodDto.builder()
                        .id(Long.parseLong(arg[1]))
                        .quantity(Integer.parseInt(arg[1]))
                        .build())
                .toList();
    }
}
