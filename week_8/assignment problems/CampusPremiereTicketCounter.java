import java.util.*;
abstract class Seat {
    String id; Seat(String id){this.id=id;} abstract double price();
}
class RegularSeat extends Seat { RegularSeat(String id){super(id);} double price(){return 150;} }
class PremiumSeat extends Seat { PremiumSeat(String id){super(id);} double price(){return 250;} }
class ReclinerSeat extends Seat { ReclinerSeat(String id){super(id);} double price(){return 400;} }
class Customer { String name; Customer(String n){name=n;} }
class Show {
    String name; boolean started=false; Map<String,Seat> booked=new HashMap<>();
    Show(String n){name=n;}
    boolean reserve(Seat s){if(booked.containsKey(s.id))return false;booked.put(s.id,s);return true;}
    void release(Seat s){booked.remove(s.id);}
}
class Booking {
    Customer customer; Show show; List<Seat> seats=new ArrayList<>(); boolean active=true;
    Booking(Customer c,Show s,List<Seat> selected){customer=c;show=s;
        if(selected.isEmpty()||selected.size()>6){System.out.println("Booking must contain 1 to 6 seats.");active=false;return;}
        for(Seat seat:selected)if(show.booked.containsKey(seat.id)){System.out.println("Seat "+seat.id+" is already booked for this show.");active=false;return;}
        for(Seat seat:selected){show.reserve(seat);seats.add(seat);}
        System.out.printf("Booking confirmed for %s: %s. Total: ₹%.2f.%n",c.name,seatNames(),total());
    }
    String seatNames(){List<String> ids=new ArrayList<>();for(Seat s:seats)ids.add(s.id);return String.join(", ",ids);}
    double total(){double sum=0;for(Seat s:seats)sum+=s.price();return sum;}
    void cancel(){if(!active){System.out.println("Booking is not active.");return;}
        if(show.started){System.out.println("Cannot cancel after the show starts.");return;}
        for(Seat s:seats)show.release(s);active=false;
        System.out.println(customer.name+"'s booking cancelled. Seats "+seatNames()+" released.");
    }
}
public class CampusPremiereTicketCounter {
    public static void main(String[] args) {
        Show show=new Show("7 PM");Customer asha=new Customer("Asha"),ravi=new Customer("Ravi"),neha=new Customer("Neha");
        Booking a=new Booking(asha,show,Arrays.asList(new RegularSeat("A1"),new RegularSeat("A2"),new PremiumSeat("F5")));
        new Booking(ravi,show,Arrays.asList(new RegularSeat("A2")));
        new Booking(ravi,show,Arrays.asList(new ReclinerSeat("R1")));
        a.cancel();new Booking(neha,show,Arrays.asList(new RegularSeat("A2")));
    }
}
