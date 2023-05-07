package Lab_5.Zadanie_3;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * starts the RMI registry, creates an instance of the PhoneBookFactory class,
 * and binds it to the registry.
 */

public class PhoneBookServer_rmi {
    private static final long serialVersionUID = 1L;
    private static final String ServerName = "rmi://localhost/PHONE-BOOK-SERVER";

    public static void main(String[] args) {
        try {
            final PhoneBookFactory factory = new PhoneBookFactory();
            LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            Naming.rebind(ServerName, factory);
            System.out.println("Server ready");
        } catch (Exception e) {
            System.out.println("Server exception: " + e);
        }
    }


}
