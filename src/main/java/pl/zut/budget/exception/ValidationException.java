package pl.zut.budget.exception;

import java.util.List;

public class ValidationException extends AppException {
    private final List<String> violations;

    public ValidationException(List<String> violations) {
        this(violations, null);
    }

    public ValidationException(List<String> violations, Throwable cause) {
        super(String.join("; ", violations), cause);
        this.violations = List.copyOf(violations);
    }

    public List<String> violations() {
        return violations;
    }
}