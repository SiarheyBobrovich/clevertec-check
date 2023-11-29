package ru.clevertec.check.integration;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.SpyBean;
import ru.clevertec.check.processor.MainOrderProcessor;
import ru.clevertec.check.repository.ProductRepository;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static ru.clevertec.check.util.TestData.buildListProducts;

public class CheckApplicationTest extends BaseIntegrationTest {

    @Autowired
    private MainOrderProcessor mainOrderProcessor;

    @SpyBean
    private ProductRepository productRepository;

    @Value("${app.product.data.save.file}")
    private String filePath;

    @Value("${app.constant.base.delimiter}")
    private String delimiter;

    private final List<String> expected = Arrays.asList("""
            Date;Time
                        
            QTY;DESCRIPTION;PRICE;DISCOUNT;TOTAL
            4;Milk;1.07$;0.09$;4.28$
            5;Cream 400g;2.71$;1.36$;13.55$
                        
            DISCOUNT CARD;DISCOUNT PERCENTAGE
            1212;2%
                        
            TOTAL PRICE;TOTAL DISCOUNT;TOTAL WITH DISCOUNT
            17.83$;1.45$;16.38$
            """.split("\n"));

    @Test
    @SneakyThrows
    void testCheckWithoutTitle() {
        int timeIndex = 1;

        doReturn(buildListProducts())
                .when(productRepository).findAllById(Set.of(1L, 2L));

        mainOrderProcessor.processOrder(new String[]{"1-1", "1-3", "2-5", "balanceDebitCard=140.99", "discountCard=1212"});

        List<String> actual = Files.readAllLines(Paths.get(filePath));
        actual.remove(timeIndex);

        assertThat(actual)
                .hasSize(expected.size())
                .containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    @SneakyThrows
    void testTitleDate() {
        LocalDate now = LocalDate.now();

        doReturn(buildListProducts())
                .when(productRepository).findAllById(Set.of(1L, 2L));

        mainOrderProcessor.processOrder(new String[]{"1-1", "1-3", "2-5", "balanceDebitCard=140.99", "discountCard=1212"});

        String actual = Files.readAllLines(Paths.get(filePath))
                .get(1)
                .split(delimiter)[0];

        LocalDate actualDate = LocalDate.parse(actual, DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        assertThat(actualDate)
                .isAfterOrEqualTo(now);
    }

    @Test
    @SneakyThrows
    void testTitleTime() {
        LocalTime now = LocalTime.now().truncatedTo(ChronoUnit.SECONDS);

        doReturn(buildListProducts())
                .when(productRepository).findAllById(Set.of(1L, 2L));

        mainOrderProcessor.processOrder(new String[]{"1-1", "1-3", "2-5", "balanceDebitCard=140.99", "discountCard=1212"});

        String actualTimeAsString = Files.readAllLines(Paths.get(filePath))
                .get(1)
                .split(delimiter)[1];

        LocalTime actualTime = LocalTime.parse(actualTimeAsString, DateTimeFormatter.ofPattern("H:mm:ss"));

        assertThat(actualTime)
                .isAfterOrEqualTo(now)
                .isBefore(LocalTime.now());
    }
}
