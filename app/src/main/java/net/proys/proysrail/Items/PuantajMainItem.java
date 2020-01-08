package net.proys.proysrail.Items;

public class PuantajMainItem {
    private String detay_name;
    private String  datay_rakam;

    public PuantajMainItem(String detay_name, String datay_rakam) {
        this.detay_name = detay_name;
        this.datay_rakam = datay_rakam;
    }

    public PuantajMainItem() {
    }

    public String getDetay_name() {
        return detay_name;
    }

    public void setDetay_name(String detay_name) {
        this.detay_name = detay_name;
    }

    public String getDatay_rakam() {
        return datay_rakam;
    }

    public void setDatay_rakam(String datay_rakam) {
        this.datay_rakam = datay_rakam;
    }
}
