package net.proys.proysrail.Items;

public class MakinePopupItem {
    String makine_name;
    Float state;

    public MakinePopupItem(String makine_name, Float state) {
        this.makine_name = makine_name;
        this.state = state;
    }

    public MakinePopupItem(String makine1) {
        this.makine_name = makine1;
    }

    public String getMakine_name() {
        return makine_name;
    }

    public void setMakine_name(String makine_name) {
        this.makine_name = makine_name;
    }

    public Float getState() {
        return state;
    }

    public void setState(Float state) {
        this.state = state;
    }
}
