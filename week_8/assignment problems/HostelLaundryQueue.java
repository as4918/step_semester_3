import java.util.*;
interface WashType { String getName(); int duration(); double charge(); }
class QuickWash implements WashType { public String getName(){return "Quick";} public int duration(){return 30;} public double charge(){return 20;} }
class NormalWash implements WashType { public String getName(){return "Normal";} public int duration(){return 45;} public double charge(){return 30;} }
class HeavyWash implements WashType { public String getName(){return "Heavy";} public int duration(){return 60;} public double charge(){return 45;} }
class WashCycle {
    String student; WashingMachine machine; WashType type;
    WashCycle(String s,WashingMachine m,WashType t){student=s;machine=m;type=t;}
}
class WashingMachine {
    private final String id; private WashCycle current;
    WashingMachine(String id){this.id=id;}
    String getId(){return id;} boolean isFree(){return current==null;}
    boolean start(String student,WashType type) {
        if(!isFree()){System.out.println("Machine "+id+" is currently busy.");return false;}
        current=new WashCycle(student,this,type);
        System.out.printf("%s wash started on %s for %s (%d min). Charge: ₹%.2f.%n",type.getName(),id,student,type.duration(),type.charge());
        return true;
    }
    void complete() {
        if(isFree()){System.out.println(id+" is already free.");return;}
        System.out.println(id+" cycle completed. "+id+" is now free.");current=null;
    }
}
public class HostelLaundryQueue {
    public static void main(String[] args) {
        WashingMachine m1=new WashingMachine("M1"),m2=new WashingMachine("M2");
        m1.start("Asha",new QuickWash());m1.start("Ravi",new HeavyWash());
        m2.start("Ravi",new HeavyWash());m1.complete();m1.start("Neha",new NormalWash());
    }
}
