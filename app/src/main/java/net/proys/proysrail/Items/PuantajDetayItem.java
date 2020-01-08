package net.proys.proysrail.Items;

public class PuantajDetayItem {
    String detay_name;
    Boolean  checked=false;

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Boolean getChecked() {
        return checked;
    }

    public PuantajDetayItem(String detay_name) {
        this.detay_name = detay_name;
    }

    public PuantajDetayItem() {
    }

    public String getDetay_name() {
        return detay_name;
    }

    public void setDetay_name(String detay_name) {
        this.detay_name = detay_name;
    }
}
