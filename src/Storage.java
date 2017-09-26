import java.io.*;
import java.util.*;

public class Storage
{

    private Map<Item, Integer> items;
// klasa <Item, int> int to quantity
    //i potem bedzie Map<id, ta klasa

    public Storage()
    {
        items = new HashMap<>();
    }

//searchByName i searchById

    private void addItem(final Item itemToAdd, final int quantity)
    {
        if(items.containsKey(itemToAdd))
            items.put(itemToAdd, items.get(itemToAdd) + quantity);
        else
            items.put(itemToAdd, quantity);
    }

    public void addItemFromString(final String itemString)
    {
        final String itemElements[] = itemString.split(", ");
        addItem(new Item(Integer.parseInt(itemElements[0]), itemElements[1], Double.parseDouble(itemElements[2])),
                Integer.parseInt(itemElements[3]));
    }

    public void printFromFile(final String fileName)
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

    public void printToFile(final String fileName)
    {
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

    public void addItemsFromFile(final String fileName)
    {
        BufferedReader reader = null;
        try
        {
            reader = new BufferedReader(new FileReader("F:\\joanna\\java\\workspace\\shop\\storage\\" + fileName));
            String sCurrentLine;
            while ((sCurrentLine = reader.readLine()) != null) {
                final String itemElements[] = sCurrentLine.split(", ");
                addItem(new Item(Integer.parseInt(itemElements[0]), itemElements[1], Double.parseDouble(itemElements[2])),
                        Integer.parseInt(itemElements[3]));
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void readStorageFromFile(final String fileName)
    {
        items.clear();
        BufferedReader reader = null;
        try
        {
            reader = new BufferedReader(new FileReader("F:\\joanna\\java\\workspace\\shop\\storage\\" + fileName));
            String sCurrentLine;
            while ((sCurrentLine = reader.readLine()) != null) {
                addItemFromString(sCurrentLine);
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

    //przekombinowane?
    private List<Item> getItemsSortedById()
    {
        List<Item> itemsSortedByIds = new ArrayList<>();
        for(int i = 0; i < getSortedIds().size(); i++)
        {
            for(Item item : items.keySet())
                if(getSortedIds().get(i) == item.getId())
                    itemsSortedByIds.add(item);
        }
        return itemsSortedByIds;
    }

    private List<Integer> getSortedIds()
    {
        List<Integer> ids = new ArrayList<>();
        for(Map.Entry<Item, Integer> pair : items.entrySet())
            ids.add(pair.getKey().getId());
        Collections.sort(ids);
        return ids;
    }
}
