package shopTestPackage;

import org.junit.jupiter.api.Test;
import shopPackage.Book;
import shopPackage.InvalidInfoException;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    @Test
    public void testCompareSame() throws InvalidInfoException {
        Book book1 = new Book(1, "book", 2.0, new String[]{"title"});
        Book book2 = new Book(1, "book", 2.0, new String[]{"title"});
        assertTrue(book1.compare(book2));
    }

    @Test
    public void testCompareDifferent() throws InvalidInfoException {
        Book book1 = new Book(1, "book", 2.0, new String[]{"title1"});
        Book book2 = new Book(1, "book", 2.0, new String[]{"title2"});
        assertFalse(book1.compare(book2));
    }

    @Test
    public void testToString() throws InvalidInfoException {
        Book book = new Book(1, "book", 2.0, new String[]{"title"});
        String bookToString = "1, BOOK, book, 2.0, title";
        assertTrue(book.toString().equals(bookToString));
    }

    @Test
    public void constructorThrowsInvalidInfoException() {
        assertThrows(InvalidInfoException.class, () ->
        {
            Book book = new Book(1, "book", 2.0, new String[]{"title", "sth"});
        });
    }
}