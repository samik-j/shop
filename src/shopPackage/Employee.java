package shopPackage;

public class Employee
{
    private int id;
    private Storage storage;

    public Employee(int _id, Storage _storage)
    {
        id = _id;
        storage = _storage;
    }

    public void addItem(final Item itemToAdd, final int quantity)
    {
       // storage.addItem(itemToAdd, quantity);
    }
}
