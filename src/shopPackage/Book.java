package shopPackage;

public class Book extends Item
{
    private String title;

    public Book(int _id, String _name, double _price)
    {
        super(_id, _name, _price);
        itemType = ItemType.BOOK;
    }

    public Book(int _id, String _name, double _price, String info)
    {
        super(_id, _name, _price);
        String itemElements[] = info.split(", ");
        title = itemElements[0];
        itemType = ItemType.BOOK;
    }
}
