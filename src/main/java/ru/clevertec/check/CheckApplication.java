package ru.clevertec.check;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class CheckApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(CheckApplication.class)
                .web(WebApplicationType.NONE)
                .main(CheckApplication.class)
                .run(args);
    }

}
