import java.util.*;
interface NotificationChannel { void send(String student,String message); }
class EmailChannel implements NotificationChannel { public void send(String s,String m){System.out.println("[Email → "+s+"] "+m);} }
class SmsChannel implements NotificationChannel { public void send(String s,String m){System.out.println("[SMS → "+s+"] "+m);} }
class AppChannel implements NotificationChannel { public void send(String s,String m){System.out.println("[App → "+s+"] "+m);} }
class Student {
    String name,department; List<NotificationChannel> channels;
    Student(String n,String d,NotificationChannel... c){name=n;department=d;channels=Arrays.asList(c);}
}
class Notice {
    String title; Set<String> departments;
    Notice(String t,String... d){title=t;departments=new LinkedHashSet<>(Arrays.asList(d));}
    boolean valid(){return title!=null&&!title.trim().isEmpty()&&!departments.isEmpty();}
}
class NoticeBoard {
    List<Student> students=new ArrayList<>();
    void addStudent(Student s){students.add(s);}
    void post(Notice n) {
        if(!n.valid()){System.out.println("Cannot post notice: At least one target department and a title are required.");return;}
        System.out.println("Notice '"+n.title+"' posted to "+String.join(", ",n.departments)+".");
        for(Student s:students)if(n.departments.contains(s.department))
            for(NotificationChannel c:s.channels)c.send(s.name,n.title);
    }
}
public class CampusNoticeBroadcaster {
    public static void main(String[] args) {
        NoticeBoard b=new NoticeBoard();
        b.addStudent(new Student("Asha","CSE",new EmailChannel(),new AppChannel()));
        b.addStudent(new Student("Ravi","ECE",new SmsChannel()));
        b.post(new Notice("Lab Closed Tomorrow","CSE"));
        b.post(new Notice("Fee Deadline Extended","CSE","ECE"));
        b.post(new Notice("Sports Day"));
    }
}
