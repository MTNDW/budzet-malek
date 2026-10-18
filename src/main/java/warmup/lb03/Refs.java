package warmup.lb03;

public class Refs {

    static void addTen(Counter c) {
        c.add(10);
    }

    static void replace(Counter c) {
        c = new Counter(100);
    }

    static void addTenToNumber(int n) {
        n += 10;
    }

    static void shout(String s) {
        s = s + "!";
    }

    static void addOneToAll(Counter[] counters) {
        for (Counter c : counters) {
            c.add(1);
        }
    }

    static void replaceFirst(Counter[] counters) {
        counters[0] = new Counter(0);
    }

    public static void main(String[] args) {
        // Typy primitywne są kopiowane,
        // Parametry objektu można zmieniać, (tablica to objekt)
        // Samego obiektu nie można. (Passujemy kopie referencji)

        Counter a = new Counter(5);
        addTen(a);
        IO.println("1: " + a.getValue());
        // 1 ---------
        // Przewiduje:
        // 15, zmiana nastąpi ponieważ wywołujemy metode klasy.
        // Wypisuje:
        // 15

        replace(a);
        IO.println("2: " + a.getValue());
        // 2 ---------
        // Przewiduje:
        // 15, funkcja nie może nadpisać obiektu.
        // Wypisuje:
        // 15

        int n = 5;
        addTenToNumber(n);
        IO.println("3: " + n);
        // 3 ---------
        // Przewiduje:
        // 5, int jest kopiowany, przez co operacja dodawania zawarta w funkcji modyfikuje
        // jedynie swoją zmienną
        // Wypisuje:
        // 5

        String s = "Java";
        shout(s);
        IO.println("4: " + s);
        // 4 ---------
        // Przewiduje:
        // Java, ten sam case typ prymitywny jest kopiowany, brak referencji uniemożliwia modyfikacje
        // Wypisuje:
        // Java

        Counter b = a;
        b.add(1);
        IO.println("5: " + a.getValue());
        // 5 ---------
        // Przewiduje:
        // 16, b wskazuje na ten sam objekt co a, wywołując metode klasy a modyfiktujemy zmienną
        // Wypisuje:
        // 16

        Counter[] counters = {a, new Counter(0)};
        addOneToAll(counters); // {17, 1} wywołuje metode klasy
        replaceFirst(counters); // {0, 1}, tablica to objekt, więc podając go jako argument modyfikujemy jego zawartość.
        IO.println("6: " + a.getValue() + " " + counters[0].getValue() + " " + counters[1].getValue());
        // 6 ---------
        // Przewiduje:
        // 17, 0, 0
        // Wypisuje:
        // 17, 0, 1 - myslałem że oba Countery sa zamieniane na 0, stąd mój błąd.
    }
}