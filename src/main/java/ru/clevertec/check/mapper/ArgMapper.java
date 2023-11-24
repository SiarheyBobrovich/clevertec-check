package ru.clevertec.check.mapper;

import org.mapstruct.Mapper;
import ru.clevertec.check.dto.request.Bucket;
import ru.clevertec.check.dto.request.DiscountCardDto;
import ru.clevertec.check.dto.request.ProductDto;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

@Mapper
public abstract class ArgMapper {

    public Bucket parseArg(String[] args) {
        return Bucket.builder()
                .products(parseArgsToOrderList(args))
                .discountCard(parseArgsToCardDto(args))
                .build();
    }

    private DiscountCardDto parseArgsToCardDto(String[] args) {
        Map<String, String> nameNumber = Arrays.stream(args)
                .filter(arg -> arg.startsWith("discountCard") || arg.startsWith("balanceDebitCard"))
                .map(arg -> arg.split("="))
                .filter(arg -> arg.length == 2)
                .filter(arg -> arg[1].matches("-?([0-9]+)|([0-9]+\\.?[0-9]*)"))
                .collect(toMap(x -> x[0], x -> x[1]));

        return DiscountCardDto.builder()
                .number(nameNumber.get("discountCard"))
                .balance(nameNumber.get("balanceDebitCard"))
                .build();
    }

    private List<ProductDto> parseArgsToOrderList(String[] args) {
        return Arrays.stream(args)
                .filter(arg -> arg.matches("^[0-9]*-[0-9].*"))
                .map(arg -> arg.split("-"))
                .filter(arg -> arg.length == 2)
                .filter(arg -> arg[0].matches("-?[0-9]+"))
                .filter(arg -> arg[1].matches("-?[0-9]+"))
                .map(arg -> Map.entry(arg[0], arg[1]))
                .map(entry -> ProductDto.builder()
                        .id(Long.parseLong(entry.getKey()))
                        .quantity(Integer.parseInt(entry.getValue()))
                        .build())
                .toList();
    }
}
