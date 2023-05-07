package Lab_5.Zadanie_3;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * defines the methods that the client can use to interact with the phone book
 */
public interface PhoneBook extends Remote {

    String getNumber(String name) throws RemoteException;
    void putNumber (String name, String number) throws RemoteException;
}
