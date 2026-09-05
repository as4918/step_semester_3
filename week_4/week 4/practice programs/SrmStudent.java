public class SrmStudent {
    static String collegeName;
    static int academicYear;
    String studentName;

    static {
        collegeName = "SRM";
        academicYear = 2026;
        System.out.println("College info loaded");
    }

    SrmStudent(String studentName) {
        this.studentName = studentName;
    }

    public static void main(String[] args) {
        String[] names = {"Ravi", "Meera", "Karthik", "Divya", "Anitha"};

        for (String name : names) {
            SrmStudent student = new SrmStudent(name);
            System.out.println("Student record created: " + student.studentName);
        }
    }
}
