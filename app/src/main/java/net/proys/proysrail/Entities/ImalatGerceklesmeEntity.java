package net.proys.proysrail.Entities;

import java.sql.Date;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity/*(foreignKeys = {
        @ForeignKey(
                entity = ImalatListeEntity.class,
                parentColumns = "imalat_id",
                childColumns = "imalat"
        ),
        @ForeignKey(
                entity = SektorListeEntity.class,
                parentColumns = "sektor_id",
                childColumns = "sektor"
        ),
        @ForeignKey(
                entity = BildirilerEntity.class,
                parentColumns = "bildiri_id",
                childColumns = "bildiri"
        )

})*/
public class ImalatGerceklesmeEntity {

    @PrimaryKey(autoGenerate = true)
    public Integer gerceklesme_id;

    public String imalat;

    public String tarih;

    public Integer km_bas;

    public Integer km_bit;

    public String sektor;

    public Integer hat_no;

    public Integer ilerleme;

    public String bildiri;

    public Integer vardiya;

    public Boolean minpi;

    public Integer getGerceklesme_id() {
        return gerceklesme_id;
    }

    public void setGerceklesme_id(Integer gerceklesme_id) {
        this.gerceklesme_id = gerceklesme_id;
    }

    public String getImalat() {
        return imalat;
    }

    public void setImalat(String imalat) {
        this.imalat = imalat;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
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

    public String getSektor() {
        return sektor;
    }

    public void setSektor(String sektor) {
        this.sektor = sektor;
    }

    public Integer getHat_no() {
        return hat_no;
    }

    public void setHat_no(Integer hat_no) {
        this.hat_no = hat_no;
    }

    public Integer getIlerleme() {
        return ilerleme;
    }

    public void setIlerleme(Integer ilerleme) {
        this.ilerleme = ilerleme;
    }

    public String getBildiri() {
        return bildiri;
    }

    public void setBildiri(String bildiri) {
        this.bildiri = bildiri;
    }

    public Integer getVardiya() {
        return vardiya;
    }

    public void setVardiya(Integer vardiya) {
        this.vardiya = vardiya;
    }

    public Boolean getMinpi() {
        return minpi;
    }

    public void setMinpi(Boolean minpi) {
        this.minpi = minpi;
    }
}
