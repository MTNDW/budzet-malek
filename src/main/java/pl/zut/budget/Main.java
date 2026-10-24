package pl.zut.budget;
import pl.zut.budget.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final List<Transaction> transactions = new ArrayList<>();

    private static final String CURRENCY = "PLN";

    static void main(String[] args) {
        boolean running = true; addSamples();

        while (running) {
            printMenu();

            String choice = IO.readln("• ");
            if (choice == null) {
                break;
            }

            IO.println("\n-------------------");

            switch (choice.strip()) {
                case "1" -> add();
                case "2" -> list();
                case "3" -> sum();
                case "0" -> running = false;
                default -> IO.println("Nieznana opcja: " + choice);
            }
            IO.println("-------------------\n");

        }
        IO.println("Do zobaczenia.");
    }

    private static void printMenu() {
        IO.println("""
                1. Dodaj transakcję
                2. Lista transakcji
                3. Suma
                0. Wyjście""");
    }

    private static void addSamples() {
        transactions.add(Income.of(LocalDate.of(2026, 10, 1), Money.of("5200,00", CURRENCY), Category.SALARY, "Wynagrodzenie"));
        transactions.add(Expense.of(LocalDate.of(2026, 10, 2), Money.of("45,90", CURRENCY), Category.FOOD, "Biedronka"));
        transactions.add(Expense.of(LocalDate.of(2026, 10, 5), Money.of("120,00", CURRENCY), Category.TRANSPORT, "Orlen"));
        transactions.add(Transfer.of(LocalDate.of(2026, 10, 6), Money.of("500,00", CURRENCY), "ROR", "Oszczędności", "Na wakacje"));
    }

    private static void add() {
        try {
            String type = IO.readln("Typ (1 przychód, 2 wydatek, 3 przelew): ").strip();
            LocalDate date = LocalDate.parse(IO.readln("Data (RRRR-MM-DD): "));
            Money amount = Money.of(IO.readln("Kwota (np. 45,90): "), CURRENCY);
            Transaction t = switch (type) {
                case "1" -> Income.of(date, amount, readCategory(), IO.readln("Opis: "));
                case "2" -> Expense.of(date, amount, readCategory(), IO.readln("Opis: "));
                case "3" -> Transfer.of(date, amount, IO.readln("Z konta: "), IO.readln("Na konto: "), IO.readln("Opis: "));
                default -> throw new IllegalArgumentException("Nieznany typ: " + type);
            };
            transactions.add(t);
        } catch (RuntimeException e) {
            IO.println("Nie dodano transakcji: " + e.getMessage());
        }
    }

    private static Category readCategory() {
        Category[] all = Category.values();
        for (int i = 0; i < all.length; i++) {
            IO.println((i + 1) + ". " + all[i].getLabel());
        }
        int number = Integer.parseInt(IO.readln("Kategoria (numer): ").strip());
        return all[number - 1];
    }

    private static void list() {
        for (Transaction t : transactions) {
            IO.println(TransactionFormatter.describe(t));
        }
    }

    private static void sum() {
        Money income = Money.of("0", CURRENCY);
        Money expenses = Money.of("0", CURRENCY);
        Money balance = Money.of("0", CURRENCY);
        for (Transaction t : transactions) {
            switch (t) {
                case Income i -> income = income.plus(i.amount());
                case Expense e -> expenses = expenses.plus(e.amount());
                case Transfer _ -> { }
            }
            balance = balance.plus(t.signedAmount());
        }
        IO.println("Wpływy:  " + income.amount() + " " + CURRENCY);
        IO.println("Wydatki: " + expenses.amount() + " " + CURRENCY);
        IO.println("Saldo:   " + balance.amount() + " " + CURRENCY);
    }

}
