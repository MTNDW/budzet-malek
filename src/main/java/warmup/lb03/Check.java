package warmup.lb03;

import java.util.HashSet;
import java.util.Set;

public class Check {
    public static void main(String[] args) {
        Point p1 = new Point(1, 2);
        Point p2 = new Point(1, 2);
        IO.println(p1);
        IO.println(p1 == p2);
        IO.println(p1.equals(p2));
        IO.println(p1.equals(null));

        Set<Point> points = new HashSet<>();
        points.add(p1);
        IO.println(points.contains(p2));

        Set<PointNoHash> noHash = new HashSet<>();
        noHash.add(new PointNoHash(1, 2));
        IO.println(noHash.contains(new PointNoHash(1, 2)));
        IO.println(new PointNoHash(1, 2).equals(new PointNoHash(1, 2)));

        GridPoint g = GridPoint.of("B3");
        IO.println(g);
        IO.println(g.equals(new GridPoint(1, 2)));
        IO.println(GridPoint.of("J10"));
        try {
            GridPoint.of("K1");
            IO.println("BŁĄD: K1 przeszło walidację");
        } catch (IllegalArgumentException e) {
            IO.println("OK: K1 poza planszą");
        }
        try {
            GridPoint.of("A0");
            IO.println("BŁĄD: A0 przeszło walidację");
        } catch (IllegalArgumentException e) {
            IO.println("OK: A0 poza planszą");
        }

        IO.println(Weekday.FRIDAY.next());
        IO.println(Weekday.SUNDAY.next());
        IO.println(Weekday.MONDAY.isWorkingDay());
        IO.println(Weekday.SATURDAY.isWorkingDay());
        IO.println(Shop.hours(Weekday.WEDNESDAY));
        IO.println(Shop.hours(Weekday.SATURDAY));
        IO.println(Shop.hours(Weekday.SUNDAY));
    }
}