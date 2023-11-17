package ru.clevertec.check.dto.response;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import ru.clevertec.check.constant.CheckConstant;

import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CheckTitle implements Printable {

    LocalDate date;
    LocalTime time;

    public CheckTitle() {
        this.date = LocalDate.now();
        this.time = LocalTime.now();
    }

    @Override
    public void print(Writer writer) throws IOException {
        writer.append(CheckConstant.Title.DATE)
                .append(CheckConstant.DELIMITER)
                .append(CheckConstant.Title.TIME)
                .append('\n')
                .append(date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")))
                .append(CheckConstant.DELIMITER)
                .append(time.truncatedTo(ChronoUnit.SECONDS).toString())
                .append('\n');
    }
}
