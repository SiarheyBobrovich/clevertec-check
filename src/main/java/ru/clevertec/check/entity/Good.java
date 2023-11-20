package ru.clevertec.check.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@Table(name = "goods")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Good {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "description", nullable = false, length = 50, unique = true)
    String description;

    @Column(name = "price", nullable = false)
    BigDecimal price;

    @Column(name = "quantity_in_stock", columnDefinition = "CHECK (quantity_in_stock >= 0)")
    Integer quantityInStock;

    @Column(name = "wholesale_goods", nullable = false)
    Boolean wholesaleGoods;
}
