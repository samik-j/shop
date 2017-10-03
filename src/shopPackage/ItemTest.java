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

}