package net.proys.proysrail.Entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class SektorListeEntity {

    @PrimaryKey(autoGenerate = true)
    public int sektor_id;

    public String isim;

    public int hat_no;

    public int km_bas;

    @NonNull
    public int km_bit;

    public int getSektor_id() {
        return sektor_id;
    }

    public void setSektor_id(int sektor_id) {
        this.sektor_id = sektor_id;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public int getHat_no() {
        return hat_no;
    }

    public void setHat_no(int hat_no) {
        this.hat_no = hat_no;
    }

    public int getKm_bas() {
        return km_bas;
    }

    public void setKm_bas(int km_bas) {
        this.km_bas = km_bas;
    }

    public int getKm_bit() {
        return km_bit;
    }

    public void setKm_bit(int km_bit) {
        this.km_bit = km_bit;
    }
}
