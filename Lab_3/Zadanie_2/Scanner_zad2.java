package Lab_3.Zadanie_2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;

public class Scanner_zad2 {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        String key;
        String value="";
        boolean change = false;
        Properties prop = new Properties();
        FileInputStream fin = null;

        try {
            fin = new FileInputStream("PropertiesDemo.dat");
        } catch (FileNotFoundException e) {

        }

        try {
            if (fin != null) {
                prop.load(fin);
                fin.close();
            }
        } catch (IOException e) {
            System.out.println("Blad odczytu pliku.");
        }

        do {
            System.out.println("Podaj klucz(parametr): ");
            System.out.println("Wpisz 'koniec', aby zakończyć");
            key = scanner.nextLine();
            if (key.equals("koniec")) continue;
            boolean is_good=false;
            while (is_good==false) {
                try {

                    System.out.println("Padaj typ parametru: \n(1) String (2) int (3) double (4)boolean");
                    String type = scanner.nextLine();
                    System.out.println("Podaj wartość: ");
                    value = scanner.nextLine();
                    switch (type) {
                        case "1":
                            is_good = true;
                            break;
                        case "2":
                            Integer value_int = Integer.parseInt(value);
                            value = String.valueOf(value_int);
                            is_good = true;
                            break;
                        case "3":
                            Double value_double = Double.parseDouble(value);
                            value= String.valueOf(value_double);
                            is_good = true;
                            break;
                        case "4":
                            Boolean value_boolean = Boolean.parseBoolean(value);
                            value= String.valueOf(value_boolean);
                            is_good = true;
                            break;
                    }
                }catch (Exception e) {
                    System.out.println(e);
                    System.out.println("Podana wartość nie jest zadeklarowanego typu.");
            }
            }
            prop.setProperty(key,value);
            change = true;
        } while (!key.equals("koniec"));

        if (change) {
            FileOutputStream fout = new FileOutputStream("PropertiesDemo.dat");
            prop.store(fout, "PropertiesDemo");
            fout.close();
        }

        Set<?> prop_set = prop.keySet();

        for (Object name : prop_set) {
            System.out.println("Klucz: " + name + " Wartość: " + prop.getProperty((String) name));
        }

    }
}
