package pl.zut.budget;

/**
 * Punkt startowy projektu. Na razie transakcje są trzymane jako napisy w tablicy.
 * Na LB02 zastąpisz to klasami z pakietu {@code model}.
 */
public class Main {

    private static final int CAPACITY = 100;

    // Format wpisu: "data;kwota;opis", np. "2026-10-01;-45,90;Zakupy"
    private static final String[] transactions = new String[CAPACITY];
    private static int count = 0;

    public static void main(String[] args) {
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
                0. Wyjście""");
    }

    private static void add() {
        if (count == CAPACITY) {
            IO.println("Brak miejsca.");
            return;
        }
        String date = IO.readln("Data (RRRR-MM-DD): ");
        String amount = IO.readln("Kwota (np. -45,90): ");
        String description = IO.readln("Opis: ");
        // TODO walidacja danych
        transactions[count++] = date + ";" + amount + ";" + description;
    }

    private static void list() {
        for (int i = 0; i < count; i++) {
            IO.println((i + 1) + ". " + transactions[i]);
        }
    }

    private static void sum() {
        // TODO policz sumę kwot (uwaga: przecinek jako separator dziesiętny)
        IO.println("TODO");
    }
}
