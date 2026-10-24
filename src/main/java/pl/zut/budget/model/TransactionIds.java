package pl.zut.budget.model;

class TransactionIds {

    private static long nextId = 1;

    static long next() {
        return nextId++;
    }
}