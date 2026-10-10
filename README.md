# Budżet

Projekt semestralny z przedmiotu Język Java: domowy rejestr transakcji rozwijany przez cały semestr. Każde laboratorium dokłada kolejną warstwę.

## Wymagania

- JDK 25
- Maven 3.9+
- IntelliJ IDEA (Community wystarczy)

Sprawdzenie:

```bash
java -version    # 25
mvn -v
```

## Uruchomienie

```bash
mvn compile exec:java
mvn test
```

## Struktura docelowa (od LB04)

```
pl.zut.budget
  model        Transaction, Income, Expense, Transfer, Money, Category, Account
  repository   Repository<T,ID>, InMemoryRepository, TransactionRepository
  service      TransactionService, ReportService, ImportService
  io           CsvParser, CsvTransactionStore, TransactionStore
  ui.console   ConsoleApp
  ui.swing     MainFrame, TransactionTableModel, TransactionFormPanel
  Main
warmup
  lb01 ... lb13   rozgrzewki
```

## Format CSV

```
date;type;amount;category;description
2026-10-01;INCOME;5200,00;SALARY;Wynagrodzenie
```

Przykładowe dane: `src/main/resources/sample.csv`.
Duży plik do LB10 i LB12: `java narzedzia/GenerateBigCsv.java big.csv 200000`.

## Zasady

- Po każdym laboratorium commit i tag `lbNN` (np. `git tag lb03 && git push origin main --tags`), potem ZIP z tagu do Moodle.
- Rozgrzewki w pakiecie `warmup.lbNN`.
- Pakiety `model` i `service` nie importują `javax.swing`.
