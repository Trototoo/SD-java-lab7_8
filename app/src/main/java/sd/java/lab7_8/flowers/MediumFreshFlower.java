package sd.java.lab7_8.flowers;

public class MediumFreshFlower extends Flower {
    private static final int FRESHNESS = 60; // Constant freshness value for MediumFreshFlower

    public MediumFreshFlower(String color, int length) {
        super(color, length, FRESHNESS);
    }
}
