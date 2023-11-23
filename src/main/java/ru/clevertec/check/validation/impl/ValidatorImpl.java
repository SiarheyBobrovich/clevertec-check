package ru.clevertec.check.validation.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import ru.clevertec.check.exception.ValidationException;
import ru.clevertec.check.validation.Validator;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Predicate;

@Component
public class ValidatorImpl implements Validator<String[]> {

    @Override
    public void validate(String[] args) {
        checkExistAnyProducts(args);
        checkExistBalance(args);
        checkArgs(args);
    }

    private void checkExistAnyProducts(String[] args) {
        if (Arrays.stream(args)
                .noneMatch(arg -> arg.matches("(\\d+--?\\d+)"))) {
            throw new ValidationException();
        }
    }

    private void checkExistBalance(String[] args) {
        if (Arrays.stream(args)
                .filter(arg -> arg.matches("(balanceDebitCard=-?(\\d+\\.?\\d*))"))
                .count() != 1) {
            throw new ValidationException();
        }
    }

    private void checkArgs(String[] args) {
        boolean isAllArgsMatch = Arrays.stream(args)
                .filter(Objects::nonNull)
                .filter(Predicate.not(StringUtils::isBlank))
                .filter(arg -> arg.matches("(\\d+--?\\d+)|(discountCard=((\\d{4})|(null)))|(balanceDebitCard=-?(\\d+\\.?\\d*))"))
                .count() == args.length;

        if (!isAllArgsMatch) {
            throw new ValidationException();
        }
    }
}
