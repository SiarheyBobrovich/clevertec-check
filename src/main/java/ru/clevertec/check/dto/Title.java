package ru.clevertec.check.dto;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import ru.clevertec.check.constant.CheckConstant;

import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.time.LocalTime;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Title implements Printable {

    LocalDate date;
    LocalTime time;

    public Title() {
        this.date = LocalDate.now();
        this.time = LocalTime.now();
    }

    @Override
    public void print(Writer writer) throws IOException {
        writer.append(CheckConstant.Title.DATE)
                .append(CheckConstant.DELIMITER)
                .append(CheckConstant.Title.TIME)
                .append('\n')
                .append(date.toString())
                .append(CheckConstant.DELIMITER)
                .append(time.toString());
    }
}
