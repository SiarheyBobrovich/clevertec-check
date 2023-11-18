package ru.clevertec.check.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Bucket {

    @Valid
    @NotNull
    @NotEmpty
    @JsonProperty("goods")
    private final List<GoodDto> goods;

    @Valid
    @NotNull
    @Builder.Default
    private final DiscountCardDto discountCard = new DiscountCardDto();

    @JsonProperty(value = "discountcard")
    public void setDiscountCard(String discountCard) {
        this.discountCard.setNumber(discountCard);
    }

    @JsonProperty(value = "balancedebitcard")
    public void setBalanceDebitCard(String balanceDebitCard) {
        this.discountCard.setBalance(balanceDebitCard);
    }
}
