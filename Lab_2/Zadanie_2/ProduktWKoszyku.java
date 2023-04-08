package Lab_2.Zadanie_2;

public class ProduktWKoszyku extends Produkt {
    private int liczbaSztukProduktu;

    @Override
    public String toString() {
        return super.toString() + " liczba sztuk=" + liczbaSztukProduktu;

    }

    public ProduktWKoszyku(){
        super();
        liczbaSztukProduktu=0;
    }
    public ProduktWKoszyku(Produkt p,int ilosc){
        super(p.getNazwa(), p.getCena());
        liczbaSztukProduktu = ilosc;
    }

    public int getLiczbaSztukProduktu() {
        return liczbaSztukProduktu;
    }
    public void setLiczbaSztukProduktu(int liczbaSztukProduktu) {
        this.liczbaSztukProduktu = liczbaSztukProduktu;
    }



}