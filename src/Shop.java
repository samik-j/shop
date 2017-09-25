import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class Shop
{
    private Storage storage;
    private Employee employee1;

    public Shop(Storage _storage, Employee _employee1)
    {
        storage = _storage;
        employee1 = _employee1;
    }

    public static void main(String[] args) throws IOException
    {
        PrintWriter writer = null;
        BufferedReader reader = null;
        try
        {
            writer = new PrintWriter("F:\\joanna\\java\\workspace\\shop\\storage\\storage.txt", "UTF-8");
            reader = new BufferedReader(new FileReader("F:\\joanna\\java\\workspace\\shop\\storage\\storage.txt"));
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        Storage storage = new Storage();
        Employee employee1 = new Employee(1234, storage);
        Shop shop = new Shop(storage, employee1);

        employee1.addItem(new Item(1, "book", 10.0), 3);
        employee1.addItem(new Item(2, "boots", 20.0), 1);
        employee1.addItem(new Item(3, "coat", 10.0), 5);
        employee1.addItem(new Item(1, "book", 10.0), 3);

        storage.printToFile(writer);
        storage.readFromFile(reader);
    }
}
