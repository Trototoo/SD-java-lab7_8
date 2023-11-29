package sd.java.lab7_8.flowers;

public abstract class Flower {
    private String color;
    private int length;
    private final int freshness;

    public Flower(String color, int length, int freshness) {
        setColor(color);
        setLength(length);
        this.freshness = freshness;
    }

    public int getLength() {
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

    public void setLength(int length) {
        // Check if length is greater than 0
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be greater than 0.");
        }

        this.length = length;
    }

    public int calculateCost() {
        return getLength() * getFreshness();
    }

    @Override
    public String toString() {
        return color + " Flower - Length: " + length + " cm, Freshness: " + freshness;
    }
}
