package Lab_2;

import java.util.Hashtable;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Zadanie_1 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String haslo="";
        String nazwa_uzytkownika="";
        Map<String, String> ht = new Hashtable<>();
        while (!nazwa_uzytkownika.equals("koniec")){
            System.out.println("Podaj nazwę użytkownika: ");
            nazwa_uzytkownika=scanner.nextLine();
            if (!nazwa_uzytkownika.equals("koniec")){
            System.out.println("Podaj hasło: ");
            haslo=scanner.nextLine();
            ht.put(nazwa_uzytkownika,haslo);}
        }
        System.out.println("Aby wyszukać hasło podaj nazwe użytkownika: ");
        nazwa_uzytkownika= scanner.nextLine();
        System.out.println("Twoje haslo: ");
        haslo=ht.get(nazwa_uzytkownika);
        System.out.println(Objects.requireNonNullElse(haslo, "Nie ma takiego użytkownika"));

    }
}

