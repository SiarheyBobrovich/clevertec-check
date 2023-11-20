package ru.clevertec.check.validation.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import ru.clevertec.check.exception.ValidationException;
import ru.clevertec.check.validation.Validator;

import java.util.Arrays;

@Component
public class ValidatorImpl implements Validator<String[]> {

    @Override
    public void validate(String[] args) {
        boolean isExistAnyGood = Arrays.stream(args)
                .anyMatch(arg -> arg.matches("(\\d+--?\\d+)"));

        boolean isExistBalance = Arrays.stream(args)
                .filter(arg -> arg.matches("(balanceDebitCard=-?(\\d+\\.?\\d*))"))
                .count() == 1;

        boolean b = Arrays.stream(args)
                .allMatch(arg ->
                        arg != null &&
                                !StringUtils.isBlank(arg) &&
                                arg.matches("(\\d+--?\\d+)|(discountCard=((\\d{4})|(null)))|(balanceDebitCard=-?(\\d+\\.?\\d*))"));

        if (!isExistAnyGood || !isExistBalance || !b) {
            throw new ValidationException();
        }
    }
}
