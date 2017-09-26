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
        String fileName = "storage.txt";
        BufferedReader reader = new BufferedReader(new FileReader("F:\\joanna\\java\\workspace\\shop\\storage\\" + fileName));
        BufferedWriter writer = new BufferedWriter(new FileWriter("F:\\joanna\\java\\workspace\\shop\\storage\\" + fileName, true));

        Storage storage = new Storage(reader, writer);
        Employee employee1 = new Employee(1234, storage);
        Shop shop = new Shop(storage, employee1);

        storage.readStorageFromFile();

        int action = -1;
        do
        {
            System.out.println("" +
                    " 1 print storage log from alternative file \n" +
                    " 2 save storage log to alternative file\n" +
                    " 3 add items from alternative file \n" +
                    " 4 add items \n" +
                    " 5 print current storage status \n" +
                    " 6 exit and save to default file");
            action = input.nextInt();
            switch(action)
            {
                case 1:
                    System.out.print("file name: ");
                    BufferedReader readerAlternative2 = null;
                    try
                    {
                        readerAlternative2 = new BufferedReader(new FileReader("F:\\joanna\\java\\workspace\\shop\\storage\\" + input.next()));
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    storage.printFromFile(readerAlternative2);
                    break;
                case 2:
                    System.out.print("file name: ");
                    BufferedWriter writerAlternative = null;
                    writerAlternative = new BufferedWriter(new FileWriter("F:\\joanna\\java\\workspace\\shop\\storage\\" + input.next()));
                    storage.printToFile(writerAlternative);
                    break;
                case 3:
                    System.out.print("file name: ");
                    BufferedReader readerAlternative = null;
                    try
                    {
                        readerAlternative = new BufferedReader(new FileReader("F:\\joanna\\java\\workspace\\shop\\storage\\" + input.next()));
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
                    storage.addItemsFromFile(readerAlternative);
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

        storage.printToFile(fileName);
    }
}
