package sd.java.lab7_8;

public class Accessory {
    private final String name;
    private final double price;

    public Accessory(String name, double price) {
        // Check if name is at least 1 character long (letter or digit)
        if (name == null || !name.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("Name must be at least 1 character long and can only contain letters.");
        }

        // Check if price is greater than or equal to 0
        if (price < 0) {
            throw new IllegalArgumentException("Price must be greater than or equal to 0.");
        }

        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " - Price: $" + price;
    }
}
