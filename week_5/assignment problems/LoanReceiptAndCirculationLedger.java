import java.util.Arrays;

/*
 * NOTE: The supplied problem requires LoanReceipt to be final while also
 * requiring ReferenceOnlyLoanReceipt to extend LoanReceipt. Those two
 * requirements conflict in Java. This version keeps the fields final and
 * uses a non-final base class so the required subclass and instanceof
 * processing can be implemented.
 */
public class LoanReceiptAndCirculationLedger {
    private final String memberId;
    private final String[] bookIds;

    public LoanReceiptAndCirculationLedger(
            String memberId, String[] bookIds) {
        this.memberId = memberId;
        this.bookIds = Arrays.copyOf(bookIds, bookIds.length);
    }

    public String[] getBookIds() {
        return Arrays.copyOf(bookIds, bookIds.length);
    }

    public LoanReceiptAndCirculationLedger withCorrectedBookId(
            int index, String newId) {
        String[] corrected = getBookIds();
        corrected[index] = newId;
        return new LoanReceiptAndCirculationLedger(memberId, corrected);
    }

    public static String processNightlyCirculation(
            LoanReceiptAndCirculationLedger[] receipts) {

        int processed = 0, nullSkipped = 0;
        int referenceOnly = 0, regular = 0;

        for (LoanReceiptAndCirculationLedger receipt : receipts) {
            if (receipt == null) {
                nullSkipped++;
                continue;
            }

            processed++;
            if (receipt instanceof ReferenceOnlyLoanReceipt)
                referenceOnly++;
            else
                regular++;
        }

        return processed + " processed | " +
               nullSkipped + " null skipped | " +
               referenceOnly + " reference-only | " +
               regular + " regular";
    }

    public static void main(String[] args) {
        LoanReceiptAndCirculationLedger r =
            new LoanReceiptAndCirculationLedger(
                "LIB-8841",
                new String[]{"BK-100", "BK-101"});

        String[] ids = r.getBookIds();
        ids[0] = "HACKED";

        System.out.println("Original first ID: " + r.getBookIds()[0]);

        LoanReceiptAndCirculationLedger corrected =
            r.withCorrectedBookId(1, "BK-102");

        System.out.println("Original: " +
            Arrays.toString(r.getBookIds()));
        System.out.println("Corrected: " +
            Arrays.toString(corrected.getBookIds()));

        LoanReceiptAndCirculationLedger[] receipts = {
            new ReferenceOnlyLoanReceipt(
                "LIB-001",
                new String[]{"BK-200"},
                "Reading Room 3"),
            null,
            new LoanReceiptAndCirculationLedger(
                "LIB-002",
                new String[]{"BK-201"})
        };

        System.out.println(processNightlyCirculation(receipts));
    }
}

class ReferenceOnlyLoanReceipt extends LoanReceiptAndCirculationLedger {
    private final String roomNumber;

    public ReferenceOnlyLoanReceipt(
            String memberId, String[] bookIds, String roomNumber) {
        super(memberId, bookIds);
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() { return roomNumber; }
}
