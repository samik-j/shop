package shopPackage;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;

public class Shop {
    private Storage storage;
    private Employee employee1;
    private static Scanner input = new Scanner(System.in).useLocale(Locale.US);
    private static final String fileName = "storage.txt";

    public Shop(Storage _storage, Employee _employee1) {
        this.storage = _storage;
        this.employee1 = _employee1;
    }

    public static void main(String[] args) {

        Storage storage = new Storage();
        Employee employee1 = new Employee(1234, storage);
        Shop shop = new Shop(storage, employee1);

        shop.run();
    }

    private void run() {
        this.readStorageFromFile();

        int action = -1;
        do {
            action = getAction();
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
                default:
                    break;
            }
        } while (action != 99);

        this.printStorageToFile(this.fileName);
    }

    private void readStorageFromFile() {
        try {
            this.storage.readStorageFromFile(getBufferedReaderForFile(this.fileName));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static int getAction() {
        System.out.println("" +
                " 1 print storage log from alternative file \n" +
                " 2 save storage log to alternative file\n" +
                " 3 add items from alternative file \n" +
                " 4 add items \n" +
                " 5 print current storage status \n" +
                " 6 search item by id \n" +
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

    private void printStorageToFile(String fileName) {
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
        int id = input.nextInt();
        try {
            System.out.println(this.storage.getItemFromStorageById(id));
        } catch (ItemNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static BufferedReader getBufferedReaderForFile(String fileName) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("F:\\joanna\\java\\workspace\\shop\\storage\\" + fileName));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return reader;
    }
}