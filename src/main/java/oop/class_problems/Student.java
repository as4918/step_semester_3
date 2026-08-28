public class Student {

    // Instance fields
    String name;
    int attendance;

    // Static fields shared by all students
    static String collegeName = "SRM Institute of Science and Technology";
    static int studentCount = 0;

    // Constructor
    public Student(String name, int attendance) {
        this.name = name;
        this.attendance = attendance;
        studentCount++;
    }

    // Static method
    static void printCollegeInfo() {
        System.out.println(collegeName);
        System.out.println("Students created: " + studentCount);
    }

    public static void main(String[] args) {

        // Create two Student objects
        Student student1 = new Student("Ravi", 85);
        Student student2 = new Student("Anitha", 90);

        // Call static method through class name
        Student.printCollegeInfo();
    }
}