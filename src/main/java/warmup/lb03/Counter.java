package warmup.lb03;

public class Counter {
    private int value;

    public Counter(int value) {
        this.value = value;
    }

    public void add(int n) {
        value += n;
    }

    public int getValue() {
        return value;
    }
}