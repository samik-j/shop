package shopTestPackage;

import org.junit.jupiter.api.Test;
import shopPackage.Item;
import shopPackage.ItemQuantity;

import static org.junit.jupiter.api.Assertions.*;

class ItemQuantityTest
{
    @Test
    public void testAddQuantity()
    {
        ItemQuantity sample = new ItemQuantity(new Item(1, "book", 2.0), 5);
        sample.addQuantity(5);
        assertEquals(sample.getQuantity(), 10);
    }

}