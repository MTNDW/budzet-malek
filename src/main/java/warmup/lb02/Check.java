package warmup.lb02;

public class Check {
    public static void main(String[] args) {
        String[] entries = {
                "2026-10-01;5200,00;Wynagrodzenie",
                "2026-10-02;-45,90;Biedronka",
                "2026-10-05;-120,00;ORLEN",
                "2026-09-28;-15,49;Spotify",
                "2026-10-07;-3,10;Żabka"
        };

        IO.println(Amounts.parse("-45,90"));
        IO.println(Amounts.parse("1 200,50"));

        IO.println(Entries.expensesSum(entries));
        IO.println(Entries.countInMonth(entries, 10));
        IO.println(Entries.countInMonth(entries, 9));

        for (String entry : entries) {
            String shop = entry.split(";")[2];
            IO.println(shop + " -> " + Categories.categoryOf(shop));
        }
    }
}
