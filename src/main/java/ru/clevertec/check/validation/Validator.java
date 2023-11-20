package ru.clevertec.check.validation;

public interface Validator<T> {

    void validate(T args);
}
