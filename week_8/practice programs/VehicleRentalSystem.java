import java.util.*;

abstract class Vehicle {
    private final String id;
    private boolean available = true;
    Vehicle(String id) { this.id = id; }
    String getId() { return id; }
    boolean isAvailable() { return available; }
    void setAvailable(boolean value) { available = value; }
    abstract double calculateCharge(int days);
}
class Sedan extends Vehicle {
    Sedan(String id) { super(id); }
    double calculateCharge(int days) { return days * 50.0; }
}
class SUV extends Vehicle {
    SUV(String id) { super(id); }
    double calculateCharge(int days) { return days * 80.0; }
}
class Truck extends Vehicle {
    Truck(String id) { super(id); }
    double calculateCharge(int days) { return days * 120.0; }
}
class Rental {
    String customer;
    Vehicle vehicle;
    int days;
    Rental(String customer, Vehicle vehicle, int days) {
        this.customer=customer; this.vehicle=vehicle; this.days=days;
    }
}
public class VehicleRentalSystem {
    static Rental rent(String customer, Vehicle vehicle, int days) {
        if (!vehicle.isAvailable()) {
            System.out.println(vehicle.getId()+" is currently unavailable."); return null;
        }
        if (days <= 0) { System.out.println("Rental days must be positive."); return null; }
        vehicle.setAvailable(false);
        Rental r=new Rental(customer,vehicle,days);
        System.out.printf("%s rented successfully by %s. Rental charge: $%.2f.%n",
                vehicle.getId(),customer,vehicle.calculateCharge(days));
        return r;
    }
    static void returnVehicle(Rental r) {
        if (r != null && !r.vehicle.isAvailable()) {
            r.vehicle.setAvailable(true);
            System.out.println(r.vehicle.getId()+" returned by "+r.customer+".");
        }
    }
    public static void main(String[] args) {
        Vehicle sedan=new Sedan("Sedan A"), suv=new SUV("SUV B");
        Rental r1=rent("Customer 1",sedan,3);
        rent("Customer 2",sedan,2);
        returnVehicle(r1);
        rent("Customer 3",suv,5);
    }
}
