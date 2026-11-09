package pl.zut.budget;
import pl.zut.budget.model.*;

import java.time.LocalDate;
import pl.zut.budget.repository.TransactionRepository;
import pl.zut.budget.repository.InMemoryTransactionRepository;
import java.time.format.DateTimeParseException;
import java.util.List;
import pl.zut.budget.exception.AppException;
import pl.zut.budget.exception.ValidationException;
import pl.zut.budget.exception.TransactionNotFoundException;

public class Main {

    private static final TransactionRepository repository = new InMemoryTransactionRepository();

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

            try {
                switch (choice.strip()) {
                    case "1" -> add();
                    case "2" -> list();
                    case "3" -> sum();
                    case "4" -> find();
                    case "5" -> remove();
                    case "0" -> running = false;
                    default -> IO.println("Nieznana opcja: " + choice);
                }
                IO.println("-------------------\n");
            } catch (AppException e) {
                IO.println("Błąd: " + e.getMessage());
            } catch (RuntimeException e) {
                IO.println("Błąd programu. Zgłoś go prowadzącemu.");
                e.printStackTrace(System.err);
            }

        }
        IO.println("Do zobaczenia.");
    }

    private static void printMenu() {
        IO.println("""
                1. Dodaj transakcję
                2. Lista transakcji
                3. Suma
                4. Znajdź transakcję po ID
                5. Usuń transakcję po ID
                0. Wyjście""");
    }

    private static void addSamples() {
        repository.add(Income.of(LocalDate.of(2026, 10, 1), Money.of("5200,00", CURRENCY), Category.SALARY, "Wynagrodzenie"));
        repository.add(Expense.of(LocalDate.of(2026, 10, 2), Money.of("45,90", CURRENCY), Category.FOOD, "Biedronka"));
        repository.add(Expense.of(LocalDate.of(2026, 10, 5), Money.of("120,00", CURRENCY), Category.TRANSPORT, "Orlen"));
        repository.add(Transfer.of(LocalDate.of(2026, 10, 6), Money.of("500,00", CURRENCY), "ROR", "Oszczędności", "Na wakacje"));
    }

    private static void add() {
        String type = read("Typ (1 przychód, 2 wydatek, 3 przelew): ");
        if (!type.equals("1") && !type.equals("2") && !type.equals("3")) {
            throw new ValidationException(List.of("Nieznany typ: " + type));
        }
        LocalDate date = readDate();
        Money amount = Money.of(read("Kwota (np. 45,90): "), CURRENCY);
        Transaction t = switch (type) {
            case "1" -> Income.of(date, amount, readCategory(), read("Opis: "));
            case "2" -> Expense.of(date, amount, readCategory(), read("Opis: "));
            case "3" -> Transfer.of(date, amount, read("Z konta: "), read("Na konto: "), read("Opis: "));
            default -> throw new ValidationException(List.of("Nieznany typ: " + type));
        };
        repository.add(t);
    }

    private static String read(String prompt) {
        String text = IO.readln(prompt);
        if (text == null) {
            throw new ValidationException(List.of("Przerwano wpisywanie danych"));
        }
        return text.strip();
    }

    private static LocalDate readDate() {
        String text = read("Data (RRRR-MM-DD): ");
        try {
            return LocalDate.parse(text);
        } catch (DateTimeParseException e) {
            throw new ValidationException(List.of("Zła data: " + text), e);
        }
    }

    private static Category readCategory() {
        Category[] all = Category.values();
        for (int i = 0; i < all.length; i++) {
            IO.println((i + 1) + ". " + all[i].getLabel());
        }
        String text = read("Kategoria (numer): ");
        int number;
        try {
            number = Integer.parseInt(text);
        } catch (NumberFormatException e) {
            throw new ValidationException(List.of("Zły numer kategorii: " + text), e);
        }
        if (number < 1 || number > all.length) {
            throw new ValidationException(List.of("Kategoria spoza zakresu 1-" + all.length));
        }
        return all[number - 1];
    }

    private static void list() {
        for (Transaction t : repository.findAll()) {
            IO.println(TransactionFormatter.describe(t));
        }
    }

    private static void sum() {
        Money income = Money.of("0", CURRENCY);
        Money expenses = Money.of("0", CURRENCY);
        Money balance = Money.of("0", CURRENCY);
        for (Transaction t : repository.findAll()) {
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

    private static long readId() {
        String text = read("Id transakcji: ");
        try {
            return Long.parseLong(text);
        } catch (NumberFormatException e) {
            throw new ValidationException(List.of("Zły numer transakcji: " + text), e);
        }
    }

    private static void find() {
        Transaction t = repository.findById(readId());
        IO.println(TransactionFormatter.describe(t));
    }

    private static void remove() {
        long id = readId();
        if (!repository.remove(id)) {
            throw new TransactionNotFoundException(id);
        }
        IO.println("Usunięto transakcję: " + id);
    }

}


//private static void add() {
//        try {
//            String type = IO.readln("Typ (1 przychód, 2 wydatek, 3 przelew): ").strip();
//            LocalDate date = LocalDate.parse(IO.readln("Data (RRRR-MM-DD): "));
//            Money amount = Money.of(IO.readln("Kwota (np. 45,90): "), CURRENCY);
//            Transaction t = switch (type) {
//                case "1" -> Income.of(date, amount, readCategory(), IO.readln("Opis: "));
//                case "2" -> Expense.of(date, amount, readCategory(), IO.readln("Opis: "));
//                case "3" -> Transfer.of(date, amount, IO.readln("Z konta: "), IO.readln("Na konto: "), IO.readln("Opis: "));
//                default -> throw new IllegalArgumentException("Nieznany typ: " + type);
//            };
//            repository.add(t);
//        } catch (RuntimeException e) {
//            IO.println("Nie dodano transakcji: " + e.getMessage());
//        }
//    }
//
//    private static Category readCategory() {
//        Category[] all = Category.values();
//        for (int i = 0; i < all.length; i++) {
//            IO.println((i + 1) + ". " + all[i].getLabel());
//        }
//        int number = Integer.parseInt(IO.readln("Kategoria (numer): ").strip());
//        return all[number - 1];
//    }

//private static void find() {
//    try {
//        long id = Long.parseLong(IO.readln("Id transakcji: ").strip());
//        Transaction t = repository.findById(id);
//        if (t == null) {
//            IO.println("Nie znaleziono transakcji: " + id);
//        } else {
//            IO.println(TransactionFormatter.describe(t));
//        }
//    } catch (RuntimeException e) {
//        IO.println("Nie wyszukano transakcji: " + e.getMessage());
//    }
//}
//
//private static void remove() {
//    try {
//        long id = Long.parseLong(IO.readln("Id transakcji: ").strip());
//        if (repository.remove(id)) {
//            IO.println("Usunięto transakcję: " + id);
//        } else {
//            IO.println("Nie znaleziono transakcji: " + id);
//        }
//    } catch (RuntimeException e) {
//        IO.println("Nie usunięto transakcji: " + e.getMessage());
//    }
//}