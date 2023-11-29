package sd.java.lab7_8.flowers;

public class FreshFlower extends Flower {
    private static final int FRESHNESS = 100; // Constant freshness value for FreshFlower

    public FreshFlower(String color, int length) {
        super(color, length, FRESHNESS);
    }
}
