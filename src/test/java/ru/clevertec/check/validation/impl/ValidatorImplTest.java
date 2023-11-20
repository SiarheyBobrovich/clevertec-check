package ru.clevertec.check.validation.impl;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.clevertec.check.dto.request.Bucket;
import ru.clevertec.check.exception.ValidationException;
import ru.clevertec.check.util.provider.BucketProvider;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ValidatorImplTest {

    private final ValidatorImpl validator = new ValidatorImpl();

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/invalid_args.csv", delimiter = '\n')
    void validateShouldThrowValidationException(String args) {
        assertThatThrownBy(() -> validator.validate(args.split(" ")))
                .isExactlyInstanceOf(ValidationException.class);
    }

    @Tag("valid")
    @ParameterizedTest
    @ArgumentsSource(BucketProvider.class)
    void validateShouldNotThrow(Bucket bucket, String args) {
        assertThatNoException()
                .isThrownBy(() -> validator.validate(args.split(" ")));
    }
}
