package warmup.lb03;

public class PointNoHash {
    private final int x;
    private final int y;

    public PointNoHash(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PointNoHash that = (PointNoHash) o; // Castuje o na Point

        return that.x == x && that.y == y;
    }
}
