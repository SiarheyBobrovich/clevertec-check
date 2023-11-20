package ru.clevertec.check.mapper;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import ru.clevertec.check.dto.request.Bucket;
import ru.clevertec.check.util.provider.BucketProvider;

import static org.assertj.core.api.Assertions.assertThat;

class ArgMapperTest {

    private final ArgMapper argMapper = new ArgMapperImpl();

    @Tag("valid")
    @ParameterizedTest
    @ArgumentsSource(BucketProvider.class)
    void parseArg(Bucket bucket, String args) {
        Bucket actual = argMapper.parseArg(args.split(" "));

        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(bucket);
    }
}
