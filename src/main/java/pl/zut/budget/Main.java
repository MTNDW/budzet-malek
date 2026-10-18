package pl.zut.budget;
import pl.zut.budget.model.Category;
import pl.zut.budget.model.Money;
import pl.zut.budget.model.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final List<Transaction> transactions = new ArrayList<>();
    private static final int CAPACITY = 100;

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
        List<Transaction> samples = List.of(
                new Transaction(LocalDate.parse("2026-10-01"), Money.of("5200,00", "PLN"), Category.SALARY, "Wynagrodzenie"),
                new Transaction(LocalDate.parse("2026-10-02"), Money.of("-45,90", "PLN"), Category.FOOD, "Biedronka"),
                new Transaction(LocalDate.parse("2026-10-05"), Money.of("-120,00", "PLN"), Category.TRANSPORT, "Orlen")
        );

        transactions.addAll(samples);
    }

    private static void add() {
        try {
            if (transactions.size() == CAPACITY) {
                IO.println("Brak miejsca."); return;
            }
            LocalDate date = LocalDate.parse(IO.readln("Data (RRRR-MM-DD): \n"));
            Money amount = Money.of(IO.readln("Kwota (np. -45,90): \n"), CURRENCY);
            String description = IO.readln("Opis: \n");
            IO.println("Kategoria: (1-7)");

            Category[] categories = Category.values();
            for (int i = 0; i < categories.length; i++) {
                IO.println((i + 1) + ". " + categories[i].getLabel());
            }
            Category category = categories[Integer.parseInt(IO.readln("• ").strip()) - 1];

            transactions.add(new Transaction(date, amount, category, description));

        } catch (RuntimeException e) {
            IO.println("Nie dodano transakcji: " + e.getMessage());
        }
    }

    private static void list() {
        for (Transaction transaction : transactions) {
            IO.println(transaction.toString());
        }
    }

    private static void sum() {
        Money income = Money.of("0", CURRENCY); Money expenses = Money.of("0", CURRENCY);

        for (Transaction transaction : transactions) {
            Money currAmount = transaction.getAmount();
            if (!currAmount.isNegative()) {
                income = income.plus(currAmount);
            } else {
                expenses = expenses.minus(currAmount);
            }
        }

        IO.println("Wpływy:  " + income.amount() + " " + CURRENCY);
        IO.println("Wydatki: " + expenses.amount() + " " + CURRENCY);
        IO.println("Saldo:   " + income.minus(expenses).amount() + " " + CURRENCY);
    }

}
