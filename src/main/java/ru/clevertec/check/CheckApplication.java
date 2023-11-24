package ru.clevertec.check;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import ru.clevertec.check.conf.CommandLineArgumentResolver;
import ru.clevertec.check.dto.CommandLineArgumentContainer;
import ru.clevertec.check.processor.MainOrderProcessor;

@SpringBootApplication
public class CheckApplication {

    public static void main(String[] args) {
        CommandLineArgumentContainer argumentContainer = CommandLineArgumentResolver.splitArgs(args);

        ConfigurableApplicationContext context = new SpringApplicationBuilder()
                .sources(CheckApplication.class)
                .web(WebApplicationType.NONE)
                .main(CheckApplication.class)
                .run(argumentContainer.getSpringArguments());

        context.getBean(MainOrderProcessor.class)
                .processOrder(argumentContainer.getAppArguments());
    }

}
