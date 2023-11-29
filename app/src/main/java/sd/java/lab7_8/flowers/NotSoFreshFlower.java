package sd.java.lab7_8.flowers;

public class NotSoFreshFlower extends Flower {
    private static final int FRESHNESS = 30; // Constant freshness value for NotSoFreshFlower

    public NotSoFreshFlower(String color, int length) {
        super(color, length, FRESHNESS);
    }
}
