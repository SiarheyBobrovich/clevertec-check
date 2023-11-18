package ru.clevertec.check.integration;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.clevertec.check.integration.annotation.IntegrationTest;

@IntegrationTest
public class BaseIntegrationTest {

    private static final PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:15.1")
            .withDatabaseName("check");

    @BeforeAll
    static void start() {
        container.start();
    }

    @DynamicPropertySource
    private static void registry(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }
}
