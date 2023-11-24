package ru.clevertec.check.util.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import ru.clevertec.check.dto.request.Bucket;
import ru.clevertec.check.util.TestData;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BucketProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        Set<String> tags = context.getTags();
        if (tags.contains("invalid")) {
            return TestData.getInvalidBuckets()
                    .stream()
                    .map(Arguments::of);
        } else if (tags.contains("valid")) {
            return TestData.getValidBuckets().stream()
                    .collect(Collectors.toMap(x -> x, this::toValidArgs))
                    .entrySet().stream()
                    .map(entry -> Arguments.of(entry.getKey(), entry.getValue()));
        } else {
            throw new UnsupportedOperationException("Tags must have 'valid' or 'invalid' value");
        }
    }

    private String toValidArgs(Bucket bucket) {
        String idQtyArgs = bucket.getProducts().stream()
                .map(good -> good.id() + "-" + good.quantity())
                .reduce((arg1, arg2) -> String.join(" ", arg1, arg2))
                .orElse("");
        String discountCardArgs = "discountCard=" + bucket.getDiscountCard().getNumber();
        String balanceDebitCardArgs = "balanceDebitCard=" + bucket.getDiscountCard().getBalance();

        return String.join(" ", idQtyArgs, discountCardArgs, balanceDebitCardArgs);
    }
}
