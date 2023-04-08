package Lab_2;

import java.util.Scanner;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Zadanie_3 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String rownanie = "";
        boolean find = false;

        String regexStr = "\\[(?:\\d+,)*\\d+][*+-]\\[(?:\\d+,)*\\d+]$";
        Pattern pattern = Pattern.compile(regexStr);
        System.out.println("Podaj dwa zbiory i operację jaką chcesz wykonać ");
        System.out.println("Możliwe operacje to (+)suma (-) różnica (*) część wspólna");
        System.out.println("Wzór wprowadzania danych [2,6,11]+[4,3,2]");
        while (!find) {
            try {

                rownanie = scanner.nextLine();
                Matcher matcher = pattern.matcher(rownanie);
                find = matcher.matches();
                matcher.group();

            } catch (IllegalStateException e) {
                System.out.println("Zły format podaj jeszcz raz");
            }
        }
        int znak = 0;
        if (rownanie.contains("+")) {
            znak = 1;
            rownanie = rownanie.replace("+", "&");
        } else if (rownanie.contains("*")) {
            znak = 2;
            rownanie = rownanie.replace("*", "&");
        } else if (rownanie.contains("-")) {
            znak = 3;
            rownanie = rownanie.replace("-", "&");
        }
        rownanie = rownanie.replaceAll("\\[", "");
        rownanie = rownanie.replaceAll("]", "");
        String[] zbiory = rownanie.split("&");
        String[] liczby1str = zbiory[0].split(",");
        String[] liczby2str = zbiory[1].split(",");

        Integer[] liczby1int = new Integer[liczby1str.length];
        for (int i = 0; i < liczby1str.length; i++) {
            liczby1int[i] = Integer.parseInt(liczby1str[i]);
        }
        Integer[] liczby2int = new Integer[liczby2str.length];
        for (int i = 0; i < liczby2str.length; i++) {
            liczby2int[i] = Integer.parseInt(liczby2str[i]);
        }

        TreeSet<Integer> liczby1 = new TreeSet<>();
        for (Integer idx : liczby1int) {
            liczby1.add(idx);
        }
        TreeSet<Integer> liczby2 = new TreeSet<>();
        for (Integer idx : liczby2int) {
            liczby2.add(idx);
        }

        TreeSet<Integer> wynik = new TreeSet<>();

        switch (znak) {
            case 1:
                wynik = liczby1;
                wynik.addAll(liczby2);
                break;
            case 2:
                for (Integer idx : liczby1) {
                    if (liczby2.contains(idx))
                        wynik.add(idx);
                }
                break;
            case 3:
                wynik = liczby1;
                wynik.addAll(liczby2);
                wynik.removeAll(liczby2);

        }
        System.out.println("Wynik: "+wynik);
    }
}

// Jeśli konieczne jest sortowanie elemntów lepszym wyborem jest TreeSet
// Treeset - Kolekcja jest przechowywana w strukturze drzewiastej.
// elementy są sortowane w porządku rosnącym
// HashSet nie gwarantuje posortowania elementów, ponieważ
// proces mieszania na ogół nie prowadzi do zachowania kolejności elementów

//wyjątki do zrobienia : liczby ujemne , zły format danych wejściowych