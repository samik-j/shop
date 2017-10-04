package shopTestPackage;

import org.junit.jupiter.api.Test;
import shopPackage.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StorageTest {
    Storage storage = new Storage();

    @Test
    public void defaultConstructorReturnEmptyStorage() {
        assertTrue(storage.getAllItems().isEmpty());
    }

    @Test
    public void testReadStorageFromFile() throws Exception {
        BufferedReader reader = mock(BufferedReader.class);
        when(reader.readLine()).thenReturn("1, OTHER, book, 2.3, 2", null);
        storage.readStorageFromFile(reader);
        ItemQuantity expected = new ItemQuantity(new Item(1, "book", 2.3), 2);
        assertTrue(storage.getItemQuantityById(1).compare(expected));
    }

    @Test
    public void addItemFromString() throws Exception {
        storage.addItemFromString("1, OTHER, book, 2.0, 4");
        ItemQuantity expected = new ItemQuantity(new Item(1, "book", 2.0), 4);
        assertTrue(storage.getItemQuantityById(1).compare(expected));
    }

    @Test
    public void addItemFromStringIfBook() throws Exception {
        storage.addItemFromString("1, BOOK, book, 2.0, thisIsTitle, 4");
        String[] info = {"thisIsTitle"};
        ItemQuantity expected = new ItemQuantity(new Book(1, "book", 2.0, info), 4);
        assertTrue(storage.getItemQuantityById(1).compare(expected));
    }

    @Test
    public void addItemFromStringIfClothing() throws Exception {
        storage.addItemFromString("1, CLOTHING, coat, 2.0, 36, 4");
        String[] info = {"36"};
        ItemQuantity expected = new ItemQuantity(new Clothing(1, "coat", 2.0, info), 4);
        assertTrue(storage.getItemQuantityById(1).compare(expected));
    }

    @Test
    public void addItemFromStringMultipleItemsSameId() throws Exception {
        storage.addItemFromString("1, OTHER, book, 2.0, 4");
        storage.addItemFromString("1, OTHER, book, 2.0, 9");
        ItemQuantity expected = new ItemQuantity(new Item(1, "book", 2.0), 13);
        assertTrue(storage.getItemQuantityById(1).compare(expected));
    }

    @Test
    public void addItemFromStringMultipleEntries() throws Exception {
        storage.addItemFromString("1, OTHER, book, 2.0, 4");
        storage.addItemFromString("2, OTHER, coat, 2.0, 4");
        ItemQuantity expected1 = new ItemQuantity(new Item(1, "book", 2.0), 4);
        ItemQuantity expected2 = new ItemQuantity(new Item(2, "coat", 2.0), 4);
        Map<Integer, ItemQuantity> expected = new HashMap<>();
        expected.put(1, expected1);
        expected.put(2, expected2);
        assertTrue(storage.getAllItems().equals(expected));
    }

    @Test()
    public void addItemFromStringThrowsMismatchItemTypeException() throws Exception {
        storage.addItemFromString("1, OTHER, book, 2.0, title, 4");

        assertThrows(MismatchItemTypeException.class, () ->
        {
            storage.addItemFromString("1, BOOK, book, 2.0, title, 4");
        });
    }

    @Test()
    public void addItemFromStringThrowsInvalidInfoException() throws Exception {
        assertThrows(InvalidInfoException.class, () ->
        {
            storage.addItemFromString("1, BOOK, book, 2.0, 4");
        });
    }

    @Test()
    public void addItemFromStringThrowsException() throws Exception {
        assertThrows(Exception.class, () ->
        {
            storage.addItemFromString("a, BOOK, book, 2.0, title, 4");
        });
    }

    @Test
    public void addItemsFromFile() throws Exception {
        BufferedReader reader = mock(BufferedReader.class);
        when(reader.readLine()).thenReturn("1, OTHER, book, 2.0, 4", "2, OTHER, sth, 2.2, 5", null);
        storage.addItemsFromFile(reader);
        ItemQuantity expected1 = new ItemQuantity(new Item(1, "book", 2.0), 4);
        ItemQuantity expected2 = new ItemQuantity(new Item(2, "sth", 2.2), 5);
        Map<Integer, ItemQuantity> expected = new HashMap<>();
        expected.put(1, expected1);
        expected.put(2, expected2);
        assertTrue(storage.getAllItems().equals(expected));
    }

    @Test
    public void testGetItemQuantityByIdNoSuchIdReturnsNull() throws Exception {
        storage.addItemFromString("1, OTHER, book, 2.0, 4");
        assertEquals(storage.getItemQuantityById(2), null);
    }

    @Test
    public void testGetItemQuantityById() throws Exception {
        storage.addItemFromString("1, OTHER, book, 2.0, 4");
        assertTrue(storage.getItemQuantityById(1).compare(new ItemQuantity(new Item(1, "book", 2.0), 4)));
    }

    @Test
    public void testGetAllItems() throws Exception {
        storage.addItemFromString("1, OTHER, book, 2.0, 4");
        storage.addItemFromString("2, OTHER, sth, 2.0, 4");
        ItemQuantity expected1 = new ItemQuantity(new Item(1, "book", 2.0), 4);
        ItemQuantity expected2 = new ItemQuantity(new Item(2, "sth", 2.0), 4);
        Map<Integer, ItemQuantity> expected = new HashMap<>();
        expected.put(1, expected1);
        expected.put(2, expected2);
        assertTrue(storage.getAllItems().equals(expected));
    }

    @Test
    public void testPrintToFile() throws Exception {
        storage.addItemFromString("1, BOOK, book, 2.0, title, 4");
        storage.addItemFromString("2, OTHER, sth, 2.0, 4");
        storage.addItemFromString("3, CLOTHING, coat, 2.0, 34, 4");
        BufferedWriter writer = mock(BufferedWriter.class);
        storage.printToFile(writer);
        verify(writer, times(1)).write("1, BOOK, book, 2.0, title, 4");
        verify(writer, times(1)).write("2, OTHER, sth, 2.0, 4");
        verify(writer, times(1)).write("3, CLOTHING, coat, 2.0, 34, 4");
        verify(writer, times(3)).newLine();
        verify(writer, times(1)).close();
    }

    @Test
    public void testPrintToFileDifferentItemTypes() throws Exception {
        storage.addItemFromString("1, BOOK, book, 2.0, thisIsTitle, 4");
        storage.addItemFromString("2, OTHER, sth, 2.0, 4");
        storage.addItemFromString("3, CLOTHING, coat, 2.0, 36, 4");
        BufferedWriter writer = mock(BufferedWriter.class);
        storage.printToFile(writer);
        verify(writer, times(1)).write("1, BOOK, book, 2.0, thisIsTitle, 4");
        verify(writer, times(1)).write("2, OTHER, sth, 2.0, 4");
        verify(writer, times(1)).write("3, CLOTHING, coat, 2.0, 36, 4");
        verify(writer, times(3)).newLine();
        verify(writer, times(1)).close();
    }

    @Test
    public void testGetItemFromStorageByIdIfHasSuchId() throws Exception {
        storage.addItemFromString("1, BOOK, book, 2.0, thisIsTitle, 4");
        Item expected = new Book(1, "book", 2.0, new String[]{"thisIsTitle"});
        assertTrue(expected.compare(storage.getItemFromStorageById(1)));
    }

    @Test
    public void testGetItemFromStorageByIdIfNoSuchId() throws Exception {
        storage.addItemFromString("1, BOOK, book, 2.0, thisIsTitle, 4");
        assertThrows(ItemNotFoundException.class, () ->
        {
            storage.getItemFromStorageById(2);
        });
    }

    @Test
    public void testGetItemByNameIfHasSuch() throws Exception {
        storage.addItemFromString("1, BOOK, book, 2.0, thisIsTitle, 4");
        storage.addItemFromString("2, BOOK, book, 2.0, thisIsTitle, 4");
        storage.addItemFromString("3, OTHER, album, 2.0, thisIsTitle, 4");
        Item expected1 = new Book(1, "book", 2.0, new String[]{"thisIsTitle"});
        Item expected2 = new Book(2, "book", 2.0, new String[]{"thisIsTitle"});
        Set<Item> itemsFoundExpected = new HashSet<>();
        itemsFoundExpected.add(expected1);
        itemsFoundExpected.add(expected2);
        assertTrue(itemsFoundExpected.equals(storage.getItemsFromStorageByName("book")));
    }

    @Test
    public void testGetItemByNameIfHasSuchPartial() throws Exception {
        storage.addItemFromString("1, BOOK, book, 2.0, thisIsTitle, 4");
        storage.addItemFromString("2, BOOK, blah, 2.0, thisIsTitle, 4");
        storage.addItemFromString("3, OTHER, album, 2.0, thisIsTitle, 4");
        Item expected1 = new Book(1, "book", 2.0, new String[]{"thisIsTitle"});
        Item expected2 = new Book(2, "blah", 2.0, new String[]{"thisIsTitle"});
        Set<Item> itemsFoundExpected = new HashSet<>();
        itemsFoundExpected.add(expected1);
        itemsFoundExpected.add(expected2);
        assertTrue(itemsFoundExpected.equals(storage.getItemsFromStorageByName("b")));
    }

    @Test
    public void testGetItemByNameIfNoSuch() throws Exception {
        storage.addItemFromString("1, BOOK, book, 2.0, thisIsTitle, 4");
        assertThrows(ItemNotFoundException.class, () ->
        {
            storage.getItemsFromStorageByName("as");
        });
    }

    @Test
    public void testGetItemsByTypeIfHasSuchType() throws Exception {
        storage.addItemFromString("1, BOOK, book, 2.0, thisIsTitle, 4");
        storage.addItemFromString("2, BOOK, blah, 2.0, thisIsTitle, 4");
        storage.addItemFromString("3, OTHER, album, 2.0, 4");
        Item expected1 = new Book(1, "book", 2.0, new String[]{"thisIsTitle"});
        Item expected2 = new Book(2, "blah", 2.0, new String[]{"thisIsTitle"});
        Set<Item> itemsFoundExpected = new HashSet<>();
        itemsFoundExpected.add(expected1);
        itemsFoundExpected.add(expected2);
        assertTrue(itemsFoundExpected.equals(storage.getItemsFromStorageByType(ItemType.BOOK)));
    }

    @Test
    public void testGetItemsByTypeIfNoSuch() throws Exception {
        storage.addItemFromString("1, BOOK, book, 2.0, thisIsTitle, 4");
        assertThrows(ItemNotFoundException.class, () ->
        {
            storage.getItemsFromStorageByType(ItemType.OTHER);
        });
    }

    @Test
    public void testGetItemsByPriceRangeIfHasSuch() throws Exception {
        storage.addItemFromString("1, BOOK, book, 2.0, thisIsTitle, 4");
        storage.addItemFromString("2, BOOK, blah, 5.0, thisIsTitle, 4");
        storage.addItemFromString("3, OTHER, album, 2.0, 4");
        Item expected1 = new Book(1, "book", 2.0, new String[]{"thisIsTitle"});
        Item expected2 = new Item(3, "album", 2.0);
        Set<Item> itemsFoundExpected = new HashSet<>();
        itemsFoundExpected.add(expected1);
        itemsFoundExpected.add(expected2);
        assertTrue(itemsFoundExpected.equals(storage.getItemsFromStorageByPriceRange(1.0, 3.0)));
    }

    @Test
    public void testGetItemsByPriceRangeIfNoSuch() throws Exception {
        storage.addItemFromString("1, BOOK, book, 2.0, thisIsTitle, 4");
        assertThrows(ItemNotFoundException.class, () ->
        {
            storage.getItemsFromStorageByPriceRange(1.0, 1.5);
        });
    }

    @Test
    public void removeQuantityOfItemIfHasSuchQuantity() throws Exception {
        storage.addItemFromString("1, BOOK, book, 2.0, thisIsTitle, 4");
        storage.removeQuantityOfItem(1, 2);
        assertEquals(2, storage.getItemQuantityById(1).getQuantity());
    }

    @Test
    public void removeQuantityOfItemIfHasNoQuantity() throws Exception {
        storage.addItemFromString("1, BOOK, book, 2.0, thisIsTitle, 4");
        assertThrows(Exception.class, () ->
        {
            storage.removeQuantityOfItem(1, 5);
        });
    }

    @Test
    public void removeQuantityOfItemIfHasNoSuchItem() throws Exception {
        storage.addItemFromString("1, BOOK, book, 2.0, thisIsTitle, 4");
        assertThrows(ItemNotFoundException.class, () ->
        {
            storage.removeQuantityOfItem(2, 5);
        });
    }
}

