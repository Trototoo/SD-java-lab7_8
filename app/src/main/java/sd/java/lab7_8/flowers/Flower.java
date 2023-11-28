package sd.java.lab7_8.flowers;

public abstract class Flower {
    private String color;
    private double length;
    private int freshness;

    public Flower(String color, double length, int freshness) {
        setColor(color);
        setLength(length);
        setFreshness(freshness);
    }

    public double getLength() {
        return length;
    }

    public int getFreshness() {
        return freshness;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        // Check if color is at least 1 character long
        if (color == null || color.trim().isEmpty()) {
            throw new IllegalArgumentException("Color must be at least 1 character long.");
        }

        this.color = color;
    }

    public void setLength(double length) {
        // Check if length is greater than 0
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be greater than 0.");
        }

        this.length = length;
    }

    public void setFreshness(int freshness) {
        this.freshness = freshness;
    }

    public double calculateCost() {
        return getLength() * getFreshness();
    }

    @Override
    public String toString() {
        return color + " Flower - Length: " + length + " cm, Freshness: " + freshness;
    }
}
