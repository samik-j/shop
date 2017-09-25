import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class Storage
{

    private Map<Item, Integer> items;

    public Storage()
    {
        items = new HashMap<>();
    }

    public void addItem(final Item itemToAdd, final int quantity)
    {
        if(items.containsKey(itemToAdd))
            items.put(itemToAdd, items.get(itemToAdd) + quantity);
        else
            items.put(itemToAdd, quantity);
    }

    public void printItems()
    {
        for(Map.Entry<Item, Integer> pair : items.entrySet())
            System.out.println(pair.getKey() + " quantity = " + pair.getValue());
    }

    public void printToFile(PrintWriter writer)
    {
        for(Map.Entry<Item, Integer> pair : items.entrySet())
            writer.println(pair.getKey() + ", " + pair.getValue());
        writer.close();
    }

    public void readFromFile(BufferedReader reader) throws IOException
    {
        String sCurrentLine;
        while ((sCurrentLine = reader.readLine()) != null) {
            System.out.println(sCurrentLine);
        }
    }

    public void readFromFileToStorage(BufferedReader reader) throws IOException
    {
        String sCurrentLine;
        while ((sCurrentLine = reader.readLine()) != null) {
            String itemElements[] = sCurrentLine.split(", ");
            Item itemToAdd = new Item(Integer.parseInt(itemElements[0]), itemElements[1], Double.parseDouble(itemElements[2]));
            addItem(itemToAdd, Integer.parseInt(itemElements[3]));
        }
    }
}
