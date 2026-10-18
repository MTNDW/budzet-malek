package warmup.lb03;

public class Shop {
    public static String hours(Weekday day) {
        return switch (day) {
            case MONDAY, WEDNESDAY, TUESDAY, THURSDAY, FRIDAY -> "8-20";
            case SATURDAY -> "9-15";
            case SUNDAY -> "zamknięte";
        };
    }

}
