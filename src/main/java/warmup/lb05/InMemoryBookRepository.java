package warmup.lb05;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InMemoryBookRepository implements BookRepository {
    private final List<Book> books = new ArrayList<>();

    @Override
    public void add(Book book) {
        books.add(Objects.requireNonNull(book, "book"));
    }

    @Override
    public List<Book> findAll() {
        return new java.util.ArrayList<>(books);
    }

    @Override
    public Book findById(long id) {
        for (Book book : books) {
            if (id == book.id()) { return book; }
        }
        return null;
    }
}