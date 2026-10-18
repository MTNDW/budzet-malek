package pl.zut.budget.model;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public record Money(BigDecimal amount, String currency) {
    public Money {
        Objects.requireNonNull(amount, "amount");

        if (!currency.matches("[A-Z]{3}")) {
            throw new IllegalArgumentException();
        }

        amount = amount.setScale(2, RoundingMode.HALF_EVEN);
    }

    public static Money of(String text, String currency) {
        text = text.replace(",", ".").replace(" ", "");
        return new Money(new BigDecimal(text), currency);
    }

    public Money plus(Money other) {
        if (!currency.equals(other.currency)) {
            throw new IllegalArgumentException();
        }

        return new Money(amount.add(other.amount), currency);
    };

    public Money minus(Money other) {
        if (!currency.equals(other.currency)) {
            throw new IllegalArgumentException();
        }

        return new Money(amount.subtract(other.amount), currency);
    };

    public boolean isNegative() {
        return amount.signum() == -1;
    }

}
