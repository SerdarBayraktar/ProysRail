package net.proys.proysrail.Items;

public class İsciPuantajItem {
    String name;
    String kategori;
    Float saat;
    public İsciPuantajItem(String name, Float saat,String kategori) {
        this.name = name;
        this.saat = saat;
        this.kategori = kategori;
    }



    public İsciPuantajItem() {
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getSaat() {
        return saat;
    }

    public void setSaat(Float saat) {
        this.saat = saat;
    }
}
