package ru.clevertec.check;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import ru.clevertec.check.conf.SystemPropertyConfigurer;
import ru.clevertec.check.processor.MainOrderProcessor;

@SpringBootApplication
public class CheckApplication {

    public static void main(String[] args) {
        args = SystemPropertyConfigurer.resolveArgs(args);

        ConfigurableApplicationContext context = new SpringApplicationBuilder()
                .sources(CheckApplication.class)
                .web(WebApplicationType.NONE)
                .main(CheckApplication.class)
                .run(args);

        context.getBean(MainOrderProcessor.class)
                .processOrder(args);
    }

}
