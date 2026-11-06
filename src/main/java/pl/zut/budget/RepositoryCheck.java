package pl.zut.budget;

import pl.zut.budget.model.Category;
import pl.zut.budget.model.Expense;
import pl.zut.budget.model.Income;
import pl.zut.budget.model.Money;
import pl.zut.budget.model.Transaction;
import pl.zut.budget.model.Transfer;
import pl.zut.budget.repository.InMemoryTransactionRepository;
import pl.zut.budget.repository.TransactionRepository;
import java.time.LocalDate;
import java.util.List;

public class RepositoryCheck {
    public static void main(String[] args) {
        TransactionRepository repository = new InMemoryTransactionRepository();
        IO.println("Puste: " + repository.count());
        IO.println("Brak: " + (repository.findById(999) == null));

        LocalDate day = LocalDate.of(2026, 10, 6);
        Transaction a = Income.of(day, Money.of("5200", "PLN"), Category.SALARY, "Wynagrodzenie");
        Transaction b = Expense.of(day, Money.of("45,90", "PLN"), Category.FOOD, "Kawa");
        Transaction c = Transfer.of(day, Money.of("500", "PLN"), "ROR", "Oszczędności", "Na wakacje");
        repository.add(a);
        repository.add(b);
        repository.add(c);
        IO.println("Po dodaniu: " + repository.count());
        IO.println("Pierwszy: " + (repository.findById(a.id()) == a));
        IO.println("Drugi: " + (repository.findById(b.id()) == b));
        IO.println("Trzeci: " + (repository.findById(c.id()) == c));
        IO.println(TransactionFormatter.describe(repository.findById(b.id())));

        List<Transaction> copy = repository.findAll();
        copy.clear();
        IO.println("Po wyczyszczeniu kopii: " + repository.count());
        copy.add(a);
        IO.println("Po dodaniu do kopii: " + repository.count());
        IO.println("Drugie repozytorium: " + new InMemoryTransactionRepository().count());

        IO.println("Usunięto: " + repository.remove(b.id()));
        IO.println("Pozostało: " + repository.count());
        IO.println("Usunięty nie istnieje: " + (repository.findById(b.id()) == null));
        IO.println("Ponowne usunięcie: " + repository.remove(b.id()));
        IO.println("Brak id: " + repository.remove(999));
        IO.println("Przelew został: " + (repository.findById(c.id()) == c));
        IO.println("Pierwszy został: " + (repository.findById(a.id()) == a));
        IO.println("Kolejność: " + repository.findAll().get(0).id() + " " + repository.findAll().get(1).id());

        try {
            repository.add(null);
            IO.println("BŁĄD: przyjęto null");
        } catch (NullPointerException e) {
            IO.println("OK: odrzucono null");
        }
        IO.println("Po próbie dodania null: " + repository.count());
    }
}