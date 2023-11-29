package sd.java.lab7_8;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sd.java.lab7_8.flowers.*;

import static org.junit.jupiter.api.Assertions.*;

public class BouquetTest {
    private Bouquet bouquet;

    @BeforeEach
    public void setUp() {
        Accessory accessory = new Accessory("Ribbon", 10);
        bouquet = new Bouquet(accessory);
    }

    @Test
    public void addFlowerAddsFlower() {
        assertEquals(0, bouquet.getFlowers().size());

        Flower flower1 = new FreshFlower("Rose", 10);
        bouquet.addFlower(flower1);
        assertEquals(1, bouquet.getFlowers().size());
        assertEquals(flower1, bouquet.getFlowers().get(0));

        Flower flower2 = new FreshFlower("Rose", 20);
        bouquet.addFlower(flower2);
        assertEquals(2, bouquet.getFlowers().size());
        assertEquals(flower2, bouquet.getFlowers().get(1));
    }

    @Test
    public void addFlowerIncreasesValue() {
        assertEquals(10, bouquet.getValue());
        bouquet.addFlower(new FreshFlower("Rose", 10));
        assertEquals(1010, bouquet.getValue());
        bouquet.addFlower(new FreshFlower("Rose", 10));
        assertEquals(2010, bouquet.getValue());
    }

    @Test
    public void addFlowerThrowsExceptionWithNullFlower() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> bouquet.addFlower(null));
        assertEquals("Flower cannot be null.", exception.getMessage());
    }

    @Test
    public void addFlowerListAddsFlowerList() {
        assertEquals(0, bouquet.getFlowers().size());

        bouquet.addFlowerList(new CustomList<>());
        assertEquals(0, bouquet.getFlowers().size());

        CustomList<Flower> flowerList = new CustomList<>(new FreshFlower("Rose", 10));
        flowerList.add(new FreshFlower("Rose", 20));

        bouquet.addFlowerList(flowerList);
        assertEquals(2, bouquet.getFlowers().size());
        bouquet.addFlowerList(flowerList);
        assertEquals(4, bouquet.getFlowers().size());
    }

    @Test
    public void addFlowerListIncreasesValue() {
        assertEquals(10, bouquet.getValue());
        CustomList<Flower> flowerList = new CustomList<>(new FreshFlower("Rose", 10));
        flowerList.add(new FreshFlower("Rose", 20));

        bouquet.addFlowerList(flowerList);
        assertEquals(3010, bouquet.getValue());
        bouquet.addFlowerList(flowerList);
        assertEquals(6010, bouquet.getValue());
    }

    @Test
    public void getListOfFlowersSortedByFreshnessReturnsCorrectList() {
        bouquet.addFlower(new FreshFlower("Rose", 10));
        bouquet.addFlower(new NotSoFreshFlower("Rose", 20));
        bouquet.addFlower(new MediumFreshFlower("Rose", 30));
        bouquet.addFlower(new NotSoFreshFlower("Rose", 40));
        bouquet.addFlower(new FreshFlower("Rose", 50));
        assertEquals(5, bouquet.getFlowers().size());
        assertEquals(100, bouquet.getFlowers().get(0).getFreshness());
        assertEquals(30, bouquet.getFlowers().get(1).getFreshness());
        assertEquals(60, bouquet.getFlowers().get(2).getFreshness());
        assertEquals(30, bouquet.getFlowers().get(3).getFreshness());
        assertEquals(100, bouquet.getFlowers().get(4).getFreshness());

        CustomList<Flower> sortedList = bouquet.getListOfFlowersSortedByFreshness();
        assertEquals(30, sortedList.get(0).getFreshness());
        assertEquals(30, sortedList.get(1).getFreshness());
        assertEquals(60, sortedList.get(2).getFreshness());
        assertEquals(100, sortedList.get(3).getFreshness());
        assertEquals(100, sortedList.get(4).getFreshness());
    }

    @Test
    public void getBouquetSortedByFreshnessReturnsCorrectBouquet() {
        bouquet.addFlower(new FreshFlower("Rose", 10));
        bouquet.addFlower(new NotSoFreshFlower("Rose", 20));
        bouquet.addFlower(new MediumFreshFlower("Rose", 30));
        bouquet.addFlower(new NotSoFreshFlower("Rose", 40));
        bouquet.addFlower(new FreshFlower("Rose", 50));
        assertEquals(5, bouquet.getFlowers().size());
        assertEquals(100, bouquet.getFlowers().get(0).getFreshness());
        assertEquals(30, bouquet.getFlowers().get(1).getFreshness());
        assertEquals(60, bouquet.getFlowers().get(2).getFreshness());
        assertEquals(30, bouquet.getFlowers().get(3).getFreshness());
        assertEquals(100, bouquet.getFlowers().get(4).getFreshness());

        Bouquet sortedBouquet = bouquet.getBouquetSortedByFreshness();
        assertEquals(5, sortedBouquet.getFlowers().size());
        assertEquals(30, sortedBouquet.getFlowers().get(0).getFreshness());
        assertEquals(30, sortedBouquet.getFlowers().get(1).getFreshness());
        assertEquals(60, sortedBouquet.getFlowers().get(2).getFreshness());
        assertEquals(100, sortedBouquet.getFlowers().get(3).getFreshness());
        assertEquals(100, sortedBouquet.getFlowers().get(4).getFreshness());
    }

    @Test
    public void getFirstFlowerInLengthRangeReturnsCorrectFlower() {
        bouquet.addFlower(new FreshFlower("Rose", 10));
        bouquet.addFlower(new NotSoFreshFlower("Rose", 20));
        bouquet.addFlower(new MediumFreshFlower("Rose", 30));
        bouquet.addFlower(new NotSoFreshFlower("Rose", 40));
        bouquet.addFlower(new FreshFlower("Rose", 50));

        assertEquals(10, bouquet.getFirstFlowerInLengthRange(0, 10).getLength());
        assertEquals(20, bouquet.getFirstFlowerInLengthRange(19, 50).getLength());
        assertNull(bouquet.getFirstFlowerInLengthRange(51, 51));
    }

    @Test
    public void toStringReturnValidString() {
        bouquet.addFlower(new FreshFlower("Rose", 10));
        bouquet.addFlower(new NotSoFreshFlower("Rose", 20));
        bouquet.addFlower(new MediumFreshFlower("Rose", 30));
        bouquet.addFlower(new NotSoFreshFlower("Rose", 40));
        bouquet.addFlower(new FreshFlower("Rose", 50));
        String expectedString = """
                Bouquet Contents:
                Rose Flower - Length: 10 cm, Freshness: 100
                Rose Flower - Length: 20 cm, Freshness: 30
                Rose Flower - Length: 30 cm, Freshness: 60
                Rose Flower - Length: 40 cm, Freshness: 30
                Rose Flower - Length: 50 cm, Freshness: 100
                Accessory: Ribbon - Price: $10
                Total Value of the Bouquet: $9610.0""";
        assertEquals(expectedString, bouquet.toString()); }
}
