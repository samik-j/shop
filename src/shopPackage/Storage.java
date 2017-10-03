package shopPackage;

import java.io.*;
import java.util.*;

public class Storage
{

    private Map<Integer, ItemQuantity> items;

    public Storage()
    {
        items = new HashMap<>();
    }

    private void addItem(final Item itemToAdd, final int quantity) throws MismatchItemTypeException
    {
        if(items.containsKey(itemToAdd.getId()) && getItemById(itemToAdd.getId()).getItemType().equals(itemToAdd.getItemType()))
            items.get(itemToAdd.getId()).addQuantity(quantity); // jak jest wlozone to sprawdzic czy jest ten sam typ
        else if(items.containsKey(itemToAdd.getId()) && !getItemById(itemToAdd.getId()).getItemType().equals(itemToAdd.getItemType()))
            throw new MismatchItemTypeException("Cannot add because item types are different");
        else
            items.put(itemToAdd.getId(), new ItemQuantity(itemToAdd, quantity));
    }

    public void addItemFromString(final String itemString) throws Exception // test dla book i other
    {
        try
        {
            final String itemElements[] = itemString.split(", ");
            final int id = Integer.parseInt(itemElements[0]);
            final String name = itemElements[2];
            final double price = Double.parseDouble(itemElements[3]);
            String[] info = new String[itemElements.length - 5];

            for (int i = 4, j = 0; i < itemElements.length - 1; i++, j++)
                info[j] = itemElements[i];

            final int quantity = Integer.parseInt(itemElements[itemElements.length - 1]);
            switch (ItemType.valueOf(itemElements[1]))
            {
                case BOOK:
                    addItem(new Book(id, name, price, info), quantity);
                    break;
                case OTHER:
                    addItem(new Item(id, name, price), quantity);
                    break;
            }
        }catch (MismatchItemTypeException e)
        {
            throw e;
        }catch (Exception e)
        {
            throw new Exception("Wrong Item information format");
        }
    }

    public void addItemsFromFile(final BufferedReader reader) throws Exception // test dla book i other
    {
        String sCurrentLine;
        try
        {
            while ((sCurrentLine = reader.readLine()) != null) {
                addItemFromString(sCurrentLine);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void printToFile(BufferedWriter writer) throws IOException // test book i other
    {
        try
        {
            for(Map.Entry<Integer, ItemQuantity> pair : items.entrySet())
            {
                writer.write(String.valueOf(pair.getValue().getItem()) + ", " + pair.getValue().getQuantity());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void printToFile(String fileName , BufferedWriter writer) throws IOException
    {
        PrintWriter clear = new PrintWriter("F:\\joanna\\java\\workspace\\shop\\storage\\" + fileName);
        clear.print("");
        clear.close();
        printToFile(writer);
    }

    public void printFromFile(BufferedReader reader)
    {
        try
        {
            String sCurrentLine;
            while ((sCurrentLine = reader.readLine()) != null)
                System.out.println(sCurrentLine);
        } catch (IOException e)
        {
            e.printStackTrace();
        }finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void readStorageFromFile(BufferedReader reader) throws Exception // test dla book i other
    {
        items.clear();
        try
        {
            String currentLine;
            do
            {
                currentLine = reader.readLine();
                if(currentLine != null)
                    addItemFromString(currentLine);
            }
            while (currentLine != null);
        } catch (IOException e)
        {
            e.printStackTrace();
        }finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void printCurrentStorageStatus()
    {
        for(Map.Entry<Integer, ItemQuantity> pair : items.entrySet())
            System.out.println(pair.getValue().getItem() + ", " + pair.getValue().getQuantity());
    }

    public ItemQuantity getItemQuantityById(int id)
    {
        return items.get(id);
    }

    public Map<Integer, ItemQuantity> getAllItems()
    {
        return items;
    }

    public Item getItemById(int id)
    {
        return items.get(id).getItem();
    }

}
