package shopPackage;

public class Clothing extends Item {
    int size;

    public Clothing(int _id, String _name, double _price, String[] info) throws InvalidInfoException {
        super(_id, _name, _price);
        if (info.length != 1)
            throw new InvalidInfoException("Invalid additional info");
        this.size = Integer.parseInt(info[0]);
        this.itemType = ItemType.CLOTHING;
    }


    @Override
    public boolean compare(final Item clothingToCompare) {
        if (clothingToCompare == null || this.getClass() != clothingToCompare.getClass()) return false;
        Clothing clothing = (Clothing) clothingToCompare;
        return super.compare(clothing) && this.size == clothing.size;
    }

    @Override
    public String toString() {
        return super.toString() + ", " + this.size;
    }

}
