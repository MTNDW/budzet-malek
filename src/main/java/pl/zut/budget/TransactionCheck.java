//package pl.zut.budget;
//
//import pl.zut.budget.model.Category;
//import pl.zut.budget.model.Expense;
//import pl.zut.budget.model.Income;
//import pl.zut.budget.model.Money;
//import pl.zut.budget.model.Transaction;
//import pl.zut.budget.model.Transfer;
//
//import java.time.LocalDate;
//
//public class TransactionCheck {
//    public static void main(String[] args) {
//        LocalDate day = LocalDate.of(2026, 10, 6);
//        Transaction a = Income.of(day, Money.of("5200", "PLN"), Category.SALARY, "Wynagrodzenie");
//        Transaction b = Expense.of(day, Money.of("45,9", "PLN"), Category.FOOD, "  Biedronka ");
//        Transaction c = Transfer.of(day, Money.of("500", "PLN"), "ROR", "Oszczędności", "Na wakacje");
//
//        IO.println(b);
//        IO.println(a.id() + " " + b.id() + " " + c.id());
//        IO.println(a.signedAmount().amount());
//        IO.println(b.signedAmount().amount());
//        IO.println(c.signedAmount().amount());
//
//        IO.println(TransactionFormatter.describe(a));
//        IO.println(TransactionFormatter.describe(b));
//        IO.println(TransactionFormatter.describe(c));
//
//        try {
//            Expense.of(day, Money.of("-45,90", "PLN"), Category.FOOD, "Biedronka");
//            IO.println("BŁĄD: Expense przyjął ujemną kwotę");
//        } catch (IllegalArgumentException e) {
//            IO.println("OK: " + e.getMessage());
//        }
//        try {
//            Income.of(day, Money.of("0", "PLN"), Category.OTHER, "Zwrot");
//            IO.println("BŁĄD: Income przyjął kwotę 0");
//        } catch (IllegalArgumentException e) {
//            IO.println("OK: " + e.getMessage());
//        }
//        try {
//            Transfer.of(day, Money.of("10", "PLN"), "ROR", " ROR ", "Test");
//            IO.println("BŁĄD: Transfer na to samo konto");
//        } catch (IllegalArgumentException e) {
//            IO.println("OK: " + e.getMessage());
//        }
//    }
//}