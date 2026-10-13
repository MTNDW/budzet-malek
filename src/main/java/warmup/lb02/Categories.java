package warmup.lb02;

public class Categories {
    public static String categoryOf(String shop) {
        return switch (shop.toLowerCase()) {
            case "biedronka", "lidl", "żabka" -> "JEDZENIE";
            case "orlen", "pkp", "jakdojade" -> "TRANSPORT";
            case "netflix", "spotify" -> "ROZRYWKA";
            default -> "INNE";
        };
    };

}
