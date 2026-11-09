package pl.zut.budget.exception;

public class TransactionNotFoundException extends NotFoundException {
    public TransactionNotFoundException(long id) {
        super("transakcja", id);
    }
}