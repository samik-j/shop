package shopPackage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    public void testChangePrice()
    {
        Item item = new Item(1, "sth", 2.0);
        double newPrice = 2.0;
        item.changePrice(newPrice);
        double expectedPrice = 2.0;
        assertTrue(item.getPrice() == expectedPrice);
    }

    @Test
    public void testToString() {
        Item item = new Item(1, "sth", 2.0);
        assertEquals(item.toString(), "1, OTHER, sth, 2.0");
    }

    @Test
    public void compareIfEqual() {
        Item item1 = new Item(1, "sth", 2.0);
        Item item2 = new Item(1, "sth", 2.0);
        assertTrue(item1.compare(item2));
    }

    @Test
    public void compareIfNotEqual() {
        Item item1 = new Item(1, "sth", 2.0);
        Item item2 = new Item(1, "sths", 2.0);
        assertFalse(item1.compare(item2));
    }
}