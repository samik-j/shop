package shopPackage;

import java.io.*;
import java.util.*;

public class Shop {
    private Storage storage;
    private static final Scanner input = new Scanner(System.in).useLocale(Locale.US);
    private static final String fileName = "storage.txt";

    public Shop(Storage _storage) {
        this.storage = _storage;
    }

    public static void main(String[] args) {

        Storage storage = new Storage();
        Shop shop = new Shop(storage);

        shop.run();
    }

    private void run() {
        this.readStorageFromFile();

        String action ="";
        do {
            System.out.println("run as \tcustomer | c\n " +
                    "\t\temployee | e\n" +
                    "\t\texit");
            action = input.next();
            if(action.equals("c"))
                this.runAsCustomer();
            else if(action.equals("e"))
                this.runAsEmployee();
        }while(!action.equals("exit"));

        this.printStorageToFile(this.fileName);

    }

    private void readStorageFromFile() {
        try {
            this.storage.readStorageFromFile(getBufferedReaderForFile(this.fileName));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void runAsCustomer() {
        int action = -1;
        do {
            action = this.getCustomerAction();
            switch(action) {
                case 1:
                    this.searchItemByName();
                    break;
                case 2:
                    this.searchItemByType();
                    break;
                case 3:
                    this.searchItemByPriceRange(searchItemByType());
                    break;
                case 4:
                    this.buyItem();
                    break;
                case 5:
                    this.buyMultipleItems();
                    break;
                default:
                    break;
            }
        }while(action != 0);
    }

    private void runAsEmployee() {
        int action = -1;
        do {
            action = this.getEmployeeAction();
            switch (action) {
                case 1:
                    this.printStorageFromFile();
                    break;
                case 2:
                    System.out.print("file name:\t");
                    this.printStorageToFile(input.next());
                    break;
                case 3:
                    this.addItemsToStorageFromFile();
                    break;
                case 4:
                    this.addItemsToStorage();
                    break;
                case 5:
                    this.storage.printCurrentStorageStatus();
                    break;
                case 6:
                    this.searchItemById();
                    break;
                case 7:
                    this.searchItemByName();
                    break;
                case 8:
                    this.changePrice();
                    break;
                case 9:
                    this.removeItemFromStorage();
                    break;
                default:
                    break;
            }
        } while (action != 0);
    }

    private static int getCustomerAction() {
        System.out.println("" +
                " 1 search by name\n" +
                " 2 search by type\n" +
                " 3 search by price range\n" +
                " 4 buy\n" +
                " 5 buy multiple\n" +
                " 0 exit");
        return input.nextInt();
    }

    private static int getEmployeeAction() {
        System.out.println("" +
                " 1 print storage log from alternative file\n" +
                " 2 save storage log to alternative file\n" +
                " 3 add items from alternative file\n" +
                " 4 add items\n" +
                " 5 print current storage status\n" +
                " 6 search item by id\n" +
                " 7 search item by name\n" +
                " 8 change price\n" +
                " 9 remove item\n" +
                " 0 exit");
        return input.nextInt();
    }

    private void printStorageFromFile() {
        System.out.print("file name:\t");
        try {
            Storage.printFromFile(getBufferedReaderForFile(input.next()));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void printStorageToFile(final String fileName) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("F:\\joanna\\java\\workspace\\shop\\storage\\" + fileName, true));
            this.storage.clearAndPrintToFile(fileName, writer);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void addItemsToStorageFromFile() {
        System.out.print("file name:\t");
        try {
            this.storage.addItemsFromFile(getBufferedReaderForFile(input.next()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void addItemsToStorage() {
        System.out.println("empty to exit");
        System.out.println("item (id, type, name, price, info..., quantity)");
        input.nextLine();
        String inputS = "";
        do {
            inputS = input.nextLine();
            if (!inputS.isEmpty())
                try {
                    this.storage.addItemFromString(inputS);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
        } while (!inputS.isEmpty());
    }

    private void searchItemById() {
        System.out.print("enter id to search for\t");
        final int id = input.nextInt();
        try {
            System.out.println(this.storage.getItemFromStorageById(id));
        } catch (ItemNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void searchItemByName() {
        System.out.print("enter name of item to search for\t");
        input.nextLine();
        final String name = input.nextLine();
        try {
            this.printItems(this.storage.getItemsFromStorageByName(name));
        } catch (ItemNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private Set<Item> searchItemByType() {
        Set<Item> items = new HashSet<>();
        System.out.println("enter type of item to search for\nBOOK | CLOTHING | OTHER");
        input.nextLine();
        final ItemType itemType = ItemType.valueOf(input.nextLine());
        try {
            items.addAll(this.storage.getItemsFromStorageByType(itemType));
            this.printItems(items);
        } catch (ItemNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return items;
    }

    private void searchItemByPriceRange(Set<Item> items) {
        System.out.println("enter price range");
        System.out.print("min: ");
        final double min = input.nextDouble();
        System.out.print("max: ");
        final double max = input.nextDouble();
        try {
            this.printItems(this.storage.getItemsFromStorageByPriceRange(min, max, items));
        } catch (ItemNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void changePrice() {
        System.out.print("enter item id\t");
        final int id = input.nextInt();
        try {
            System.out.println(this.storage.getItemFromStorageById(id));
            System.out.print("enter new price\t");
            final double newPrice = input.nextDouble();
            this.storage.getItemFromStorageById(id).changePrice(newPrice);
        } catch (ItemNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void removeItemFromStorage() {
        System.out.print("enter item id\t");
        final int id = input.nextInt();
        try {
            this.storage.removeItem(id);
            System.out.println("removed item by id\t" + id);
        } catch (ItemNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void buyItem() {
        System.out.print("enter item id\t");
        final int id = input.nextInt();
        System.out.print("enter quantity\t");
        final int quantity = input.nextInt();
        try {
            this.storage.addQuantityById(id, -quantity);
            System.out.println("bought |" + this.storage.getItemFromStorageById(id) + "| in quantity " + quantity);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void buyMultipleItems() {
        String addAnother = "";
        Map<Integer, Integer> toBuy = new HashMap<>();
        do {
            addToBuy(toBuy);
            System.out.println("add more items\t y | n");
            addAnother = input.next();
        }while(!addAnother.equals("n"));
        try {
            if(!toBuy.isEmpty()) {
                for (Map.Entry<Integer, Integer> pair : toBuy.entrySet()) {
                    this.storage.addQuantityById(pair.getKey(), -pair.getValue());
                    System.out.println("bought |" + this.storage.getItemFromStorageById(pair.getKey()) + "| in quantity " + pair.getValue());
                }
            }
            else
                System.out.println("no items bought");
        } catch(Exception e) {
            e.getMessage();
        }
    }

    private void addToBuy(Map<Integer, Integer> toBuy) {
        System.out.print("enter item id\t");
        int id = input.nextInt();
        System.out.print("enter quantity\t");
        final int quantity = input.nextInt();
        try {
            if (this.storage.hasQuantity(id, quantity)) {
                if (toBuy.containsKey(id))
                    toBuy.put(id, toBuy.get(id) + quantity);
                else
                    toBuy.put(id, quantity);
            } else
                System.out.println("quantity available\t" + this.storage.getItemQuantityById(id).getQuantity());
        } catch (ItemNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void printItems(Set<Item> items) {
        for(Item item : items)
            System.out.println(item);
    }

    private static BufferedReader getBufferedReaderForFile(final String fileName) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("F:\\joanna\\java\\workspace\\shop\\storage\\" + fileName));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return reader;
    }

}