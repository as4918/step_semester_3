import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;
abstract class Room {
    String id; double rate;
    Room(String id,double rate){this.id=id;this.rate=rate;}
    double price(LocalDate start,LocalDate end){return rate*ChronoUnit.DAYS.between(start,end);}
}
class StandardRoom extends Room { StandardRoom(String id){super(id,100);} }
class DeluxeRoom extends Room { DeluxeRoom(String id){super(id,180);} }
class Suite extends Room { Suite(String id){super(id,300);} }
class Reservation {
    String customer; Room room; LocalDate start,end,deadline; boolean active=true;
    Reservation(String c,Room r,LocalDate s,LocalDate e,LocalDate d){customer=c;room=r;start=s;end=e;deadline=d;}
    boolean overlaps(LocalDate s,LocalDate e){return active && s.isBefore(end) && start.isBefore(e);}
}
public class HotelBookingSystem {
    static List<Reservation> reservations=new ArrayList<>();
    static boolean available(Room r,LocalDate s,LocalDate e) {
        for(Reservation x:reservations) if(x.room==r && x.overlaps(s,e)) return false;
        return true;
    }
    static Reservation book(String c,Room r,LocalDate s,LocalDate e,LocalDate deadline) {
        if(!e.isAfter(s)){System.out.println("End date must be after start date.");return null;}
        if(!available(r,s,e)){System.out.println(r.getClass().getSimpleName()+" "+r.id+" is not available.");return null;}
        Reservation x=new Reservation(c,r,s,e,deadline); reservations.add(x);
        System.out.printf("Reservation confirmed for %s, %s %s (%s-%s). Price: $%.2f.%n",
                c,r.getClass().getSimpleName(),r.id,s,e,r.price(s,e)); return x;
    }
    static void cancel(Reservation r,LocalDate today) {
        if(r==null || !r.active){System.out.println("Reservation is not active.");return;}
        if(today.isAfter(r.deadline)){System.out.println("Cancellation deadline has passed.");return;}
        r.active=false;System.out.println("Reservation for "+r.customer+", room "+r.room.id+" cancelled successfully.");
    }
    public static void main(String[] args) {
        Room standard=new StandardRoom("101"), deluxe=new DeluxeRoom("201");
        LocalDate s=LocalDate.of(2027,1,1), e=LocalDate.of(2027,1,5);
        System.out.println(available(standard,s,e)?"Standard Room 101 is available.":"Not available.");
        Reservation a=book("Customer A",standard,s,e,s.minusDays(1));
        book("Customer B",standard,LocalDate.of(2027,1,3),LocalDate.of(2027,1,7),s);
        cancel(a,LocalDate.of(2026,12,20));
        book("Customer C",deluxe,LocalDate.of(2027,2,10),LocalDate.of(2027,2,12),LocalDate.of(2027,2,1));
    }
}
