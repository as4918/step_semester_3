public class Payment {
    double lastCharged;

    void pay(double amount) {
        lastCharged = amount;
        System.out.println("Paid (cash): Rs " + amount);
    }

    static class CardPayment extends Payment {
        void payWithProcessingFee(double amount) {
            lastCharged = amount * 1.02;
            System.out.println("Charged (card, incl. fee): Rs " + lastCharged);
        }
    }

    static void processTransaction(Payment payment, double amount) {
        if (payment instanceof CardPayment) {
            CardPayment cardPayment = (CardPayment) payment;
            cardPayment.payWithProcessingFee(amount);
        } else {
            payment.pay(amount);
        }
    }

    public static void main(String[] args) {
        Payment[] payments = {
            new CardPayment(), new Payment(), new CardPayment(),
            new Payment(), new CardPayment()
        };

        double[] amounts = {100, 50, 200, 75, 120};
        double totalCollected = 0;

        for (int i = 0; i < payments.length; i++) {
            processTransaction(payments[i], amounts[i]);
            totalCollected += payments[i].lastCharged;
        }

        System.out.println("Total Collected: Rs " + totalCollected);
    }
}
