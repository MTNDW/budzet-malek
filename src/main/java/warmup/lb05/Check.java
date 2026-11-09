package warmup.lb05;

import java.util.List;

public class Check {
    public static void main(String[] args) {
        BookRepository repository = new InMemoryBookRepository();
        IO.println("Puste: " + repository.count());
        IO.println("Brak: " + (repository.findById(999) == null));
        Book a = new Book(10, "Java");
        Book b = new Book(30, "Algorytmy");
        Book c = new Book(80, "Sieci");
        repository.add(a);
        repository.add(b);
        repository.add(c);
        IO.println("Liczba: " + repository.count());
        IO.println("Pierwsza: " + (repository.findById(10) == a));
        IO.println("Druga: " + (repository.findById(30) == b));
        IO.println("Trzecia: " + (repository.findById(80) == c));
        IO.println("Nie ma: " + (repository.findById(20) == null));
        IO.println(repository.findAll());
        List<Book> copy = repository.findAll();
        copy.clear();
        IO.println("Po clear kopii: " + repository.count());
        copy.add(new Book(99, "Test"));
        IO.println("Po add do kopii: " + repository.count());
        IO.println("Książka 99 w repo: " + (repository.findById(99) != null));
        repository.add(new Book(90, "Bazy danych"));
        IO.println("Po add do repo: " + repository.count());
        IO.println("Stara kopia: " + copy.size());
        IO.println("Drugie repo: " + new InMemoryBookRepository().count());
    }
}