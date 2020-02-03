package net.proys.proysrail.Entities;

import java.sql.Date;
import java.sql.Time;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(
                entity = BildiriTipListeEntity.class,
                parentColumns = "bildiritip_id",
                childColumns = "bildiri_tipi"
        ),
        @ForeignKey(
                entity = KullanicilarEntity.class,
                parentColumns = "kullanici_id",
                childColumns = "kullanici"
        )
})
public class BildirilerEntity {

    @PrimaryKey(autoGenerate = true)
    public Long bildiri_id;

    public int bildiri_tipi;

    public String bildiri_tarih;

    public int kullanici;

    public int kabul;

    public String son_giris;

    public String kabul_zamani;

    public int getBildiri_tipi() {
        return bildiri_tipi;
    }

    public void setBildiri_tipi(int bildiri_tipi) {
        this.bildiri_tipi = bildiri_tipi;
    }

    public int getKullanici() {
        return kullanici;
    }

    public void setKullanici(int kullanici) {
        this.kullanici = kullanici;
    }

    public Long getBildiri_id() {
        return bildiri_id;
    }

    public void setBildiri_id(Long bildiri_id) {
        this.bildiri_id = bildiri_id;
    }

    public String getBildiri_tarih() {
        return bildiri_tarih;
    }

    public void setBildiri_tarih(String bildiri_tarih) {
        this.bildiri_tarih = bildiri_tarih;
    }

    public int getKabul() {
        return kabul;
    }

    public void setKabul(int kabul) {
        this.kabul = kabul;
    }

    public String getSon_giris() {
        return son_giris;
    }

    public void setSon_giris(String son_giris) {
        this.son_giris = son_giris;
    }

    public String getKabul_zamani() {
        return kabul_zamani;
    }

    public void setKabul_zamani(String kabul_zamani) {
        this.kabul_zamani = kabul_zamani;
    }
}
