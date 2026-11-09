package pl.zut.budget.exception;

public class NotFoundException extends AppException {
    private final String type;
    private final long id;

    public NotFoundException(String type, long id) {
        super("Nie znaleziono: " + type + " " + id);
        this.type = type;
        this.id = id;
    }

    public String type() { return type; }
    public long id() { return id; }
}