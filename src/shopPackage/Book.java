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

    public boolean compare(Book bookToCompare) {
        return super.compare(bookToCompare) && this.title.equals(bookToCompare.title);
    }

    @Override
    public String toString() {
        return super.toString() + ", " + this.title;
    }
}
