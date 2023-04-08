package Lab_2.Zadanie_2;

public class Klient {
    private String nazwisko;
    Koszyk koszyk;

    public Klient(String nazwisko) {
        this.nazwisko = nazwisko;
        koszyk = new Koszyk();
    }

    public String getNazwisko() {
        return nazwisko;
    }
    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }


}
