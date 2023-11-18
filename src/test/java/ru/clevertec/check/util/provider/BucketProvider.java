package ru.clevertec.check.util.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import ru.clevertec.check.util.TestData;

import java.util.Set;
import java.util.stream.Stream;

public class BucketProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        Set<String> tags = context.getTags();
        if (tags.contains("invalid")) {
            return TestData.getInvalidBuckets()
                    .stream()
                    .map(Arguments::of);
        }else {
            return TestData.getValidBuckets()
                    .stream()
                    .map(Arguments::of);
        }
    }
}
