package shopPackage;

public class Item {
    private int id;
    private String name;
    private double price;
    protected ItemType itemType;

    public Item(int _id, String _name, double _price) {
        this.itemType = ItemType.OTHER;
        this.id = _id;
        this.name = _name;
        this.price = _price;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public ItemType getItemType() {
        return this.itemType;
    }

    public double getPrice() {
        return this.price;
    }

    public void changePrice(final double newPrice) {
        this.price = newPrice;
    }

    public boolean compare(final Item itemToCompare) { // test it
        return this.itemType == itemToCompare.itemType && this.id == itemToCompare.id
                && this.name.equals(itemToCompare.name) && this.price == itemToCompare.price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        return this.id == item.id;
    }

    @Override
    public int hashCode() {
        return this.id;
    }

    @Override
    public String toString() { // test it
        return "" + this.id + ", " + this.itemType + ", " + this.name + ", " + this.price;
    }
}