package Lab_5.Zadanie_3;

import java.rmi.Naming;
import java.rmi.Remote;

/**
 *  connect to the RMI server, look up the PhoneBookFactory object,
 *  and use it to create an instance of the PhoneBook interface
 *  and call the methods to interact with the phone book.
 */
public class PhoneBookClient_rmi {
    private static final long serialVersionUID = 1L;
    private static final String ServerName = "rmi://localhost/PHONE-BOOK-SERVER";

    public static void main(String[] args)  {
        try {
            Remote remote = (PhoneBookFactory) Naming.lookup(ServerName);
            System.out.println(remote.getClass());
            if (remote instanceof PhoneBookFactory factory) {
                PhoneBook phoneBook = factory.getPhoneBook();
            phoneBook.putNumber("Janek", "234789654");
            String phoneNumber = phoneBook.getNumber("Janek");
            System.out.println("Janka numer to: " + phoneNumber);
            }
        } catch (Exception e) {

            System.out.println("Client exception: " );
            e.printStackTrace();
        }
    }
}
