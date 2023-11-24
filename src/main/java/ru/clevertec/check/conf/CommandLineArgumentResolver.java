package ru.clevertec.check.conf;

import lombok.extern.slf4j.Slf4j;
import ru.clevertec.check.dto.ArgsWrapper;
import ru.clevertec.check.dto.CommandLineArgumentContainer;

import java.util.Arrays;

@Slf4j
public class CommandLineArgumentResolver {

    public static CommandLineArgumentContainer splitArgs(String[] args) {
        log.info("Input args: {}", Arrays.asList(args));
        ArgsWrapper argsWrapper = new ArgsWrapper();

        Arrays.stream(args)
                .forEach(arg -> {
                    if (arg.startsWith("pathToFile")) {
                        argsWrapper.addSpringArg(
                                "--spring.product.data.file=true",
                                "--spring.product.data.path=" + arg.split("=")[1]);

                    } else if (arg.startsWith("saveToFile")) {
                        argsWrapper.addSpringArg("--spring.product.data.save.file=" + arg.split("=")[1]);

                    } else {
                        argsWrapper.addAppArg(arg);
                    }
                });

        log.info("Output args: {}", argsWrapper);

        return argsWrapper;
    }
}
