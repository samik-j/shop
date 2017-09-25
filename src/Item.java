public class Item
{
    private int id;
    private String name;
    private double price;

    public Item(int _id, String _name, double _price)
    {
        id = _id;
        name = _name;
        price = _price;
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
        return "" + id + ", " + name + ", " + price;
    }
}