package sd.java.lab7_8;

import org.junit.jupiter.api.Test;
import sd.java.lab7_8.flowers.Flower;
import sd.java.lab7_8.flowers.FreshFlower;
import sd.java.lab7_8.flowers.MediumFreshFlower;
import sd.java.lab7_8.flowers.NotSoFreshFlower;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FlowerTest {

    @Test
    public void gettersReturnsValidValues() {
        Flower freshFlower = new FreshFlower("Red", 10);
        Flower mediumFreshFlower = new MediumFreshFlower("Blue", 5);
        Flower notSoFreshFlower = new NotSoFreshFlower("Green", 1);

        assertEquals(10, freshFlower.getLength());
        assertEquals(5, mediumFreshFlower.getLength());
        assertEquals(1, notSoFreshFlower.getLength());

        assertEquals(100, freshFlower.getFreshness());
        assertEquals(60, mediumFreshFlower.getFreshness());
        assertEquals(30, notSoFreshFlower.getFreshness());

        assertEquals("Red", freshFlower.getColor());
        assertEquals("Blue", mediumFreshFlower.getColor());
        assertEquals("Green", notSoFreshFlower.getColor());
    }

    @Test
    public void settersSetValidValues() {
        Flower freshFlower = new FreshFlower("Red", 10);
        Flower mediumFreshFlower = new MediumFreshFlower("Blue", 5);
        Flower notSoFreshFlower = new NotSoFreshFlower("Green", 1);

        assertEquals(10, freshFlower.getLength());
        assertEquals(5, mediumFreshFlower.getLength());
        assertEquals(1, notSoFreshFlower.getLength());

        assertEquals("Red", freshFlower.getColor());
        assertEquals("Blue", mediumFreshFlower.getColor());
        assertEquals("Green", notSoFreshFlower.getColor());

        freshFlower.setLength(20);
        mediumFreshFlower.setLength(10);
        notSoFreshFlower.setLength(2);

        freshFlower.setColor("Yellow");
        mediumFreshFlower.setColor("Orange");
        notSoFreshFlower.setColor("Purple");

        assertEquals(20, freshFlower.getLength());
        assertEquals(10, mediumFreshFlower.getLength());
        assertEquals(2, notSoFreshFlower.getLength());

        assertEquals("Yellow", freshFlower.getColor());
        assertEquals("Orange", mediumFreshFlower.getColor());
        assertEquals("Purple", notSoFreshFlower.getColor());

    }

    @Test
    public void settersThrowsExceptionWhenInvalidValuesArePassed() {
        createFreshFlowerAndCatchExceptionColor(null);
        createFreshFlowerAndCatchExceptionColor("");
        createFreshFlowerAndCatchExceptionColor(" ");
        createFreshFlowerAndCatchExceptionColor("  ");

        createFreshFlowerAndCatchExceptionLength(0);
        createFreshFlowerAndCatchExceptionLength(-1);
        createFreshFlowerAndCatchExceptionLength(-100);
    }

    @Test
    public void calculateCostReturnsValidValues() {
        Flower freshFlower = new FreshFlower("Red", 10);
        Flower mediumFreshFlower = new MediumFreshFlower("Blue", 5);
        Flower notSoFreshFlower = new NotSoFreshFlower("Green", 1);

        assertEquals(1000, freshFlower.calculateCost());
        assertEquals(300, mediumFreshFlower.calculateCost());
        assertEquals(30, notSoFreshFlower.calculateCost());

        freshFlower.setLength(1);
        mediumFreshFlower.setLength(1);
        notSoFreshFlower.setLength(1);

        assertEquals(100, freshFlower.calculateCost());
        assertEquals(60, mediumFreshFlower.calculateCost());
        assertEquals(30, notSoFreshFlower.calculateCost());
    }

    @Test
    public void toStringReturnValidString() {
        Flower freshFlower = new FreshFlower("Red", 10);
        Flower mediumFreshFlower = new MediumFreshFlower("Blue", 5);
        Flower notSoFreshFlower = new NotSoFreshFlower("Green", 1);

        assertEquals("Red Flower - Length: 10 cm, Freshness: 100", freshFlower.toString());
        assertEquals("Blue Flower - Length: 5 cm, Freshness: 60", mediumFreshFlower.toString());
        assertEquals("Green Flower - Length: 1 cm, Freshness: 30", notSoFreshFlower.toString()); }

    void createFreshFlowerAndCatchExceptionColor(String color) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new FreshFlower(color, 10));
        assertEquals("Color must be at least 1 character long.", exception.getMessage());
    }

    void createFreshFlowerAndCatchExceptionLength(int length) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new FreshFlower("Red", length));
        assertEquals("Length must be greater than 0.", exception.getMessage());
    }
}
