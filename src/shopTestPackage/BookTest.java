package shopTestPackage;

import org.junit.jupiter.api.Test;
import shopPackage.Book;
import shopPackage.InvalidInfoException;
import shopPackage.Item;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    @Test
    public void testCompareSame() throws InvalidInfoException {
        final Item book1 = new Book(1, "book", 2.0, new String[]{"title"});
        final Item book2 = new Book(1, "book", 2.0, new String[]{"title"});
        assertTrue(book1.compare(book2));
    }

    @Test
    public void testCompareDifferent() throws InvalidInfoException {
        final Item book1 = new Book(1, "book", 2.0, new String[]{"title1"});
        final Item book2 = new Book(1, "book", 2.0, new String[]{"title2"});
        assertFalse(book1.compare(book2));
    }

    @Test
    public void testToString() throws InvalidInfoException {
        final Item book = new Book(1, "book", 2.0, new String[]{"title"});
        final String bookToString = "1, BOOK, book, 2.0, title";
        assertTrue(book.toString().equals(bookToString));
    }

    @Test
    public void constructorThrowsInvalidInfoException() {
        assertThrows(InvalidInfoException.class, () ->
        {
            final Item book = new Book(1, "book", 2.0, new String[]{"title", "sth"});
        });
    }
}