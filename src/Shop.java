import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
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

        int action;
        do
        {
            System.out.println("1 print storage log | 2 load storage log | 3 save storage log | 4 add items | 5 exit");
            action = input.nextInt();
            switch(action)
            {
                case 1:
                    System.out.print("input file name: ");
                    storage.readFromFile(input.next());
                    break;
                case 2:
                    System.out.print("input file name: ");
                    storage.readFromFileToStorage(input.next());
                    break;
                case 3:
                    System.out.print("input file name: ");
                    storage.printToFile(input.next());
                    break;
                case 4:
                    System.out.println("empty to exit");
                    System.out.println("input item (id, name, price, quantity): ");
                    input.nextLine();
                    String inputS = "";
                    do
                    {
                        inputS = input.nextLine();
                        if(!inputS.isEmpty())
                            storage.addItemFromInput(inputS);

                    }while(!inputS.isEmpty());
                    break;
                default:
                    break;
            }
        }while(action != 5);
    }
}
