package warmup.lb03;

public enum Weekday {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;

    private final boolean workingDay = isWorkingDay();

    public boolean isWorkingDay() {
        return this != SATURDAY && this != SUNDAY;
    }

    public Weekday next() {
        return Weekday.values()[(this.ordinal() + 1) % 7];
    }

}
