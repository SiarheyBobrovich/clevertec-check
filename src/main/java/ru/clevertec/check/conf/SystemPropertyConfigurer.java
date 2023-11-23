package ru.clevertec.check.conf;

import org.springframework.boot.SpringApplicationRunListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SystemPropertyConfigurer implements SpringApplicationRunListener {

    public static String[] resolveArgs(String[] args) {
        List<String> result = new ArrayList<>();

        Arrays.stream(args)
                .forEach(arg -> {
                    if (arg.startsWith("pathToFile")) {
                        result.add("--spring.product.data.file=true");
                        result.add("--spring.product.data.path=" + arg.split("=")[1]);

                    } else {
                        result.add(arg);
                    }
                });

        return result.toArray(String[]::new);
    }
}
