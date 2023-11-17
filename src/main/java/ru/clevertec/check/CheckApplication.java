package ru.clevertec.check;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import ru.clevertec.check.dto.GoodArgs;
import ru.clevertec.check.service.CheckService;
import ru.clevertec.check.util.ArgUtil;

@SpringBootApplication
public class CheckApplication {

    public static void main(String[] args) {
        GoodArgs goodArgs = ArgUtil.parseArg(args);

        ConfigurableApplicationContext context = new SpringApplicationBuilder()
                .sources(CheckApplication.class)
                .web(WebApplicationType.NONE)
                .main(CheckApplication.class)
                .run(args);

        context.getBean(CheckService.class)
                .generateCheck(goodArgs);
    }

}
