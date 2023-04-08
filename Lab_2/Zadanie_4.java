package Lab_2;

import java.util.ArrayList;
import java.util.Random;
import java.util.TreeSet;

public class Zadanie_4 {
    public static void main(String[] args) {
        Integer MAX = Integer.parseInt(args[0]);
        Integer N = Integer.parseInt(args[1]);
        Integer S = Integer.parseInt(args[2]);

        Random random = new Random();
        TreeSet<Integer> wylosowaneliczbyUnikatowe = new TreeSet<>();
        ArrayList <Integer> wylosowaneliczbyWKolejnosc = new ArrayList<>();

        int temp = -1;

        while (temp != S) {
            temp = random.nextInt(MAX);
            wylosowaneliczbyUnikatowe.add(temp);
            wylosowaneliczbyWKolejnosc.add(temp);
        }

        System.out.println("Wylosowane liczny w kolejności losowania: ");
        System.out.println(wylosowaneliczbyWKolejnosc);
        if (N*2>wylosowaneliczbyWKolejnosc.size()) N = wylosowaneliczbyWKolejnosc.size()/2;

        ArrayList <Integer> liczby_przod = new ArrayList<>();
        for (int i=0;i<N;i++){
            liczby_przod.add(wylosowaneliczbyWKolejnosc.get(i));
        }
        System.out.print(N+" pierwsze liczb: ");
        System.out.println(liczby_przod);

        ArrayList <Integer> liczby_tyl = new ArrayList<>();
        for (int i=wylosowaneliczbyWKolejnosc.size()-1;i> wylosowaneliczbyWKolejnosc.size()-N-1;i--){
            liczby_tyl.add(wylosowaneliczbyWKolejnosc.get(i));
        }
        System.out.print(N+" ostatnie liczby: ");
        System.out.println(liczby_tyl);

        System.out.println("Wylosowane liczny unikatowe i posortowane: ");
        System.out.println(wylosowaneliczbyUnikatowe);


    }
}
