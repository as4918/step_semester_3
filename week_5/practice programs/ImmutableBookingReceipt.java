import java.util.Arrays;

/*
 * NOTE: The supplied problem says the base class must be final AND
 * GroupBookingReceipt must extend it. Java cannot satisfy both rules.
 * This implementation keeps the fields final and leaves the base class
 * non-final so the required subclass can exist.
 */
public class ImmutableBookingReceipt {
    private final String bookingId;
    private final String[] seatNumbers;

    public ImmutableBookingReceipt(String bookingId, String[] seatNumbers) {
        this.bookingId = bookingId;
        this.seatNumbers = Arrays.copyOf(seatNumbers, seatNumbers.length);
    }

    public String[] getSeatNumbers() {
        return Arrays.copyOf(seatNumbers, seatNumbers.length);
    }

    public ImmutableBookingReceipt withUpdatedSeat(int index, String newSeat) {
        String[] updated = getSeatNumbers();
        updated[index] = newSeat;
        return new ImmutableBookingReceipt(bookingId, updated);
    }

    public static String processNightlySettlement(
            ImmutableBookingReceipt[] receipts) {

        int processed = 0, nullSkipped = 0, group = 0, individual = 0;

        for (ImmutableBookingReceipt receipt : receipts) {
            if (receipt == null) {
                nullSkipped++;
                continue;
            }

            processed++;
            if (receipt instanceof GroupBookingReceipt) group++;
            else individual++;
        }

        return processed + " processed | " +
               nullSkipped + " null skipped | " +
               group + " group | " +
               individual + " individual";
    }

    public static void main(String[] args) {
        ImmutableBookingReceipt b =
            new ImmutableBookingReceipt("CH-1001",
                new String[]{"A1", "A2"});

        String[] seats = b.getSeatNumbers();
        seats[0] = "X";

        System.out.println("Original first seat: " +
                           b.getSeatNumbers()[0]);

        ImmutableBookingReceipt updated =
            b.withUpdatedSeat(1, "A3");

        System.out.println("Original: " +
            Arrays.toString(b.getSeatNumbers()));
        System.out.println("Updated: " +
            Arrays.toString(updated.getSeatNumbers()));

        ImmutableBookingReceipt[] receipts = {
            new GroupBookingReceipt("CH-2002",
                new String[]{"B1", "B2"}, 2),
            null,
            new ImmutableBookingReceipt("CH-3003",
                new String[]{"C1"})
        };

        System.out.println(processNightlySettlement(receipts));
    }
}

class GroupBookingReceipt extends ImmutableBookingReceipt {
    private final int groupSize;

    public GroupBookingReceipt(
            String bookingId, String[] seatNumbers, int groupSize) {
        super(bookingId, seatNumbers);
        this.groupSize = groupSize;
    }

    public int getGroupSize() { return groupSize; }
}
