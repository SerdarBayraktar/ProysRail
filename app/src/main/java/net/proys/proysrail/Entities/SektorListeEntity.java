package net.proys.proysrail.Entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class SektorListeEntity {

    @PrimaryKey(autoGenerate = true)
    public Integer sektor_id;

    public String isim;

    public Integer hat_no;

    public Integer km_bas;

    @NonNull
    public Integer km_bit;

    public Integer getSektor_id() {
        return sektor_id;
    }

    public void setSektor_id(Integer sektor_id) {
        this.sektor_id = sektor_id;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public Integer getHat_no() {
        return hat_no;
    }

    public void setHat_no(Integer hat_no) {
        this.hat_no = hat_no;
    }

    public Integer getKm_bas() {
        return km_bas;
    }

    public void setKm_bas(Integer km_bas) {
        this.km_bas = km_bas;
    }

    public Integer getKm_bit() {
        return km_bit;
    }

    public void setKm_bit(Integer km_bit) {
        this.km_bit = km_bit;
    }
}
