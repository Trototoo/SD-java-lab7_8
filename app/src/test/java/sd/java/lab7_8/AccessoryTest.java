package sd.java.lab7_8;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccessoryTest {

    private Accessory accessory;

    @BeforeEach
    public void setUp() {
        accessory = new Accessory("Ribbon", 10);
    }

    @Test
    public void settersSetValidValuesAndGettersReturnValidValues() {
        assertEquals(10, accessory.getPrice());
        assertEquals("Ribbon", accessory.getName());
    }

    @Test
    public void setterThrowExceptionWithInvalidValues() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> accessory.setPrice(-1));
        assertEquals("Price must be greater than or equal to 0.", exception.getMessage());
        exception = assertThrows(IllegalArgumentException.class, () -> accessory.setName(null));
        assertEquals("Name must be at least 1 character long and can only contain letters.", exception.getMessage());
        exception = assertThrows(IllegalArgumentException.class, () -> accessory.setName(""));
        assertEquals("Name must be at least 1 character long and can only contain letters.", exception.getMessage());
        exception = assertThrows(IllegalArgumentException.class, () -> accessory.setName("1"));
        assertEquals("Name must be at least 1 character long and can only contain letters.", exception.getMessage());
    }

    @Test
    public void toStringReturnsCorrectString() {
        assertEquals("Ribbon - Price: $10", accessory.toString());
    }
}
