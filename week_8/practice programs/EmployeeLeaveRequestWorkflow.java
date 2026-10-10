abstract class Employee {
    private final String name;
    Employee(String name) { this.name=name; }
    String getName() { return name; }
    abstract boolean isLeaveAllowed(int days);
}
class FullTimeEmployee extends Employee {
    FullTimeEmployee(String name) { super(name); }
    boolean isLeaveAllowed(int days) { return days > 0 && days <= 30; }
}
class PartTimeEmployee extends Employee {
    PartTimeEmployee(String name) { super(name); }
    boolean isLeaveAllowed(int days) { return days > 0 && days <= 10; }
}
class LeaveRequest {
    private final Employee employee;
    private final String dates;
    private String status="Pending";
    LeaveRequest(Employee e,String dates,int days) {
        employee=e; this.dates=dates;
        if (!e.isLeaveAllowed(days)) status="Rejected";
        System.out.println("Leave request submitted for "+e.getName()+" ("+dates+"). Status: "+status);
    }
    void review(boolean approve,String manager) {
        if (!status.equals("Pending")) { System.out.println("Request already reviewed."); return; }
        status=approve ? "Approved" : "Rejected";
        System.out.println(employee.getName()+"'s leave request ("+dates+") "+status.toLowerCase()
                +". Status: "+status+". Reviewed by "+manager+".");
    }
    void changeStatus(String next) {
        if (!status.equals("Pending")) System.out.println("Cannot change leave request status from "+status+" to "+next+".");
        else if (next.equals("Approved") || next.equals("Rejected")) status=next;
        else System.out.println("Invalid status transition.");
    }
}
public class EmployeeLeaveRequestWorkflow {
    public static void main(String[] args) {
        Employee john=new FullTimeEmployee("John"), jane=new PartTimeEmployee("Jane");
        LeaveRequest a=new LeaveRequest(john,"Jan 1-5",5); a.review(true,"Alice");
        LeaveRequest b=new LeaveRequest(jane,"Feb 10-11",2); b.review(false,"Bob");
        a.changeStatus("Pending");
    }
}
