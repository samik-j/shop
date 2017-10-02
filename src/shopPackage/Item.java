package shopPackage;

public class Item
{
    private int id;
    private String name;
    private double price;
    protected ItemType itemType;

    public Item(int _id, String _name, double _price)
    {
        itemType = ItemType.OTHER;
        id = _id;
        name = _name;
        price = _price;
    }

    public int getId()
    {
        return id;
    }

    public boolean compare(Item itemToCompare)
    {
        return itemType == itemToCompare.itemType && id == itemToCompare.id
                && name.equals(itemToCompare.name) && price == itemToCompare.price;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        return id == item.id;
    }

    @Override
    public int hashCode()
    {
        return id;
    }

    @Override
    public String toString()
    {
        return "" + id + ", " + itemType + ", " + name + ", " + price;
    }
}