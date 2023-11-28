package sd.java.lab7_8;

import sd.java.lab7_8.flowers.FreshFlower;
import sd.java.lab7_8.flowers.MediumFreshFlower;
import sd.java.lab7_8.flowers.NotSoFreshFlower;

public class App {

    public static void main(String[] args) {
        // Create accessories to the bouquet
        Accessory ribbon = new Accessory("Ribbon", 5);
        Accessory paper = new Accessory("Paper", 10);

        // Create new bouquets
        Bouquet bouquetWithRibbon = new Bouquet(ribbon);
        Bouquet bouquetWithPaper = new Bouquet(paper);

        // Add flowers to the bouquet
        bouquetWithRibbon.addFlower(new FreshFlower("Red", 10));
        bouquetWithRibbon.addFlower(new MediumFreshFlower("Blue", 20));
        bouquetWithRibbon.addFlower(new MediumFreshFlower("Yellow", 30));
        bouquetWithRibbon.addFlower(new NotSoFreshFlower("Green", 40));


        // Print the bouquet
        System.out.println(bouquetWithRibbon.getBouquetSortedByFreshness());

        // Print the first flower in the bouquet with length between 21 and 31
        System.out.println("First flower in length range 21-31: " + bouquetWithRibbon.getFirstFlowerInLengthRange(21, 31));

        // Print the cost of the bouquet
        System.out.println("Cost: $" + bouquetWithRibbon.getValue());
    }
}
