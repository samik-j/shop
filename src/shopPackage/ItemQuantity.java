package shopPackage;

public class ItemQuantity
{

    private Item item;
    private int quantity;

    public ItemQuantity(Item _item, int _quantity)
    {
        item = _item;
        quantity = _quantity;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void addQuantity(int _quantity)
    {
        quantity += _quantity;
    }

    public Item getItem()
    {
        return item;
    }

    public boolean compare(ItemQuantity itemQuantityToCompare)
    {
        return item.compare(itemQuantityToCompare.getItem()) && quantity == itemQuantityToCompare.getQuantity();
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemQuantity that = (ItemQuantity) o;

        if (quantity != that.quantity) return false;
        return item != null ? item.equals(that.item) : that.item == null;
    }

}
