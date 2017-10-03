package shopPackage;

public class ItemQuantity {

    private Item item;
    private int quantity;

    public ItemQuantity(Item _item, int _quantity) {
        this.item = _item;
        this.quantity = _quantity;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void addQuantity(int _quantity) {
        this.quantity += _quantity;
    }

    public Item getItem() {
        return this.item;
    }

    public boolean compare(ItemQuantity itemQuantityToCompare) {
        return this.item.compare(itemQuantityToCompare.getItem()) && this.quantity == itemQuantityToCompare.getQuantity();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemQuantity other = (ItemQuantity) o;

        if (this.quantity != other.quantity) return false;
        return this.item != null ? this.item.equals(other.item) : other.item == null;
    }

}
