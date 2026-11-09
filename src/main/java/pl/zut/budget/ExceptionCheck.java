package pl.zut.budget;

import pl.zut.budget.exception.AppException;
import pl.zut.budget.exception.ValidationException;
import pl.zut.budget.exception.TransactionNotFoundException;
import pl.zut.budget.model.*;
import pl.zut.budget.repository.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ExceptionCheck {
    public static void main(String[] args) {
        TransactionRepository repository = new InMemoryTransactionRepository();
        LocalDate day = LocalDate.of(2026, 10, 6);
        Transaction a = Income.of(day, Money.of("100", "PLN"), Category.SALARY, "Praca");
        repository.add(a);
        IO.println("Znaleziono: " + (repository.findById(a.id()) == a));
        try {
            repository.findById(999);
            IO.println("BŁĄD: brak wyjątku dla id 999");
        } catch (TransactionNotFoundException e) {
            IO.println(e.getMessage());
            IO.println("Typ i id: " + e.type() + " " + e.id());
        }
        IO.println("Liczba po szukaniu: " + repository.count());
        try {
            Expense.of(null, Money.of("-5", "PLN"), null, " ");
            IO.println("BŁĄD: przyjęto zły wydatek");
        } catch (ValidationException e) {
            IO.println(e.getMessage());
            IO.println("Liczba błędów: " + e.violations().size());
        }
        try {
            Income.of(day, Money.of("0", "PLN"), Category.OTHER, "Zwrot");
            IO.println("BŁĄD: przyjęto zerowy przychód");
        } catch (ValidationException e) {
            IO.println(e.getMessage());
        }
        try {
            Transfer.of(day, Money.of("10", "PLN"), "ROR", " ROR ", " ");
            IO.println("BŁĄD: przyjęto zły przelew");
        } catch (ValidationException e) {
            IO.println(e.getMessage());
        }
        try {
            new Money(null, "pln");
            IO.println("BŁĄD: przyjęto zły Money");
        } catch (ValidationException e) {
            IO.println("Money: " + e.getMessage());
        }
        try {
            Money.of("abc", "PLN");
            IO.println("BŁĄD: przyjęto tekst jako kwotę");
        } catch (ValidationException e) {
            IO.println(e.getMessage());
            IO.println("Przyczyna: " + e.getCause().getClass().getSimpleName());
        }
        try {
            Income.of(day, Money.of("0", "PLN"), Category.OTHER, "Zwrot");
        } catch (AppException e) {
            IO.println("Złapano jako AppException: " + e.getClass().getSimpleName());
        }
        List<String> errors = new java.util.ArrayList<>();
        errors.add("Pierwszy błąd");
        ValidationException e = new ValidationException(errors);
        errors.clear();
        IO.println("Lista w wyjątku: " + e.violations());
        IO.println("Program działa dalej");
    }
}