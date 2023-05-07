package Lab_5.Zadanie_3;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * acts as a factory for creating instances of the PhoneDictionaryImpl class
 * and bind it to the RMI registry.
 */
public class PhoneBookFactory extends UnicastRemoteObject implements Remote {
    private static final long serialVersionUID = 1L;

    protected PhoneBookFactory() throws RemoteException {
        super();
    }

    public PhoneBook getPhoneBook() throws RemoteException {
        return new PhoneBook_impl();
    }
}
