package shopPackage;

public class Book extends Item
{
    private String title;

    public Book(int _id, String _name, double _price, String[] info)
    {
        super(_id, _name, _price);
        title = info[0];
        itemType = ItemType.BOOK;
    }
    //zrobic compare zeby bylo do testow

    @Override
    public String toString()
    {
        return super.toString() + ", " + title;
    }
}
