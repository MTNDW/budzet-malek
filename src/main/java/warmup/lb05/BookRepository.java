package warmup.lb05;

import java.util.List;

public interface BookRepository {
    void add(Book book);
    List<Book> findAll();
    Book findById(long id);

    default int count() {
        return findAll().size();
    }
}