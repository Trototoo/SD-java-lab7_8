package sd.java.lab7_8;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CustomListTest {
    private CustomList<String> list;

    @BeforeEach
    public void setUp() {
        list = new CustomList<>();
    }

    @Test
    public void addAddsElement() {
        assertEquals(0, list.size());

        assertTrue(list.add("Test"));
        assertEquals(1, list.size());
        assertEquals("Test", list.get(0));

        assertTrue(list.add("Test2"));
        assertEquals(2, list.size());
        assertEquals("Test2", list.get(1));
    }

    @Test
    public void getReturnsElementAtSpecifiedIndex() {
        setupListWithDefaultValues();
        assertEquals(3, list.size());

        assertEquals("Test", list.get(0));
        assertEquals("Test2", list.get(1));
        assertEquals("Test3", list.get(2));
    }

    @Test
    public void addAddsElementAtSpecifiedIndex() {
        assertEquals(0, list.size());

        list.add(0, "Test");
        assertEquals(1, list.size());
        assertEquals("Test", list.get(0));

        list.add(0, "Test2");
        assertEquals(2, list.size());
        assertEquals("Test2", list.get(0));
        assertEquals("Test", list.get(1));
    }

    @Test
    public void addAllAddsAllElements() {
        CustomList<String> list2 = new CustomList<>();
        list2.add("Test4");
        list2.add("Test5");
        list2.add("Test6");

        assertEquals(0, list.size());
        assertEquals(3, list2.size());
        list.addAll(list2);
        assertEquals(3, list.size());
        assertEquals("Test4", list.get(0));
        assertEquals("Test5", list.get(1));
        assertEquals("Test6", list.get(2));
    }

    @Test
    public void removeRemovesElement() {
        setupListWithDefaultValues();
        assertEquals(3, list.size());

        assertTrue(list.remove("Test"));
        assertEquals(2, list.size());
        assertEquals("Test2", list.get(0));
        assertEquals("Test3", list.get(1));

        assertTrue(list.remove("Test3"));
        assertEquals(1, list.size());
        assertEquals("Test2", list.get(0));
    }

    @Test
    public void removeRemovesElementAtSpecifiedIndex() {
        setupListWithDefaultValues();
        assertEquals(3, list.size());

        list.remove(0);
        assertEquals(2, list.size());
        assertEquals("Test2", list.get(0));
        assertEquals("Test3", list.get(1));

        list.remove(1);
        assertEquals(1, list.size());
        assertEquals("Test2", list.get(0));
    }

    @Test
    public void removeAllRemovesAllElements() {
        setupListWithDefaultValues();
        assertEquals(3, list.size());

        CustomList<String> list2 = new CustomList<>();
        list2.add("Test");
        list2.add("Test3");

        list.removeAll(list2);
        assertEquals(1, list.size());
        assertEquals("Test2", list.get(0));
    }

    @Test
    public void retainAllRetainsAllElements() {
        setupListWithDefaultValues();
        assertEquals(3, list.size());

        CustomList<String> list2 = new CustomList<>();
        list2.add("Test");
        list2.add("Test3");

        assertTrue(list.retainAll(list2));
        assertEquals(2, list.size());
        assertEquals("Test", list.get(0));
        assertEquals("Test3", list.get(1));
    }

    @Test
    public void clearRemovesAllElements() {
        setupListWithDefaultValues();
        assertEquals(3, list.size());

        list.clear();
        assertEquals(0, list.size());
    }

    @Test
    public void setSetsElementAtSpecifiedIndex() {
        setupListWithDefaultValues();
        assertEquals(3, list.size());

        list.set(0, "Test4");
        assertEquals(3, list.size());
        assertEquals("Test4", list.get(0));
        assertEquals("Test2", list.get(1));
        assertEquals("Test3", list.get(2));
    }

    @Test
    public void indexOfReturnsIndexOfElement() {
        setupListWithDefaultValues();
        list.add("Test");
        assertEquals(4, list.size());

        assertEquals(1, list.indexOf("Test2"));
        assertEquals(0, list.indexOf("Test"));
        assertEquals(2, list.indexOf("Test3"));
    }

    @Test
    public void lastIndexOfReturnsLastIndexOfElement() {
        setupListWithDefaultValues();
        list.add("Test");
        list.add("Test2");
        list.add("Test3");
        assertEquals(6, list.size());

        assertEquals(4, list.lastIndexOf("Test2"));
        assertEquals(3, list.lastIndexOf("Test"));
        assertEquals(5, list.lastIndexOf("Test3"));
    }

    @Test
    public void containsReturnsTrueIfElementExists() {
        setupListWithDefaultValues();
        assertEquals(3, list.size());

        assertTrue(list.contains("Test"));
        assertTrue(list.contains("Test2"));
        assertTrue(list.contains("Test3"));
    }

    @Test
    public void containsAllReturnsTrueIfAllElementsExist() {
        setupListWithDefaultValues();
        assertEquals(3, list.size());

        CustomList<String> list2 = new CustomList<>();
        list2.add("Test");
        list2.add("Test3");

        assertTrue(list.containsAll(list2));
        list2.add("Test4");
        assertFalse(list.containsAll(list2));
    }

    @Test
    public void isEmptyReturnsTrueIfListIsEmpty() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());

        list.add("Test");
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
    }

    @Test
    public void toArrayReturnsArray() {
        setupListWithDefaultValues();
        assertEquals(3, list.size());

        Object[] array = list.toArray();
        assertEquals(3, array.length);
        assertEquals("Test", array[0]);
        assertEquals("Test2", array[1]);
        assertEquals("Test3", array[2]);
    }

    @Test
    public void toArrayReturnsArrayWithSpecifiedType() {
        setupListWithDefaultValues();
        assertEquals(3, list.size());

        String[] array = list.toArray(new String[0]);
        assertEquals(3, array.length);
        assertEquals("Test", array[0]);
        assertEquals("Test2", array[1]);
        assertEquals("Test3", array[2]);
    }

    @Test
    public void iteratorReturnsIterator() {
        setupListWithDefaultValues();
        assertEquals(3, list.size());

        int i = 0;
        for (String s : list) {
            assertEquals(list.get(i), s);
            assertEquals(list.indexOf(list.get(i)),i);
            i++;
        }
    }

    @Test
    public void iteratorRemoveRemovesElement() {
        setupListWithDefaultValues();
        assertEquals(3, list.size());

        int i = 0;
        for (String s : list) {
            assertEquals(list.get(i), s);
            assertEquals(list.indexOf(list.get(i)),i);
            list.remove(s);
        }
        assertEquals(0, list.size());
    }

    @Test
    public void iteratorHasNextReturnsTrueIfNextElementExists() {
        setupListWithDefaultValues();
        assertEquals(3, list.size());

        int i = 0;
        for (String s : list) {
            assertEquals(list.get(i), s);
            assertEquals(list.indexOf(list.get(i)),i);
            i++;
        }
    }

    @Test
    public void iteratorNextReturnsNextElement() {
        setupListWithDefaultValues();
        assertEquals(3, list.size());

        int i = 0;
        for (String s : list) {
            assertEquals(list.get(i), s);
            assertEquals(list.indexOf(list.get(i)),i);
            i++;
        }
    }

    @Test
    public void iteratorRemoveThrowsExceptionIfNextHasNotBeenCalled() {
        setupListWithDefaultValues();
        assertEquals(3, list.size());

        assertThrows(UnsupportedOperationException.class, () -> list.iterator().remove());
    }

    @Test
    public void iteratorRemoveThrowsExceptionIfRemoveHasAlreadyBeenCalled() {
        setupListWithDefaultValues();
        assertEquals(3, list.size());

        int i = 0;
        for (String s : list) {
            assertEquals(list.get(i), s);
            assertEquals(list.indexOf(list.get(i)),i);
            list.remove(s);
            assertThrows(UnsupportedOperationException.class, () -> list.iterator().remove());
        }
    }

    @Test
    public void addAllAddsList() {
        assertFalse(list.addAll(0, List.of()));
        assertEquals(0, list.size());

        list.add("Test");
        assertEquals(1, list.size());

        assertTrue(list.addAll(0, Arrays.asList("Test2", "Test3")));
        assertEquals(3, list.size());

        assertTrue(list.contains("Test2"));
        assertTrue(list.contains("Test3"));
    }

    @Test
    public void addAllThrowsExceptionIfIndexIsOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.addAll(1, List.of()));
    }

    @Test
    public void removeIfRemovesElements() {
        assertFalse(list.removeIf(s -> s.equals("Test")));
        assertEquals(0, list.size());

        setupListWithDefaultValues();
        assertEquals(3, list.size());

        assertTrue(list.removeIf(s -> s.equals("Test")));
        assertEquals(2, list.size());
        assertFalse(list.contains("Test"));

        assertTrue(list.removeIf(s -> s.equals("Test2")));
        assertEquals(1, list.size());
        assertFalse(list.contains("Test2"));

        assertTrue(list.removeIf(s -> s.equals("Test3")));
        assertEquals(0, list.size());
        assertFalse(list.contains("Test3"));
    }

    @Test
    public void removeIfThrowsExceptionIfPredicateIsNull() {
        list.add("Test");
        list.add("Test");
        list.add("Test");

        assertTrue(list.removeIf(s -> s.equals("Test")));
        assertEquals(0, list.size());
        assertFalse(list.contains("Test"));
    }

    @Test
    public void sublistReturnsSublist() {
        CustomList<String> sublist;
        assertThrows(IndexOutOfBoundsException.class, () -> list.subList(0, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.subList(0, 0));

        setupListWithDefaultValues();
        assertEquals(3, list.size());

        sublist = list.subList(0, 2);
        assertEquals(2, sublist.size());
        assertEquals("Test", sublist.get(0));
        assertEquals("Test2", sublist.get(1));
    }

    private void setupListWithDefaultValues() {
        list.add("Test");
        list.add("Test2");
        list.add("Test3");
    }
}
