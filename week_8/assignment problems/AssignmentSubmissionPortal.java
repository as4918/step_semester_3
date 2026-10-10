import java.time.*;
import java.time.temporal.ChronoUnit;
abstract class Assignment {
    String title; int maxMarks; LocalDate due;
    Assignment(String t,int m,LocalDate d){title=t;maxMarks=m;due=d;}
    abstract double penaltyPerDay();
    double finalMarks(double awarded,long lateDays){return Math.max(0,awarded*(1-penaltyPerDay()*lateDays));}
}
class CodingAssignment extends Assignment {
    CodingAssignment(String t,int m,LocalDate d){super(t,m,d);}
    double penaltyPerDay(){return 0.10;}
}
class WrittenAssignment extends Assignment {
    WrittenAssignment(String t,int m,LocalDate d){super(t,m,d);}
    double penaltyPerDay(){return 0.20;}
}
class Submission {
    String student; Assignment assignment; LocalDate submitted; private String status="Submitted";
    Submission(String s,Assignment a,LocalDate d){student=s;assignment=a;submitted=d;
        long late=Math.max(0,ChronoUnit.DAYS.between(a.due,d));
        System.out.println(s+"'s submission for '"+a.title+"' received ("+(late==0?"on time":late+" days late")+"). Status: "+status+".");}
    void grade(double marks) {
        if(!status.equals("Submitted")){System.out.println("Cannot grade: submission is already graded.");return;}
        long late=Math.max(0,ChronoUnit.DAYS.between(assignment.due,submitted));
        double finalMark=assignment.finalMarks(Math.min(marks,assignment.maxMarks),late);
        status="Graded";
        System.out.printf("%s graded: %.0f/%d%s. Status: %s.%n",student,finalMark,assignment.maxMarks,
            late>0?" after "+(int)(late*assignment.penaltyPerDay()*100)+"% late penalty":"",status);
    }
    void resubmit(){if(status.equals("Graded"))System.out.println("Cannot resubmit: '"+assignment.title+"' has already been graded.");else System.out.println("Resubmission accepted.");}
}
public class AssignmentSubmissionPortal {
    public static void main(String[] args) {
        Assignment coding=new CodingAssignment("Linked List Lab",50,LocalDate.of(2027,3,10));
        Assignment written=new WrittenAssignment("Design Essay",50,LocalDate.of(2027,3,12));
        Submission a=new Submission("Asha",coding,LocalDate.of(2027,3,10));
        Submission r=new Submission("Ravi",written,LocalDate.of(2027,3,14));
        a.grade(45);r.grade(40);a.resubmit();
    }
}
