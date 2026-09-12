public class BookCopyCirculationGuard {
    private final int copiesTotal;
    private int copiesAvailable;

    public BookCopyCirculationGuard(int copiesTotal) {
        if (copiesTotal <= 0)
            throw new IllegalArgumentException("copiesTotal must be positive");
        this.copiesTotal = copiesTotal;
        this.copiesAvailable = copiesTotal;
    }

    public void checkOut() {
        if (copiesAvailable > 0) copiesAvailable--;
    }

    public void checkIn() {
        if (copiesAvailable < copiesTotal) copiesAvailable++;
    }

    public int getCopiesAvailable() {
        return copiesAvailable;
    }

    public static void main(String[] args) {
        BookCopyCirculationGuard b = new BookCopyCirculationGuard(3);

        b.checkOut();
        b.checkOut();
        b.checkOut();
        b.checkOut();
        System.out.println("Available after check-outs: " + b.getCopiesAvailable());

        b.checkIn();
        b.checkIn();
        b.checkIn();
        b.checkIn();
        System.out.println("Available after check-ins: " + b.getCopiesAvailable());
    }
}
