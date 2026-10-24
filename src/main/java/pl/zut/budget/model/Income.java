package pl.zut.budget.model;

import java.time.LocalDate;
import java.util.Objects;

public record Income(long id, LocalDate date, Money amount, Category category, String description)
        implements Transaction {

    public Income {
        Objects.requireNonNull(date, "date");
        Objects.requireNonNull(amount, "amount");
        Objects.requireNonNull(category, "category");
        if (!amount.isPositive()) {
            throw new IllegalArgumentException("Kwota musi być dodatnia: " + amount.amount());
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Opis nie może być pusty");
        }
        description = description.strip();
    }

    public static Income of(LocalDate date, Money amount, Category category, String description) {
        return new Income(TransactionIds.next(), date, amount, category, description);
    }
}