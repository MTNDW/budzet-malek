package pl.zut.budget;
import pl.zut.budget.model.Category;
import pl.zut.budget.model.Money;
import pl.zut.budget.model.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final int CAPACITY = 100;

    private static final String CURRENCY = "PLN";
    private static final List<Transaction> transactions = new ArrayList<>();
    private static int count = 0;

    public static void main(String[] args) {
        addSamples();
        IO.println("Budżet");
        boolean running = true;
        while (running) {
            printMenu();
            String choice = IO.readln("> ");
            if (choice == null) {
                break;
            }
            switch (choice.strip()) {
                case "1" -> add();
                case "2" -> list();
                case "3" -> sum();
                case "0" -> running = false;
                default -> IO.println("Nieznana opcja: " + choice);
            }
        }
        IO.println("Do zobaczenia.");
    }

    private static void printMenu() {
        IO.println("""

                1. Dodaj transakcję
                2. Lista transakcji
                3. Suma
                0. Wyjście
                """);
    }

    private static void addSamples() {
        transactions.add(new Transaction(LocalDate.parse("2026-10-01"), Money.of("5200,00", "PLN"), Category.SALARY, "Wynagrodzenie"));
        transactions.add(new Transaction(LocalDate.parse("2026-10-02"), Money.of("-45,90", "PLN"), Category.FOOD, "Biedronka"));
        transactions.add(new Transaction(LocalDate.parse("2026-10-05"), Money.of("-120,00", "PLN"), Category.TRANSPORT, "Orlen"));
    };

    private static void add() {
        try {
            if (count == CAPACITY) {
                IO.println("Brak miejsca.");
                return;
            }
            String date = IO.readln("Data (RRRR-MM-DD): ");
            String amount = IO.readln("Kwota (np. -45,90): ");
            String description = IO.readln("Opis: ");

            Category[] c = Category.values();

            for (int i = 0; i < c.length; i++) IO.println((i + 1) + ". " + c[i].getLabel());
            Category category = c[Integer.parseInt(IO.readln("Numer: ").strip()) - 1];

            transactions.add(new Transaction(LocalDate.parse(date), Money.of(amount, "PLN"), category, description));

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
        Money income = Money.of("0", CURRENCY);
        Money expenses = Money.of("0", CURRENCY);

        for (Transaction transaction : transactions) {
            Money curr = transaction.getAmount();
            if (curr.isNegative()) {
                expenses = expenses.minus(curr);
            } else {
                income = income.plus(curr);
            }
        }

        Money balance = income.minus(expenses);

        IO.println("Wpływy:  " + income.amount() + " " + CURRENCY);
        IO.println("Wydatki: " + expenses.amount() + " " + CURRENCY);
        IO.println("Saldo:   " + balance.amount() + " " + CURRENCY);
    }

}
