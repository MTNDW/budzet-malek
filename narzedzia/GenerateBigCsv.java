// Generator dużego pliku CSV do LB10 i LB12.
// Uruchomienie: java narzedzia/GenerateBigCsv.java big.csv 200000

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Locale;
import java.util.random.RandomGenerator;

void main(String[] args) throws Exception {
    Path out = Path.of(args.length > 0 ? args[0] : "big.csv");
    int lines = args.length > 1 ? Integer.parseInt(args[1]) : 200_000;

    String[] categories = {"FOOD", "HOUSING", "TRANSPORT", "HEALTH", "ENTERTAINMENT", "OTHER"};
    String[] descriptions = {"Zakupy", "Rachunek", "Bilet", "Apteka", "Kino", "Prezent"};
    RandomGenerator rnd = RandomGenerator.getDefault();
    LocalDate start = LocalDate.of(2020, 1, 1);

    try (var w = Files.newBufferedWriter(out)) {
        w.write("date;type;amount;category;description");
        w.newLine();
        for (int i = 0; i < lines; i++) {
            LocalDate date = start.plusDays(rnd.nextInt(365 * 6));
            boolean income = rnd.nextInt(10) == 0;
            double amount = income ? 1000 + rnd.nextDouble() * 6000 : 5 + rnd.nextDouble() * 500;
            int c = rnd.nextInt(categories.length);
            String line = "%s;%s;%s;%s;%s %d".formatted(
                    date,
                    income ? "INCOME" : "EXPENSE",
                    String.format(Locale.forLanguageTag("pl-PL"), "%.2f", amount),
                    income ? "SALARY" : categories[c],
                    income ? "Wynagrodzenie" : descriptions[c],
                    i + 1);
            w.write(line);
            w.newLine();
        }
    }
    IO.println("Zapisano " + lines + " linii do " + out.toAbsolutePath());
}
