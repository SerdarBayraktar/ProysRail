package net.proys.proysrail.Items;

public class İsciPuantajItem {
    String name;

    Float saat;
    public İsciPuantajItem(String name, Float saat) {
        this.name = name;
        this.saat = saat;
    }



    public İsciPuantajItem() {
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
