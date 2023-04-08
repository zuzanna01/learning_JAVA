package Lab_2.Zadanie_2;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // tworzenie listy produktów
        ArrayList<Produkt> listaProduktow = new ArrayList<>();
        listaProduktow.add(new Produkt("mleko", 5.69));
        listaProduktow.add(new Produkt("cytryna", 2.38));
        listaProduktow.add(new Produkt("kisiel", 2.99));
        listaProduktow.add(new Produkt("bułka",0.79));
        listaProduktow.add(new Produkt("riso",2.49));
        listaProduktow.add(new Produkt("browar",2.99));
        listaProduktow.add(new Produkt("czekolada",5.29));
        listaProduktow.add(new Produkt("masło",7.59));
        listaProduktow.add(new Produkt("szynka",8.99));
        listaProduktow.add(new Produkt("proszek do prania",9.89));
        listaProduktow.add(new Produkt("woda",1.59));
        listaProduktow.add(new Produkt("pączek",2.59));

/*
        for (Produkt idx : listaProduktow) {
            System.out.println(idx);
        }
 */
        ArrayList<Klient> listaKlientow = new ArrayList<>();

        listaKlientow.add(new Klient("Brzozowski"));
        listaKlientow.get(0).koszyk.push(new ProduktWKoszyku(listaProduktow.get(2),7));
        listaKlientow.get(0).koszyk.push(new ProduktWKoszyku(listaProduktow.get(5),6));
        listaKlientow.get(0).koszyk.push(new ProduktWKoszyku(listaProduktow.get(9),2));
        listaKlientow.add(new Klient("Szpakowska"));
        listaKlientow.get(1).koszyk.push(new ProduktWKoszyku(listaProduktow.get(0),1));
        listaKlientow.get(1).koszyk.push(new ProduktWKoszyku(listaProduktow.get(4),3));
        listaKlientow.add(new Klient("Michałowska"));
        listaKlientow.get(2).koszyk.push(new ProduktWKoszyku(listaProduktow.get(2),7));
        listaKlientow.get(2).koszyk.push(new ProduktWKoszyku(listaProduktow.get(3),8));
        listaKlientow.get(2).koszyk.push(new ProduktWKoszyku(listaProduktow.get(4),2));
        listaKlientow.get(2).koszyk.push(new ProduktWKoszyku(listaProduktow.get(5),4));
        listaKlientow.get(2).koszyk.push(new ProduktWKoszyku(listaProduktow.get(11),4));
        // tworzenie kolejki
        ArrayDeque <Klient> kolejkaDoKasy =new ArrayDeque<>();
        kolejkaDoKasy.push(listaKlientow.get(0));
        kolejkaDoKasy.push(listaKlientow.get(1));
        kolejkaDoKasy.push(listaKlientow.get(2));

        // obsługa kolejki
        double doZapłaty;
        while (kolejkaDoKasy.peekLast()!=null) {
            doZapłaty=0;
            Klient temp = kolejkaDoKasy.pollLast();
            System.out.println("Koszyk klienta o nazwisku "+ temp.getNazwisko());
            System.out.println("Zawartość koszyka : ");
            while(!(temp.koszyk.isEmpty())){
                ProduktWKoszyku tempProdukt;
                tempProdukt = (ProduktWKoszyku) temp.koszyk.lastElement();
                System.out.println(temp.koszyk.lastElement());
                doZapłaty+=(tempProdukt.getCena()*tempProdukt.getLiczbaSztukProduktu());
                temp.koszyk.pop();
            }
            System.out.printf("Do zapłaty : %.2f", doZapłaty);
            System.out.println();

        }
    }
}
