package pl.zut.budget.repository;

import pl.zut.budget.model.Transaction;
import java.util.List;

public interface TransactionRepository {
    void add(Transaction transaction);
    List<Transaction> findAll();
    Transaction findById(long id);
    boolean remove(long id);

    default int count() {
        return findAll().size();
    }
}