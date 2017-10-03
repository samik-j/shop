package shopPackage;

import java.io.*;
import java.util.*;

public class Storage {

    private Map<Integer, ItemQuantity> items;

    public Storage() {
        this.items = new HashMap<>();
    }

    private void addItem(final Item itemToAdd, final int quantity) throws MismatchItemTypeException {
        if (this.items.containsKey(itemToAdd.getId())) {
            if (this.getItemById(itemToAdd.getId()).getItemType().equals(itemToAdd.getItemType()))
                this.items.get(itemToAdd.getId()).addQuantity(quantity);
            else
                throw new MismatchItemTypeException("Cannot add because item types are different");
        } else
            this.items.put(itemToAdd.getId(), new ItemQuantity(itemToAdd, quantity));
    }

    public void addItemFromString(final String itemString) throws Exception {
        try {
            final String itemElements[] = itemString.split(", ");
            final int id = Integer.parseInt(itemElements[0]);
            final String name = itemElements[2];
            final double price = Double.parseDouble(itemElements[3]);
            String[] info = new String[itemElements.length - 5];

            for (int i = 4, j = 0; i < itemElements.length - 1; i++, j++)
                info[j] = itemElements[i];

            final int quantity = Integer.parseInt(itemElements[itemElements.length - 1]);
            switch (ItemType.valueOf(itemElements[1])) {
                case BOOK:
                    this.addItem(new Book(id, name, price, info), quantity);
                    break;
                case CLOTHING:
                    this.addItem(new Clothing(id, name, price, info), quantity);
                    break;
                case OTHER:
                    this.addItem(new Item(id, name, price), quantity);
                    break;
            }
        } catch (MismatchItemTypeException e) {
            throw e;
        } catch (InvalidInfoException e) {
            throw e;
        } catch (Exception e) {
            throw new Exception("Wrong Item information format");
        }
    }

    public void addItemsFromFile(final BufferedReader reader) throws Exception {
        String sCurrentLine;
        try {
            while ((sCurrentLine = reader.readLine()) != null) {
                this.addItemFromString(sCurrentLine);
            }
        } catch (IOException e) {
            throw new IOException("Error while reading file");
        } finally {
            tryCloseReader(reader);
        }
    }

    public void printToFile(BufferedWriter writer) throws IOException {
        try {
            for (Map.Entry<Integer, ItemQuantity> pair : this.items.entrySet()) {
                writer.write(String.valueOf(pair.getValue().getItem()) + ", " + pair.getValue().getQuantity());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            throw new IOException("Error while writing to file");
        }
    }

    public void clearAndPrintToFile(String fileName, BufferedWriter writer) throws IOException {
        try {
            PrintWriter clear = new PrintWriter("F:\\joanna\\java\\workspace\\shop\\storage\\" + fileName);
            clear.print("");
            clear.close();
            this.printToFile(writer);
        } catch (IOException e) {
            throw new IOException("Error while reading file");
        }
    }

    public static void printFromFile(BufferedReader reader) throws IOException {
        try {
            String currentLine;
            do {
                currentLine = reader.readLine();
                if (currentLine != null)
                    System.out.println(currentLine);
            }
            while (currentLine != null);
        } catch (IOException e) {
            throw new IOException("Error while reading file");
        } finally {
            tryCloseReader(reader);
        }
    }

    public void readStorageFromFile(BufferedReader reader) throws Exception {
        this.items.clear();
        this.addItemsFromFile(reader);
    }

    public void printCurrentStorageStatus() {
        for (Map.Entry<Integer, ItemQuantity> pair : this.items.entrySet())
            System.out.println(pair.getValue().getItem() + ", " + pair.getValue().getQuantity());
    }

    public ItemQuantity getItemQuantityById(int id) {
        return this.items.get(id);
    }

    public Map<Integer, ItemQuantity> getAllItems() {
        return this.items;
    }

    private Item getItemById(int id)
    {
        return this.items.get(id).getItem();
    }

    public Item getItemFromStorageById(int id) throws ItemNotFoundException {
        if(!this.items.containsKey(id))
            throw new ItemNotFoundException("Item not found");
        return this.items.get(id).getItem();
    }

    private static void tryCloseReader(BufferedReader reader) throws IOException {
        try {
            reader.close();
        } catch (IOException e) {
            throw new IOException("Error while reading file");
        }
    }

}
