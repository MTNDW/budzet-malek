package pl.zut.budget.repository;
import pl.zut.budget.exception.TransactionNotFoundException;

import pl.zut.budget.model.Transaction;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InMemoryTransactionRepository implements TransactionRepository {
    private final List<Transaction> transactions = new ArrayList<>();

    @Override
    public void add(Transaction transaction) {
        transactions.add(Objects.requireNonNull(transaction));
    }

    @Override
    public List<Transaction> findAll() {
        return new ArrayList<>(transactions);
    }

    @Override
    public Transaction findById(long id) {
        for (Transaction t : transactions) {
            if (t.id() == id) { return t; }
        }
        throw new TransactionNotFoundException(id);
    }

    @Override
    public boolean remove(long id) {
        for (int i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).id() == id) {
                transactions.remove(i);
                return true;
            }
        }
        return false;
    }
}