package pl.zut.budget;

import pl.zut.budget.model.Category;
import pl.zut.budget.model.Money;
import pl.zut.budget.model.Transaction;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class ModelCheck {
    public static void main(String[] args) {
        Money a = Money.of("12,5", "PLN");
        Money b = Money.of("12,50", "PLN");
        IO.println(a);
        IO.println(a.equals(b));
        IO.println(Money.of("1 200,00", "PLN").minus(a).amount());
        IO.println(a.plus(Money.of("-20", "PLN")).isNegative());

        try {
            Money.of("10", "zł");
            IO.println("BŁĄD: Money przyjęło walutę zł");
        } catch (IllegalArgumentException e) {
            IO.println("OK: zła waluta odrzucona");
        }
        try {
            a.plus(Money.of("1", "EUR"));
            IO.println("BŁĄD: plus dodał PLN do EUR");
        } catch (IllegalArgumentException e) {
            IO.println("OK: różne waluty odrzucone");
        }

        Transaction t1 = new Transaction(LocalDate.of(2026, 10, 2), Money.of("-45,90", "PLN"), Category.FOOD, "Biedronka");
        Transaction t2 = new Transaction(LocalDate.of(2026, 10, 2), Money.of("-45,90", "PLN"), Category.FOOD, "Biedronka");
        IO.println(t1);
        IO.println(t2);
        IO.println(t1.equals(t2));

        Set<Transaction> set = new HashSet<>();
        set.add(t1);
        set.add(t1);
        set.add(t2);
        IO.println(set.size());

        try {
            new Transaction(LocalDate.of(2026, 10, 3), Money.of("-5,00", "PLN"), Category.OTHER, "  ");
            IO.println("BŁĄD: Transaction przyjęła pusty opis");
        } catch (IllegalArgumentException e) {
            IO.println("OK: pusty opis odrzucony");
        }
        IO.println(Category.FOOD.getLabel());
    }
}