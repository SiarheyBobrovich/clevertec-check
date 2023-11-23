package ru.clevertec.check.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DiscountCardDto {

    @Pattern(regexp = "^\\d{4}")
    private String number;

    @NotNull
    @Pattern(regexp = "^-?\\d++\\.?\\d*")
    private String balance;

    public Integer getNumber() {
        return number == null ? null : Integer.parseInt(number);
    }

    public BigDecimal getBalance() {
        return new BigDecimal(balance);
    }
}
