package shopPackage;

public class Clothing extends Item
{
    int size;

    public Clothing(int _id, String _name, double _price, int _size)
    {
        super(_id, _name, _price);
        size = _size;
        itemType = ItemType.CLOTHING;
    }


}
