package pl.zut.budget.model;

import java.time.LocalDate;

public sealed interface Transaction permits Income, Expense, Transfer {

    long id();

    LocalDate date();

    Money amount();

    String description();

    // przelew między własnymi kontami nie zmienia salda
    default Money signedAmount() {
        return switch (this) {
            case Income _ -> amount();
            case Expense _ -> amount().negate();
            case Transfer _ -> Money.of("0", amount().currency()); // zero w tej samej walucie: Money.of("0", amount().currency())
        };
    }
}
















//package pl.zut.budget.model;
//import java.time.LocalDate;
//import java.util.Objects;
//
//public class Transaction {
//    private final long id;
//    private final LocalDate date;
//    private final Money amount;
//    private final Category category;
//    private final String description;
//    private static long nextId = 1;
//
//    public long getId() {
//        return id;
//    }
//
//    public LocalDate getDate() {
//        return date;
//    }
//
//    public Money getAmount() {
//        return amount;
//    }
//
//    public Category getCategory() {
//        return category;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (o == null || getClass() != o.getClass()) return false;
//        Transaction that = (Transaction) o;
//        return id == that.id;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hashCode(id);
//    }
//
//    @Override
//    public String toString() {
//        return  "#" + id +
//                " " + date +
//                (amount.isNegative() ? " " : "  ") + amount.amount() +
//                " " + amount.currency() +
//                " [" + category.getLabel() + "]" +
//                " " + description;
//    }
//
//    public Transaction(LocalDate date, Money amount, Category category, String description) {
//        if (description == null || description.isBlank()) {
//            throw new IllegalArgumentException("description == null || description.isBlank()");
//        }
//
//        Objects.requireNonNull(date, "date == null");
//        Objects.requireNonNull(amount, "amount == null");
//        Objects.requireNonNull(category, "category == null");
//        Objects.requireNonNull(description, "description == null");
//
//        this.date = date;
//        this.amount = amount;
//        this.category = category;
//        this.description = description.strip();
//        this.id = nextId++;
//    }
//
//}
