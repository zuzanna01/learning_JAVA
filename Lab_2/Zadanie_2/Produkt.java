package Lab_2.Zadanie_2;

public class Produkt {
    private double cena;
    private String nazwa;

    public Produkt() {
        this.cena = 0;
        this.nazwa="";
    }

    public Produkt(String nazwa, double cena){
        this.nazwa=nazwa;
        this.cena=cena;
    }

    @Override
    public String toString() {
        return nazwa+ " kosztuje " + cena +" zł";
    }


    public double getCena() {
        return cena;
    }
    public void setCena(double cena) {
        this.cena = cena;
    }
    public String getNazwa() {
        return nazwa;
    }
    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }


}
