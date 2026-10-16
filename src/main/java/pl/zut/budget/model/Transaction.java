package pl.zut.budget.model;
import java.time.LocalDate;
import java.util.Objects;

public class Transaction {
    private final long id;
    private final LocalDate date;
    private final Money amount;
    private final Category category;
    private final String description;

    private static long nextId = 1;

    public long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Money getAmount() {
        return amount;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public String getDescription() {
        return description;
    }

    public Transaction(LocalDate date, Money amount, Category category, String description) {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException();
        }

        Objects.requireNonNull(date, "date");
        Objects.requireNonNull(amount, "amount");
        Objects.requireNonNull(category, "category");
        Objects.requireNonNull(description, "description");

        String strippedDescription = description.strip();

        this.date = date;
        this.amount = amount;
        this.category = category;
        this.description = strippedDescription;
        this.id = nextId++;
    }

    @Override
    public String toString() {
        return  "#" + id +
                " " + date +
                " " + amount.amount() +
                " " + amount.currency() +
                " [" + category.getLabel() + "]" +
                " " + description;
    }
}
