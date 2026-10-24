package pl.zut.budget;

import pl.zut.budget.model.Expense;
import pl.zut.budget.model.Income;
import pl.zut.budget.model.Money;
import pl.zut.budget.model.Transaction;
import pl.zut.budget.model.Transfer;

public class TransactionFormatter {

    public static String describe(Transaction t) {
        String details = switch (t) {
            case Income i -> money(i.amount()) + " [" + i.category().getLabel() + "]";
            case Expense e -> money(e.amount().negate()) + " [" + e.category().getLabel() + "]"; // kwota z minusem, kategoria jak wyżej
            case Transfer tr -> money(tr.amount()) + " [Przelew: ROR -> Oszczędności]";
        };
        return "#" + t.id() + " " + t.date() + " " + details + " " + t.description();
    }

    private static String money(Money m) {
        return m.amount() + " " + m.currency();
    }
}