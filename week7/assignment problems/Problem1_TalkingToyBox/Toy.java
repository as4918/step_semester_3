public abstract class Toy {
    private static int counter = 1000;
    private final String toyId;
    protected final String name;
    public Toy(String name) {
        this.name = name;
        this.toyId = "TOY-" + (++counter);
    }
    public abstract String makeSound();
    public String getToyId() { return toyId; }
}