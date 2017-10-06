package shopPackage;

public class Book extends Item {
    private String title;

    public Book(int _id, String _name, double _price, String[] info) throws InvalidInfoException {
        super(_id, _name, _price);
        if (info.length != 1)
            throw new InvalidInfoException("Invalid additional info");
        this.title = info[0];
        this.itemType = ItemType.BOOK;
    }

    @Override
    public boolean compare(final Item bookToCompare) {
        if (bookToCompare == null || this.getClass() != bookToCompare.getClass()) return false;
        Book book = (Book) bookToCompare;
        return super.compare(book) && this.title.equals(book.title);
    }

    @Override
    public String toString() {
        return super.toString() + ", " + this.title;
    }
}
