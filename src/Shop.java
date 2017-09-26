import java.io.*;
import java.util.Locale;
import java.util.Scanner;

public class Shop
{
    private Storage storage;
    private Employee employee1;
    private static Scanner input = new Scanner(System.in).useLocale(Locale.US);

    public Shop(Storage _storage, Employee _employee1)
    {
        storage = _storage;
        employee1 = _employee1;
    }

    public static void main(String[] args) throws IOException
    {

        Storage storage = new Storage();
        Employee employee1 = new Employee(1234, storage);
        Shop shop = new Shop(storage, employee1);

        int action = -1;
        do
        {
            System.out.println(" 0 print storage log from file \n 1 load storage \n 2 save storage log \n 3 add items from file \n 4 add items \n 5 print current storage status \n 6 exit");
            action = input.nextInt();
            switch(action)
            {
                case 0:
                    System.out.print("file name: ");
                    storage.printFromFile(input.next());
                    break;
                case 1:
                    System.out.print("file name: ");
                    storage.readStorageFromFile(input.next());
                    break;
                case 2:
                    System.out.print("file name: ");
                    storage.printToFile(input.next());
                    break;
                case 3:
                    System.out.print("file name: ");
                    storage.addItemsFromFile(input.next());
                    break;
                case 4:
                    System.out.println("empty to exit");
                    System.out.println("item (id, name, price, quantity): ");
                    input.nextLine();
                    String inputS = "";
                    do
                    {
                        inputS = input.nextLine();
                        if(!inputS.isEmpty())
                            storage.addItemFromString(inputS);

                    }while(!inputS.isEmpty());
                    break;
                default:
                    break;
                case 5:
                    storage.printCurrentStorageStatus();
                    break;
            }
        }while(action != 6);
    }
}
