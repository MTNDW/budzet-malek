package pl.zut.budget.model;

public enum Category {
    SALARY("Wynagrodzenie"), FOOD("Jedzenie"), HOUSING("Mieszkanie"), TRANSPORT("Transport"), HEALTH("Zdrowie"), ENTERTAINMENT("Rozrywka"), OTHER("Inne");

    private final String label;
    Category(String label) { this.label = label; }

    public String getLabel() {
        return this.label;
    };
}