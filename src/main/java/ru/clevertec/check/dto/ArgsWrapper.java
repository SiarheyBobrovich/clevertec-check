package ru.clevertec.check.dto;

import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ToString
public class ArgsWrapper implements CommandLineArgumentContainer {

    private final List<String> appArgs = new ArrayList<>();
    private final List<String> springArgs = new ArrayList<>();

    public void addAppArg(String... args) {
        appArgs.addAll(Arrays.asList(args));
    }

    public void addSpringArg(String... args) {
        springArgs.addAll(Arrays.asList(args));
    }

    @Override
    public String[] getAppArguments() {
        return appArgs.toArray(String[]::new);
    }

    @Override
    public String[] getSpringArguments() {
        return springArgs.toArray(String[]::new);
    }
}
