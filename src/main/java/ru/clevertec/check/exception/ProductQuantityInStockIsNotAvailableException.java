package ru.clevertec.check.exception;

import lombok.Getter;
import ru.clevertec.check.constant.CheckConstant;
import ru.clevertec.check.dto.response.Printable;

import java.io.IOException;
import java.io.Writer;
import java.text.MessageFormat;

@Getter
public class ProductQuantityInStockIsNotAvailableException extends Exception implements Printable {

    private final Integer available;
    private final Integer need;
    private final Long id;

    public ProductQuantityInStockIsNotAvailableException(Long id, Integer available, Integer need) {
        super(MessageFormat.format("BAD REQUEST, product id: {0}, available: {1}, needs: {2}", id, available, need));
        this.id = id;
        this.available = available;
        this.need = need;
    }

    @Override
    public void print(Writer writer) throws IOException {
        writer.append(CheckConstant.Exception.ERROR)
                .append('\n')
                .append(CheckConstant.Exception.BAD_REQUEST)
                .append('\n')
                .flush();
    }
}
