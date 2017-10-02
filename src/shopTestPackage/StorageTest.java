package shopTestPackage;

import org.junit.jupiter.api.Test;
import shopPackage.Book;
import shopPackage.Item;
import shopPackage.ItemQuantity;
import shopPackage.Storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StorageTest
{
    Storage storage = new Storage();

    @Test
    public void testReadStorageFromFile() throws IOException
    {
        BufferedReader reader = mock(BufferedReader.class);
        when(reader.readLine()).thenReturn("1, OTHER, book, 2.3, 2", null);
        storage.readStorageFromFile(reader);
        ItemQuantity expected = new ItemQuantity(new Item(1, "book", 2.3), 2);
        assertTrue(storage.getItemQuantityById(1).compare(expected));
    }

    @Test
    public void defaultConstructorReturnEmptyStorage()
    {
        assertTrue(storage.getAllItems().isEmpty());
    }

    @Test
    public void addItemFromString()
    {
        storage.addItemFromString("1, OTHER, book, 2.0, 4");
        ItemQuantity expected = new ItemQuantity(new Item(1, "book", 2.0), 4);
        assertTrue(storage.getItemQuantityById(1).compare(expected));
    }

    @Test
    public void addItemFromStringMultipleItemsSameId()
    {
        storage.addItemFromString("1, OTHER, book, 2.0, 4");
        storage.addItemFromString("1, OTHER, book, 2.0, 9");
        ItemQuantity expected = new ItemQuantity(new Item(1, "book", 2.0), 13);
        assertTrue(storage.getItemQuantityById(1).compare(expected));
    }

    @Test
    public void addItemFromStringMultipleEntries()
    {
        storage.addItemFromString("1, OTHER, book, 2.0, 4");
        storage.addItemFromString("2, OTHER, coat, 2.0, 4");
        ItemQuantity expected1 = new ItemQuantity(new Item(1, "book", 2.0), 4);
        ItemQuantity expected2 = new ItemQuantity(new Item(2, "coat", 2.0), 4);
        Map<Integer, ItemQuantity> expected = new HashMap<>();
        expected.put(1, expected1);
        expected.put(2, expected2);
        assertTrue(storage.getAllItems().equals(expected));
    }

    @Test
    public void addItemsFromFile() throws IOException
    {
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
    public void testGetItemQuantityByIdNoSuchIdReturnsNull()
    {
        storage.addItemFromString("1, OTHER, book, 2.0, 4");
        assertEquals(storage.getItemQuantityById(2), null);
    }

    @Test
    public void testGetItemQuantityById()
    {
        storage.addItemFromString("1, OTHER, book, 2.0, 4");
        assertTrue(storage.getItemQuantityById(1).compare(new ItemQuantity(new Item(1, "book", 2.0), 4)));
    }

    @Test
    public void testGetAllItems()
    {
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
    public void testPrintToFile() throws IOException
    {
        storage.addItemFromString("1, OTHER, book, 2.0, 4");
        storage.addItemFromString("2, OTHER, sth, 2.0, 4");
        BufferedWriter writer = mock(BufferedWriter.class);
        storage.printToFile(writer);
        verify(writer, times(1)).write("1, OTHER, book, 2.0, 4");
        verify(writer, times(1)).write("2, OTHER, sth, 2.0, 4");
        verify(writer, times(2)).newLine();
        verify(writer, times(1)).close();
    }

    @Test
    public void addItemFromStringIfBook()
    {
        storage.addItemFromString("1, BOOK, book, 2.0, thisIsTitle, 4");
        String[] info = {"thisIsTitle"};
        ItemQuantity expected = new ItemQuantity(new Book(1, "book", 2.0, info), 4);
        assertTrue(storage.getItemQuantityById(1).compare(expected));
    }

    @Test
    public void testPrintToFileDifferentItemTypes() throws IOException
    {
        storage.addItemFromString("1, BOOK, book, 2.0, thisIsTitle, 4");
        storage.addItemFromString("2, OTHER, sth, 2.0, 4");
        BufferedWriter writer = mock(BufferedWriter.class);
        storage.printToFile(writer);
        verify(writer, times(1)).write("1, BOOK, book, 2.0, thisIsTitle, 4");
        verify(writer, times(1)).write("2, OTHER, sth, 2.0, 4");
        verify(writer, times(2)).newLine();
        verify(writer, times(1)).close();
    }

}

