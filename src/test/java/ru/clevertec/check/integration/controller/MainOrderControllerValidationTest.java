package ru.clevertec.check.integration.controller;

import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.clevertec.check.controller.MainOrderController;
import ru.clevertec.check.dto.request.Bucket;
import ru.clevertec.check.integration.BaseIntegrationTest;
import ru.clevertec.check.service.OrderService;
import ru.clevertec.check.util.provider.BucketProvider;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

@RequiredArgsConstructor
public class MainOrderControllerValidationTest extends BaseIntegrationTest {

    @MockBean
    private OrderService orderService;

    @Autowired
    private MainOrderController mainOrderController;

    @Tag("invalid")
    @ParameterizedTest
    @ArgumentsSource(BucketProvider.class)
    void createCheckValidationFail(Bucket bucket) {
        assertThatThrownBy(() -> mainOrderController.createCheck(bucket))
                .isExactlyInstanceOf(ConstraintViolationException.class);

        verifyNoInteractions(orderService);
    }

    @Tag("valid")
    @ParameterizedTest
    @ArgumentsSource(BucketProvider.class)
    void createCheckValidation(Bucket bucket) {
        mainOrderController.createCheck(bucket);

        verify(orderService).generateCheck(bucket);
    }
}
