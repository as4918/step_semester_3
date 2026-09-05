public class IdCard {

    String name;
    int booksIssued;

    // Constructor
    public IdCard(String name, int booksIssued) {
        this.name = name;
        this.booksIssued = booksIssued;
    }

    public static void main(String[] args) {

        // Create one IdCard object
        IdCard ravi = new IdCard("Ravi", 0);

        // Both variables refer to the SAME object
        IdCard duplicate = ravi;

        // Change through second reference
        duplicate.booksIssued = 3;

        // Print value through first reference
        System.out.println(
            "Ravi's booksIssued (via first variable): " + ravi.booksIssued
        );

        // Check if both references point to the same object
        System.out.println("duplicate == ravi: " + (duplicate == ravi));

        // Create a separate object with the same values
        IdCard separate = new IdCard("Ravi", 3);

        // Check if separate object is the same object
        System.out.println("separate == ravi: " + (separate == ravi));
    }
}