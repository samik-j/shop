package shopTestPackage;

import org.junit.jupiter.api.Test;
import shopPackage.Item;
import shopPackage.ItemQuantity;

import static org.junit.jupiter.api.Assertions.*;

class ItemQuantityTest {
    @Test
    public void testAddQuantity() {
        ItemQuantity sample = new ItemQuantity(new Item(1, "book", 2.0), 5);
        sample.changeQuantityBy(5);
        assertEquals(sample.getQuantity(), 10);
    }

    @Test
    public void compareSame() {
        ItemQuantity itemQuantity1 = new ItemQuantity(new Item(1, "book", 2.0), 5);
        ItemQuantity itemQuantity2 = new ItemQuantity(new Item(1, "book", 2.0), 5);
        assertTrue(itemQuantity1.compare(itemQuantity2));
    }

    @Test
    public void compareDifferent() {
        ItemQuantity itemQuantity1 = new ItemQuantity(new Item(1, "book", 2.0), 5);
        ItemQuantity itemQuantity2 = new ItemQuantity(new Item(1, "book", 2.0), 7);
        assertFalse(itemQuantity1.compare(itemQuantity2));
    }
}