package shopTestPackage;

import org.junit.jupiter.api.Test;
import shopPackage.Clothing;
import shopPackage.InvalidInfoException;
import shopPackage.Item;

import static org.junit.jupiter.api.Assertions.*;

class ClothingTest {
    @Test
    public void testCompareSame() throws InvalidInfoException {
        Item clothing1 = new Clothing(1, "coat", 2.0, new String[]{"34"});
        Item clothing2 = new Clothing(1, "coat", 2.0, new String[]{"34"});
        assertTrue(clothing1.compare(clothing2));
    }

    @Test
    public void testCompareDifferent() throws InvalidInfoException {
        Item clothing1 = new Clothing(1, "coat", 2.0, new String[]{"34"});
        Item clothing2 = new Clothing(1, "coat", 2.0, new String[]{"36"});
        assertFalse(clothing1.compare(clothing2));
    }

    @Test
    public void testToString() throws InvalidInfoException {
        Item clothing = new Clothing(1, "coat", 2.0, new String[]{"34"});
        String clothingToString = "1, CLOTHING, coat, 2.0, 34";
        assertTrue(clothing.toString().equals(clothingToString));
    }

    @Test
    public void constructorThrowsInvalidInfoException() {
        assertThrows(InvalidInfoException.class, () ->
        {
            Item clothing = new Clothing(1, "coat", 2.0, new String[]{"34", "sth"});
        });
    }

}