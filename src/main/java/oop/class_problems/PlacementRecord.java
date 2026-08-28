class Placement {
    String studentName;
    String company;
    double packageLpa;

    Placement(String studentName, String company, double packageLpa) {
        this.studentName = studentName;
        this.company = company;
        this.packageLpa = packageLpa;
    }

    void printRecord() {
        System.out.println(studentName + " -> " + company + " @ " + packageLpa + " LPA");
    }
}

public class PlacementRecord {
    public static void main(String[] args) {

        Placement[] records = {
            new Placement("Ravi", "TCS", 4.5),
            new Placement("Anitha", "Zoho", 6.2),
            new Placement("Karthik", "Infosys", 4.0)
        };

        for (Placement record : records) {
            record.printRecord();
        }
    }
}