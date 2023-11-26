package ru.clevertec.check.dto.response;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Component
@Scope("prototype")
public class CheckTitle extends AbstractTitlePrintable {

    @Value("${app.constant.base.delimiter}")
    private String delimiter;

    @Getter
    @Value("${app.constant.title}")
    private String title;

    @Value("#{T(java.time.LocalDate).now()}")
    private LocalDate date;

    @Value("#{T(java.time.LocalTime).now()}")
    private LocalTime time;

    @Override
    protected void printBody(Writer writer) throws IOException {
        writer.append(date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")))
                .append(delimiter)
                .append(time.truncatedTo(ChronoUnit.SECONDS).toString())
                .append('\n');
    }
}
