package pl.zut.budget.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

final class TransactionValidation {
    static List<String> common(LocalDate date, Money amount, String description) {
        List<String> errors = new ArrayList<>();
        if (date == null) {
            errors.add("Brak daty");
        }
        if (amount == null) {
            errors.add("Brak kwoty");
        } else if (!amount.isPositive()) {
            errors.add("Kwota musi być dodatnia: " + amount.amount());
        }
        if (description == null || description.isBlank()) {
            errors.add("Opis nie może być pusty");
        }
        return errors;
    }

    static List<String> categorized(LocalDate date, Money amount,
                                    Category category, String description) {
        List<String> errors = common(date, amount, description);
        if (category == null) {
            errors.add("Brak kategorii");
        }
        return errors;
    }

    static List<String> transfer(LocalDate date, Money amount,
                                 String from, String to, String description) {
        List<String> errors = common(date, amount, description);
        if (from == null || from.isBlank() || to == null || to.isBlank()) {
            errors.add("Brak nazwy konta");
        } else if (from.strip().equals(to.strip())) {
            errors.add("Przelew na to samo konto: " + from.strip());
        }
        return errors;
    }
}