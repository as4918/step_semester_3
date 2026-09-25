public abstract class DeliveryNote {
    protected final String trackingId;
    public DeliveryNote(String trackingId) { this.trackingId = trackingId; }
    public abstract String confirmDelivery();
    public String confirmDelivery(String signature) {
        return confirmDelivery() + ", signed by " + signature;
    }
}