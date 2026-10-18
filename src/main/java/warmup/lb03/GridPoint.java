package warmup.lb03;

public record GridPoint(int x, int y) {
    public GridPoint {
        if (x < 0 || y < 0 || x > 9 || y > 9) {
            throw new IllegalArgumentException("x = " + x + ",y = " + y);
        }
    }

    public static GridPoint of(String text) {
        String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        String letter = text.substring(0, 1).toUpperCase();
        int number = Integer.parseInt(text.substring(1));

        for (int i = 0; i < letters.length; i++) {
            if (letter.equals(letters[i])) {
                return new GridPoint(i, number - 1);
            }
        }

        throw new IllegalArgumentException();
    }
}
