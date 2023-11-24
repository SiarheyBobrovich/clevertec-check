package ru.clevertec.check.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CheckConstant {

    public static final char CURRENCY = '$';
    public static final char PERCENTAGE = '%';
    public static final char DELIMITER = ';';

    @UtilityClass
    public static class Title {

        public static final String DATE = "Date";
        public static final String TIME = "Time";
    }

    @UtilityClass
    public static class Body {

        public static final String QTY = "QTY";
        public static final String DESCRIPTION = "DESCRIPTION";
        public static final String PRICE = "PRICE";
        public static final String DISCOUNT = "DISCOUNT";
        public static final String TOTAL = "TOTAL";
    }

    @UtilityClass
    public static class DiscountCard {

        public static final String DISCOUNT_CARD = "DISCOUNT CARD";
        public static final String DISCOUNT_PERCENTAGE = "DISCOUNT PERCENTAGE";
    }

    @UtilityClass
    public static class Total {

        public static final String TOTAL_PRICE = "TOTAL PRICE";
        public static final String TOTAL_DISCOUNT = "TOTAL DISCOUNT";
        public static final String TOTAL_WITH_DISCOUNT = "TOTAL WITH DISCOUNT";
    }

    public static class Exception {

        public static final String ERROR = "ERROR";
        public static final String BAD_REQUEST = "BAD REQUEST";
        public static final String INTERNAL_SERVER_ERROR = "INTERNAL SERVER ERROR";
        public static final String BALANCE_NOT_AVAILABLE = "NOT ENOUGH MONEY";
    }

}
