package pl.zut.budget.model;

import pl.zut.budget.exception.ValidationException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public record Money(BigDecimal amount, String currency) {
    public Money {
        List<String> errors = new ArrayList<>();
        if (amount == null) {
            errors.add("Brak kwoty");
        }
        if (currency == null || !currency.matches("[A-Z]{3}")) {
            errors.add("Zła waluta: " + currency);
        }
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
        amount = amount.setScale(2, RoundingMode.HALF_EVEN);
    }

    public static Money of(String text, String currency) {
        if (text == null || text.isBlank()) {
            throw new ValidationException(List.of("Brak kwoty"));
        }
        BigDecimal amount;
        try {
            amount = new BigDecimal(text.replace(" ", "").replace(',', '.'));
        } catch (NumberFormatException e) {
            throw new ValidationException(List.of("Zła kwota: " + text), e);
        }
        return new Money(amount, currency);
    }

    public Money plus(Money other) {
        checkSameCurrency(other);
        return new Money(amount.add(other.amount), currency);
    }

    public Money minus(Money other) {
        checkSameCurrency(other);
        return new Money(amount.subtract(other.amount), currency);
    }

    public boolean isNegative() { return amount.signum() < 0; }
    public boolean isPositive() { return amount.signum() > 0; }
    public Money negate() { return new Money(amount.negate(), currency); }

    private void checkSameCurrency(Money other) {
        if (!currency.equals(other.currency)) {
            throw new ValidationException(List.of("Różne waluty: " + currency + " i " + other.currency));
        }
    }
}

//package pl.zut.budget.model;
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//import java.util.Objects;
//
//public record Money(BigDecimal amount, String currency) {
//    public Money {
//        Objects.requireNonNull(amount, "amount");
//
//        if (!currency.matches("[A-Z]{3}")) {
//            throw new IllegalArgumentException();
//        }
//
//        amount = amount.setScale(2, RoundingMode.HALF_EVEN);
//    }
//
//    public static Money of(String text, String currency) {
//        text = text.replace(",", ".").replace(" ", "");
//        return new Money(new BigDecimal(text), currency);
//    }
//
//    public Money plus(Money other) {
//        if (!currency.equals(other.currency)) {
//            throw new IllegalArgumentException();
//        }
//
//        return new Money(amount.add(other.amount), currency);
//    };
//
//    public Money minus(Money other) {
//        if (!currency.equals(other.currency)) {
//            throw new IllegalArgumentException();
//        }
//
//        return new Money(amount.subtract(other.amount), currency);
//    };
//
//    public boolean isNegative() {
//        return amount.signum() == -1;
//    }
//
//    public boolean isPositive() {
//        return amount.signum() == 1;
//    }
//
//    public Money negate() {
//        return new Money(amount.negate(), currency);
//    }
//
//}
