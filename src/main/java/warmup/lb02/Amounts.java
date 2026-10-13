package warmup.lb02;
import java.math.BigDecimal;

public class Amounts {
    public static BigDecimal parse(String text) {
        text = text.replace(",", ".").replace(" ", "");
        return new BigDecimal(text);
    };

}