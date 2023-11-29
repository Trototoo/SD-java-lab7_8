package sd.java.lab7_8;

import sd.java.lab7_8.flowers.Flower;

import java.util.Comparator;

public class Bouquet {
    private final CustomList<Flower> flowers;
    private final Accessory accessory;
    private int value; // Total value of the bouquet

    public Bouquet(Accessory accessory) {
        this.flowers = new CustomList<>();
        this.accessory = accessory;
        this.value = accessory.getPrice();
    }

    public void addFlower(Flower flower) {
        if (flower == null) {
            throw new NullPointerException("Flower cannot be null.");
        }
        flowers.add(flower);
        value += flower.calculateCost(); // Update the total value when adding a flower
    }

    public void addFlowerList(CustomList<Flower> flowerList) {
        flowers.addAll(flowerList);
        for (Flower flower : flowerList) {
            value += flower.calculateCost(); // Update the total value for each added flower
        }
    }

    public CustomList<Flower> getFlowers() {
        return new CustomList<>(flowers);
    }

    public CustomList<Flower> getListOfFlowersSortedByFreshness() {
        CustomList<Flower> sortedBouquet = new CustomList<>(flowers);
        sortedBouquet.sort(Comparator.comparingInt(Flower::getFreshness));
        return sortedBouquet;
    }

    public Bouquet getBouquetSortedByFreshness() {
        Bouquet sortedBouquet = new Bouquet(this.accessory);
        sortedBouquet.addFlowerList(this.getListOfFlowersSortedByFreshness());
        return sortedBouquet;
    }

    public Flower getFirstFlowerInLengthRange(int minLength, int maxLength) {
        for (Flower flower : flowers) {
            int flowerLength = flower.getLength();
            if (flowerLength >= minLength && flowerLength <= maxLength) {
                return flower;
            }
        }
        return null;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Bouquet Contents:\n");
        for (Flower flower : flowers) {
            result.append(flower).append("\n");
        }
        result.append("Accessory: ").append(accessory).append("\n");
        result.append("Total Value of the Bouquet: $").append(getValue());
        return result.toString();
    }
}
