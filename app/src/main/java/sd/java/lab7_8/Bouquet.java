package sd.java.lab7_8;

import sd.java.lab7_8.flowers.Flower;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Bouquet {
    private final List<Flower> flowers;
    private final Accessory accessory;
    private double value; // Total value of the bouquet

    public Bouquet(Accessory accessory) {
        this.flowers = new ArrayList<>();
        this.accessory = accessory;
        this.value = accessory.getPrice();
    }

    public void addFlower(Flower flower) {
        flowers.add(flower);
        value += flower.calculateCost(); // Update the total value when adding a flower
    }

    public void addFlowerList(List<Flower> flowerList) {
        flowers.addAll(flowerList);
        for (Flower flower : flowerList) {
            value += flower.calculateCost(); // Update the total value for each added flower
        }
    }

    public List<Flower> getFlowers() {
        return new ArrayList<>(flowers);
    }

    public List<Flower> getListOfFlowersSortedByFreshness() {
        List<Flower> sortedBouquet = new ArrayList<>(flowers);
        sortedBouquet.sort(Comparator.comparingInt(Flower::getFreshness));
        return sortedBouquet;
    }

    public Bouquet getBouquetSortedByFreshness() {
        Bouquet sortedBouquet = new Bouquet(this.accessory);
        sortedBouquet.addFlowerList(this.getListOfFlowersSortedByFreshness());
        return sortedBouquet;
    }

    public Flower getFirstFlowerInLengthRange(double minLength, double maxLength) {
        for (Flower flower : flowers) {
            double flowerLength = flower.getLength();
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
