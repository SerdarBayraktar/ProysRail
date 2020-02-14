package net.proys.proysrail.Entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class EtkenListeEntity {

    @PrimaryKey (autoGenerate = true)
    public Integer etken_id;

    public String isim;

    public Double vt_deger;

    public Integer getEtken_id() {
        return etken_id;
    }

    public void setEtken_id(Integer etken_id) {
        this.etken_id = etken_id;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public Double getVt_deger() {
        return vt_deger;
    }

    public void setVt_deger(Double vt_deger) {
        this.vt_deger = vt_deger;
    }
}
