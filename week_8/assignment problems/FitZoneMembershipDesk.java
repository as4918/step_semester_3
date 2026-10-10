interface MembershipPlan { String name(); int months(); double discount(); default double calculateFee(){return 1000.0*months()*(1-discount());} }
class MonthlyPlan implements MembershipPlan { public String name(){return "Monthly";} public int months(){return 1;} public double discount(){return 0;} }
class QuarterlyPlan implements MembershipPlan { public String name(){return "Quarterly";} public int months(){return 3;} public double discount(){return .10;} }
class AnnualPlan implements MembershipPlan { public String name(){return "Annual";} public int months(){return 12;} public double discount(){return .25;} }
class Membership {
    String member; MembershipPlan plan; private String status="Active";
    Membership(String m,MembershipPlan p){member=m;plan=p;System.out.printf("%s membership created for %s. Fee: ₹%.2f. Status: %s.%n",p.name(),m,p.calculateFee(),status);}
    void checkIn(){if(status.equals("Active"))System.out.println(member+" checked in successfully.");else System.out.println("Check-in denied: "+member+"'s membership is "+status+".");}
    void freeze(){if(status.equals("Expired"))System.out.println("Cannot freeze an Expired membership.");else if(status.equals("Frozen"))System.out.println("Membership is already Frozen.");else{status="Frozen";System.out.println(member+"'s membership frozen. Status: "+status+".");}}
    void unfreeze(){if(status.equals("Expired"))System.out.println("Cannot unfreeze an Expired membership.");else if(status.equals("Frozen")){status="Active";System.out.println(member+"'s membership unfrozen. Status: "+status+".");}else System.out.println("Membership is already Active.");}
    void expire(){status="Expired";System.out.println(member+"'s membership expired. Status: "+status+".");}
}
public class FitZoneMembershipDesk {
    public static void main(String[] args) {
        Membership a=new Membership("Asha",new QuarterlyPlan()),r=new Membership("Ravi",new MonthlyPlan());
        a.checkIn();a.freeze();a.checkIn();r.expire();r.freeze();
    }
}
