package net.proys.proysrail.Items;

public class FuelMainItem {
    String fuel_date;
    String fuel_miktar;

    public FuelMainItem(String fuel_date, String fuel_miktar) {
        this.fuel_date = fuel_date;
        this.fuel_miktar = fuel_miktar;
    }

    public FuelMainItem() {
    }

    public String getFuel_date() {
        return fuel_date;
    }

    public void setFuel_date(String fuel_date) {
        this.fuel_date = fuel_date;
    }

    public String getFuel_miktar() {
        return fuel_miktar;
    }

    public void setFuel_miktar(String fuel_miktar) {
        this.fuel_miktar = fuel_miktar;
    }
}
