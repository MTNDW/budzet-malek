package warmup.lb02;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Entries {

    public static BigDecimal expensesSum(String[] entries) {
        BigDecimal suma = new BigDecimal("0.00");

        for (String entry : entries) {
            BigDecimal kwota = Amounts.parse(entry.split(";")[1]);

            if (kwota.signum() == -1) {
                suma = suma.add(kwota);
            };
        }

        return suma;
    };

    public static int countInMonth(String[] entries, int month) {
        int count = 0;

        for (String entry : entries) {
            String text = entry.split(";")[0];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate data = LocalDate.parse(text, formatter);

            if (data.getMonthValue() == month) {
                count++;
            };

        };

        return count;
    };

}
