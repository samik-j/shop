import java.io.*;
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

    public void addItemFromInput(String input)
    {
        String itemElements[] = input.split(", ");
        addItem(new Item(Integer.parseInt(itemElements[0]), itemElements[1], Double.parseDouble(itemElements[2])),
                Integer.parseInt(itemElements[3]));
    }

    public void printItems()
    {
        for(Map.Entry<Item, Integer> pair : items.entrySet())
            System.out.println(pair.getKey() + " quantity = " + pair.getValue());
    }

    public void readFromFile(String fileName)
    {
        BufferedReader reader = null;
        try
        {
            reader = new BufferedReader(new FileReader("F:\\joanna\\java\\workspace\\shop\\storage\\" + fileName));
            String sCurrentLine;
            while ((sCurrentLine = reader.readLine()) != null) {
                System.out.println(sCurrentLine);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void printToFile(String fileName)
    {
        /*
        PrintWriter writer = null;
        try
        {
            writer = new PrintWriter("F:\\joanna\\java\\workspace\\shop\\storage\\" + fileName, "UTF-8");
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        for(Map.Entry<Item, Integer> pair : items.entrySet())
            writer.println(pair.getKey() + ", " + pair.getValue());
        writer.close();
        */
        BufferedWriter writer = null;
        try
        {
            writer = new BufferedWriter(new FileWriter("F:\\joanna\\java\\workspace\\shop\\storage\\" + fileName));
            for(Map.Entry<Item, Integer> pair : items.entrySet())
            {
                writer.write(pair.getKey() + ", " + pair.getValue());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void addItemsFromFile(String fileName)
    {
        BufferedReader reader = null;
        try
        {
            reader = new BufferedReader(new FileReader("F:\\joanna\\java\\workspace\\shop\\storage\\" + fileName));
            String sCurrentLine;
            while ((sCurrentLine = reader.readLine()) != null) {
                String itemElements[] = sCurrentLine.split(", ");
                addItem(new Item(Integer.parseInt(itemElements[0]), itemElements[1], Double.parseDouble(itemElements[2])),
                        Integer.parseInt(itemElements[3]));
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void readStorageFromFile(String fileName)
    {
        items.clear();
        BufferedReader reader = null;
        try
        {
            reader = new BufferedReader(new FileReader("F:\\joanna\\java\\workspace\\shop\\storage\\" + fileName));
            String sCurrentLine;
            while ((sCurrentLine = reader.readLine()) != null) {
                String itemElements[] = sCurrentLine.split(", ");
                addItem(new Item(Integer.parseInt(itemElements[0]), itemElements[1], Double.parseDouble(itemElements[2])),
                        Integer.parseInt(itemElements[3]));
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void printCurrentStorageStatus()
    {
        for(Map.Entry<Item, Integer> pair : items.entrySet())
            System.out.println(pair.getKey() + ", " + pair.getValue());
    }

}
