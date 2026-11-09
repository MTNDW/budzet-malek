package pl.zut.budget.model;

import pl.zut.budget.exception.ValidationException;
import java.time.LocalDate;
import java.util.List;

public record Transfer(long id, LocalDate date, Money amount, String from, String to, String description)
        implements Transaction {
    public Transfer {
        List<String> errors = TransactionValidation.transfer(date, amount, from, to, description);
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
        from = from.strip();
        to = to.strip();
        description = description.strip();
    }

    public static Transfer of(LocalDate date, Money amount, String from, String to, String description) {
        return new Transfer(TransactionIds.next(), date, amount, from, to, description);
    }
}

//package pl.zut.budget.model;
//
//import java.time.LocalDate;
//import java.util.Objects;
//
//public record Transfer(long id, LocalDate date, Money amount, String from, String to, String description)
//        implements Transaction {
//
//    public Transfer {
//        Objects.requireNonNull(date, "date");
//        Objects.requireNonNull(amount, "amount");
//        if (!amount.isPositive()) {
//            throw new IllegalArgumentException("Kwota musi być dodatnia: " + amount.amount());
//        }
//        if (from == null || from.isBlank() || to == null || to.isBlank()) {
//            throw new IllegalArgumentException("Brak nazwy konta");
//        }
//        from = from.strip();
//        to = to.strip();
//        if (from.equals(to)) {
//            throw new IllegalArgumentException("Przelew na to samo konto: " + from);
//        }
//        if (description == null || description.isBlank()) {
//            throw new IllegalArgumentException("Opis nie może być pusty");
//        }
//        description = description.strip();
//    }
//
//    public static Transfer of(LocalDate date, Money amount, String from, String to, String description) {
//        return new Transfer(TransactionIds.next(), date, amount, from, to, description);
//    }
//}