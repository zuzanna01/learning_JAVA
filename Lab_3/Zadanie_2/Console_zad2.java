package Lab_3.Zadanie_2;

import java.io.*;
import java.util.Properties;
import java.util.Set;

public class Console_zad2 {

    public static void main(String[] args) throws IOException {

        Console console;
        String key;
        String value = "";
        boolean change = false;
        Properties prop = new Properties();
        FileInputStream fin = null;

        console = System.console();
        if (console == null) return;

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
            key = console.readLine("Podaj klucz(parametr): \nWpisz 'koniec', aby zakonczyc\n");
            if (key.equals("koniec")) continue;

            boolean is_good = false;
            while (is_good == false) {
                try {
                    String type = console.readLine("Padaj typ parametru: \n(1) String (2) int (3) double (4)boolean \n");

                    value = console.readLine("Podaj wartosc: ");
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
                } catch (Exception e) {
                    System.out.println(e);
                    System.out.println("Podana wartosc nie jest zadeklarowanego typu. ");
                }
            }


            prop.setProperty(key, value);
            change = true;
        } while (!key.equals("koniec"));

        if (change) {
            FileOutputStream fout = new FileOutputStream("PropertiesDemo.dat");
            prop.store(fout, "PropertiesDemo");
            fout.close();
        }

        Set<?> prop_set = prop.keySet();

        for (Object name : prop_set) {
            System.out.println("Klucz: " + name + " wartosc: " + prop.getProperty((String) name));
        }


    }
}
