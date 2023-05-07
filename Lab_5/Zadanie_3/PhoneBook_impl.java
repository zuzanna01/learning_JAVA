package Lab_5.Zadanie_3;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This class implements the PhoneDictionary interface and provides
 * the actual implementation for the methods defined in the interface
 */
public class PhoneBook_impl extends UnicastRemoteObject implements PhoneBook {
    private static final long serialVersionUID = 1L;
    private  ConcurrentHashMap<String, String> phone_book;

    protected PhoneBook_impl() throws RemoteException {
        super();
        phone_book=loadFile();
    }

    @Override
    public String getNumber(String name) throws RemoteException {
        String number = phone_book.get(name);
        return number;
    }

    @Override
    public void putNumber(String name, String number) throws RemoteException {
        phone_book.put(name,number);
    }

    public static ConcurrentHashMap<String, String> loadFile() {
        ConcurrentHashMap<String, String> phoneBook = new ConcurrentHashMap<>();
        String fileName = "phone_book.txt";
        File file;
        file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println(fileName + " has been created!");
            } catch (IOException e) {
                System.err.println("Server exception: " + e);
            }
        } else {
            try {
                List<String> lines = Files.readAllLines(Paths.get(fileName));
                for (String line : lines) {
                    String[] split_line = line.split(" ");
                    phoneBook.put(split_line[0], split_line[1]);
                }
            } catch (IOException e) {
                System.err.println("Server exception: " + e);
            }
        }
        return phoneBook;
    }
}
