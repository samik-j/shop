package shopPackage;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;

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
                    "\t\temployee | e");
            action = input.next();
            if(action.equals("c"))
                runAsCustomer();
            else if(action.equals("e"))
                runAsEmployee();
        }while(action != "c" || action !="e");

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

    }

    private void runAsEmployee() {
        int action = -1;
        do {
            action = getEmployeeAction();
            switch (action) {
                case 1:
                    this.printStorageFromFile();
                    break;
                case 2:
                    System.out.print("file name: ");
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
                default:
                    break;
            }
        } while (action != 99);
    }

    private static int getEmployeeAction() {
        System.out.println("" +
                " 1 print storage log from alternative file \n" +
                " 2 save storage log to alternative file\n" +
                " 3 add items from alternative file \n" +
                " 4 add items \n" +
                " 5 print current storage status \n" +
                " 6 search item by id \n" +
                " 7 search item by name \n" +
                " 8 change price \n" +
                " 99 exit and save to default file");
        return input.nextInt();
    }

    private void printStorageFromFile() {
        System.out.print("file name: ");
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
        System.out.print("file name: ");
        try {
            this.storage.addItemsFromFile(getBufferedReaderForFile(input.next()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void addItemsToStorage() {
        System.out.println("empty to exit");
        System.out.println("item (id, type, name, price, info..., quantity): ");
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
        System.out.println("enter id to search for:");
        final int id = input.nextInt();
        try {
            System.out.println(this.storage.getItemFromStorageById(id));
        } catch (ItemNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void searchItemByName() {
        System.out.println("enter name of item to search for:");
        input.nextLine();
        final String name = input.nextLine();
        try {
            for(Item item : this.storage.getItemFromStorageByName(name))
                System.out.println(item);
        } catch (ItemNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void changePrice() {
        System.out.println("enter item id");
        final int id = input.nextInt();
        try {
            System.out.println(this.storage.getItemFromStorageById(id));
            System.out.println("enter new price");
            final double newPrice = input.nextDouble();
            this.storage.getItemFromStorageById(id).changePrice(newPrice);
        } catch (ItemNotFoundException e) {
            System.out.println(e.getMessage());
        }
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